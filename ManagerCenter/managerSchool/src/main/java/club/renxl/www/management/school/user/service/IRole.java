package club.renxl.www.management.school.user.service;

import club.renxl.www.management.school.user.dao.domain.Role;
import club.renxl.www.pageInfo.PageInfo;
import club.renxl.www.response.BaseResponse;

/**
 * 角色管理
 * 维护角色和角色权限关联表
 * 菜单暂时不做
 * @author renxl
 * @date 2018/09/26
 * @version 1.0.0
 *
 */
public interface IRole {
	
	/**
	 *  添加角色;添加角色对应的权限
	 * @param role
	 * @return
	 */
	BaseResponse addRole(Role role);
	
	/**
	 * 删除角色，同时删除角色对应的权限
	 * @param role roleId
	 * @return
	 */
	BaseResponse deleteRole(Role role);

	/**
	 *	 查看角色以及角色对应的权限信息
	 *	 状态位标记已经获取的权限 
	 *  
	 * @param role
	 * @return
	 */
	BaseResponse lookRole(PageInfo<Role> rolePage);
	
	/**
	 * 	更新角色，以及角色对应的权限信息
	 * @param role
	 * @return
	 */
	BaseResponse updateRole(Role role);
	
	/**
	 *  查看权限以及对已经拥有的权限应的角色
	 *  状态位标记
	 * @param role
	 * @return
	 */
	BaseResponse lookRoleDetail(Role role);
	
	/**
	 * 	批量删除
	 *  roleName存储id集合
	 * @param role
	 * @return
	 */
	BaseResponse deleteRoles(Role role);

	
	
	
	
	////////////////////////////////////////////////////////角色权限////////////////////////////////////////
	///关联角色;角色对应的权限，权限名称等///////////////////////////////////////////////////////////////////
	
	 
	

}
