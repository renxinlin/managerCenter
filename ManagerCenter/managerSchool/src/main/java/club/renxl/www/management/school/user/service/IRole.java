package club.renxl.www.management.school.user.service;

import club.renxl.www.management.school.user.dao.domain.Role;
import club.renxl.www.pageInfo.PageInfo;
import club.renxl.www.response.BaseResponse;

/**
 * 角色管理
 * 维护角色和角色权限关联表
 * @author renxl
 * @date 2018/09/26
 * @version 1.0.0
 *
 */
public interface IRole {
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	BaseResponse addRole(Role role);
	
	/**
	 * 删除角色，同时删除角色对应的权限
	 * @param role
	 * @return
	 */
	BaseResponse deleteRole(Role role);

	/**
	 * 查看角色 ;没有角色详情查看，角色对应的权限查看
	 * 角色本身的详情查看没有什么必要
	 * @param role
	 * @return
	 */
	BaseResponse lookRole(PageInfo<Role> rolePage);
	
	// 角色名称的修改，也对应到角色权限的修改功能中
	
	
	
	
	
	
	////////////////////////////////////////////////////////角色权限////////////////////////////////////////
	///关联角色;角色对应的权限，权限名称等///////////////////////////////////////////////////////////////////
	
	 
	

}
