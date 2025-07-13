package storage.dto;

import storage.model.Menu;

public class AuthDTO {
	private Integer authId;
	private Integer activeFlag;
	private Integer permission;
	private Menu menu;
	public AuthDTO(Integer authId, Integer activeFlag, Integer permission, Menu menu) {
		super();
		this.authId = authId;
		this.activeFlag = activeFlag;
		this.permission = permission;
		this.menu = menu;
	}
	public AuthDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getAuthId() {
		return authId;
	}
	public void setAuthId(Integer authId) {
		this.authId = authId;
	}
	public Integer getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Integer getPermission() {
		return permission;
	}
	public void setPermission(Integer permission) {
		this.permission = permission;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	
}