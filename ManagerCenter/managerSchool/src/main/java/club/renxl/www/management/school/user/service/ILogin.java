package club.renxl.www.management.school.user.service;

import club.renxl.www.management.school.user.dao.domain.User;
import club.renxl.www.response.BaseResponse;

/**
 * 登录登出
 * @author renxl
 * @date 2018/09/26
 * @version 1.0.0
 *
 */
public interface ILogin {
	
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	BaseResponse login(User user);
	
	
	/**
	 * 登出
	 * @param user
	 * @return
	 */
	BaseResponse loginOut(User user);

}
