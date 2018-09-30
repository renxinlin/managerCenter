package club.renxl.www.management.school.user.service;

import java.util.List;

import club.renxl.www.management.school.user.dao.domain.User;
import club.renxl.www.pageInfo.PageInfo;
import club.renxl.www.response.BaseResponse;

/**
 * 	用户管理接口
 * 
 * @author renxl
 * @date 2018/09/30
 * @version 1.0.0
 *
 */
public interface IUser {
	/**
	 * 	添加用户
	 * 
	 * @param user 
	 * @return
	 */
	BaseResponse addUser(User user);
	
	/**
	 * 	删除用户
	 * 
	 * @param user id
	 * @return
	 */
	BaseResponse deleteUser(User user);
	
	
	/**
	 * 	批量删除用户
	 * 
	 * @param users id的集合
	 * @return
	 */
	BaseResponse deletesUser(List<User> users);
	
	
	/**
	 * 	更新用户
	 * 
	 * @param user
	 * @return
	 */
	BaseResponse updateUser(User user);
	
	
	/**
	 * 	用户信息查询
	 * 
	 * @param user
	 * @return
	 */
	BaseResponse lookUser(User user);
	
	
	/**
	 * 	分页查询用户
	 * 
	 * @param user
	 * @return
	 */
	BaseResponse pageUser(PageInfo<User> pageInfo);
}
