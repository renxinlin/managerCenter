package club.renxl.www.management.school.user.service;

import club.renxl.www.management.school.user.dao.domain.Permission;
import club.renxl.www.pageInfo.PageInfo;
import club.renxl.www.response.BaseResponse;

/**
 * 权限管理接口
 * 
 * @author renxl
 * @date 2018/09/26
 * @version 1.0.0
 *
 */
public interface IPermission {
	/**
	 * 增加权限
	 * 
	 * @param permission
	 * @return
	 */
	BaseResponse addPermission(Permission permission);

	/**
	 * 修改权限
	 * 
	 * @param permission
	 * @return
	 */
	BaseResponse updatePermission(Permission permission);

	/**
	 * 查询权限详情
	 * 
	 * @param permission
	 * @return
	 */
	BaseResponse lookPermission(Permission permission);

	/**
	 * 	权限分页
	 * 
	 * @param permission
	 * @return
	 */
	BaseResponse pagePermission(PageInfo<Permission> permission);

	/**
	 *	 权限删除
	 * 
	 * @param permission
	 * @return
	 */
	BaseResponse deletePermission(Permission permission);

	/**
	 * 	批量删除; name属性接收ID的集合
	 * 
	 * @param permission
	 * @return
	 */
	BaseResponse deletesPermission(Permission permission);

	/**
	 *	 该功能暂时不做 权限删除;当角色使用了权限后不可以被删除|删除角色具有的权限
	 * 
	 * @param permission
	 * @return
	 */
	// BaseResponse delete(Permission permission);

}
