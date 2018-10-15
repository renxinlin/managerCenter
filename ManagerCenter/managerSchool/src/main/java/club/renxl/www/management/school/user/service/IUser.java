package club.renxl.www.management.school.user.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

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
	 * 	添加用户;以及用户所拥有的角色
	 * 
	 * @param user 
	 * @return
	 */
	BaseResponse addUser(HttpServletRequest request, User user);
	
	/**
	 * 	删除用户以及用户所拥有的角色
	 * 
	 * @param user id
	 * @return
	 */
	BaseResponse deleteUser(User user);
	
	
	/**
	 * 	批量删除用户以及用户所拥有的角色
	 * 
	 * @param users id的集合
	 * @return
	 */
	BaseResponse deletesUser(List<User> users);
	
	
	/**
	 * 	更新用户信息;删除用户所拥有的权限，新增用户所拥有的权限
	 * 
	 * @param user
	 * @return
	 */
	BaseResponse updateUser(HttpServletRequest request, User user);

	
	/**
	 * 	用户信息查询以及获取用户所拥有的角色
	 * 
	 * @param user
	 * @return
	 * @throws ExecutionException  线程执行异常
	 * @throws InterruptedException 线程执行中断
	 */
	BaseResponse lookUser(User user) throws InterruptedException, ExecutionException;
	
	
	/**
	 * 	分页查询用户
	 * 
	 * @param user
	 * @return
	 */
	BaseResponse pageUser(PageInfo<User> pageInfo);


}
