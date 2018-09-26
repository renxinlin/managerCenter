package club.renxl.www.management.school.user.service;

import club.renxl.www.response.BaseResponse;

/**
 * 菜单树结构管理
 * @author renxl
 * @date 2018/09/26
 * @version 1.0.0
 *
 */
public interface IMenu {
	/**
	 * 查询树结构
	 * @return
	 */
	BaseResponse lookTree();
	
	/**
	 * 查询详情 参见
	 * @param permission
	 * @return
	 */
	// BaseResponse lookTreeDetail(Permission permission);
	
	// insert update lookTreeDetail from IPermission
	
	// delete not dev now
	
}
