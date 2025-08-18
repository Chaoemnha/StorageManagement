package storage.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import storage.dao.AuthDAO;
import storage.dao.MenuDAO;
import storage.model.Auth;
import storage.model.AuthForm;
import storage.model.Menu;
import storage.model.Paging;
import storage.model.Role;

@Service
public class MenuService {
	@Autowired
	private MenuDAO<Menu> menuDAO;
	@Autowired
	private AuthDAO<Auth> authDAO;
	private static final Logger log = Logger.getLogger(MenuService.class);

	public List<Menu> getListMenu(Paging paging, Menu menu) {
		log.info("show all menu");
		StringBuilder queryStr = new StringBuilder();
		//tim tat ca menu co AF=0 hoac AF=1 => lay dc tat
		queryStr.append(" or model.activeFlag=0");
		Map<String, Object> mapParams = new HashMap<>();
		if (menu != null) {
			if (StringUtils.hasLength(menu.getUrl())) {
				queryStr.append(" and model.url like :url");
				mapParams.put("url", "%" + menu.getUrl() + "%");
			}
		}
		return menuDAO.findAll(queryStr.toString(), mapParams, paging);
	}
	
	public void changeStatus(Integer id) {
		Menu menu = menuDAO.findById(Menu.class, id);
		if(menu!=null) {
			menu.setActiveFlag(menu.getActiveFlag()==1?0:1);
			menuDAO.update(menu);
		}
	}
	
	public void updatePermission(AuthForm authForm) {
		int roleId = authForm.getRoleId();
		int menuId = authForm.getMenuId();
		int permission = authForm.getPermission();
		Auth auth = authDAO.find(roleId, menuId);
		if(auth!=null) {
			auth.setPermission(permission);
			authDAO.update(auth);
		}
		else {
			if(permission==1) {
				auth = new Auth();
				auth.setActiveFlag(1);
				Role role = new Role();
				role.setId(roleId);
				Menu menu = new Menu();
				menu.setId(menuId);
				auth.setRole(role);
				auth.setMenu(menu);
				auth.setPermission(1);
				auth.setCreateDate(new Timestamp(new Date().getTime()));
				auth.setUpdateDate(new Timestamp(new Date().getTime()));
				authDAO.save(auth);
			}
			//Ko can lam per==0 vi no se khong co trong bang auth
		}
	}
}
