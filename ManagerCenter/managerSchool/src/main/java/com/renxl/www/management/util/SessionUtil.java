package com.renxl.www.management.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import club.renxl.www.management.school.user.dao.domain.User;


/**

 * session工具类
 * @author renxl
 * @date 2018-1-16
 * @version 1.0.0
 **/
public class SessionUtil {
	/**
	 * session存储的key名称
	 */
	private static final String USER_KEY_NAME = "user";
	/**
	 * 全局删除id标示
	 */
	public static String GLOB_DELETE_ID_VAL = "globDeleteIdVal";

	/**
	 * 获取request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return requestAttributes == null ? null : requestAttributes.getRequest();
	}

	/**
	 * 获取session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession(false);
	}

	/**
	 * 获取真实路径
	 * 
	 * @return
	 */
	public static String getRealRootPath() {
		return getRequest().getServletContext().getRealPath("/");
	}

	/**
	 * 获取ip
	 * 
	 * @return
	 */
	public static String getIp() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (servletRequestAttributes != null) {
			HttpServletRequest request = servletRequestAttributes.getRequest();
			return request.getRemoteAddr();
		}
		return null;
	}

	/**
	 * 获取session中的Attribute
	 * 
	 * @param name
	 * @return
	 */
	public static Object getSessionAttribute(String name) {
		HttpServletRequest request = getRequest();
		return request == null ? null : request.getSession().getAttribute(name);
	}

	/**
	 * 设置session的Attribute
	 * 
	 * @param name
	 * @param value
	 */
	public static void setSessionAttribute(String name, Object value) {
		HttpServletRequest request = getRequest();
		if (request != null) {
			request.getSession().setAttribute(name, value);
		}
	}

	
	
	
	
	
	
	/**
	 * 获取session中用户
	 * 
	 * @param name
	 * @return
	 */
	public static User getUser() {
		HttpServletRequest request = getRequest();
		return (User) (request == null ? null : request.getSession().getAttribute(USER_KEY_NAME));
	}

	/**
	 * 设置session的用户
	 * 
	 * @param name
	 * @param value
	 */
	public static void setUser(User value) {
		HttpServletRequest request = getRequest();
		if (request != null) {
			request.getSession().setAttribute(USER_KEY_NAME, value);
		}
	}


	public static void removerUser() {
		HttpServletRequest request = getRequest();
		if (request != null) {
			request.getSession().removeAttribute(USER_KEY_NAME);
		}
		
	}
	
	
	
	
	
	/**
	 * 获取request中的Attribute
	 * 
	 * @param name
	 * @return
	 */
	public static Object getRequestAttribute(String name) {
		HttpServletRequest request = getRequest();
		return request == null ? null : request.getAttribute(name);
	}

	/**
	 * 设置request的Attribute
	 * 
	 * @param name
	 * @param value
	 */
	public static void setRequestAttribute(String name, Object value) {
		HttpServletRequest request = getRequest();
		if (request != null) {
			request.setAttribute(name, value);
		}
	}

	/**
	 * 获取上下文path
	 * 
	 * @return
	 */
	public static String getContextPath() {
		return getRequest().getContextPath();
	}

	/**
	 * 删除session中的Attribute
	 * 
	 * @param name
	 */
	public static void removeSessionAttribute(String name) {
		getRequest().getSession().removeAttribute(name);
	}


}
 