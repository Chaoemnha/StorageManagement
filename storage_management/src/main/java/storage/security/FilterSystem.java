package storage.security;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import storage.model.Auth;
import storage.model.User;
import storage.model.UserRole;
import storage.util.Constant;

public class FilterSystem implements HandlerInterceptor {
	Logger logger = Logger.getLogger(FilterSystem.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("Request URI ="+request.getRequestURI());
		User user = (User) request.getSession().getAttribute(Constant.USER_INFO);
		if(user==null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		if(user!=null) {
			String url = request.getServletPath();
			if(!hasPermission(url, user)) {
				response.sendRedirect(request.getContextPath()+"/access-denied");
				return false;
			}
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	private boolean hasPermission(String url, User user) {
		if(url.contains("/index")||url.contains("/access-denied"))
			return true;
		UserRole userRole = user.getUserRoles().iterator().next();
		Set<Auth> auths = userRole.getRole().getAuths();
		for(Object obj: auths) {
			Auth auth = (Auth) obj;
			if(url.contains(auth.getMenu().getUrl()))
				return auth.getPermission()==1;
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
