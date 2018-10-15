package club.renxl.www.management.school.user.service;

import javax.servlet.http.HttpServletRequest;

import club.renxl.www.management.school.user.dao.domain.User;
import club.renxl.www.response.BaseResponse;

/**
 * 登录登出 关于HttpServletRequest
 * 框架基于http协议，所以request和response可以携带至业务服务层
 * @author renxl
 * @date 2018/09/26
 * @version 1.0.1
 *
 */
public interface ILogin {
	
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	BaseResponse login(HttpServletRequest request, User user);
	
	
	/**
	 * 登出
	 * @param user
	 * @return
	 */
	BaseResponse loginOut(HttpServletRequest request, User user);

	/**
	 * 获取当前登录用户;业务服务层与领域层分离
	 * @param request
	 * @return
	 */
	BaseResponse getUser(HttpServletRequest request);



}
