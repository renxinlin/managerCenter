package club.renxl.www.management.school.user.http.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.renxl.www.management.school.user.dao.domain.Permission;
import club.renxl.www.management.school.user.dao.domain.Role;
import club.renxl.www.management.school.user.service.IRole;
import club.renxl.www.pageInfo.PageInfo;
import club.renxl.www.response.BaseResponse;
import club.renxl.www.response.LayuiResponse;

/**
 * 角色管理
 * 维护角色和角色-权限
 * @author renxl
 * @date 2018/09/29
 * @version 1.0.0
 * 
 *
 */
@RestController
@RequestMapping("role")
public class RoleController {
	@Autowired
	private IRole irole;
	
	/**
	 * 	添加角色以及权限
	 * @return
	 */
	@RequestMapping("add")
	public BaseResponse addRole(@RequestBody Role role) {
		return irole.addRole(role);
	}
	
	

	/**
	 * 	删除角色以及权限
	 * @return
	 */
	@RequestMapping("delete")
	public BaseResponse deleteRole(@RequestBody Role role) {
		return irole.deleteRole(role);
	}
	
	/**
	 * 	删除角色以及权限
	 * @return
	 */
	@RequestMapping("deletes")
	public BaseResponse deletesRole(@RequestBody Role role) {
		return irole.deleteRoles(role);
	}
	
	
	
	

	/**
	 *   更新角色以及权限
	 * @return
	 */
	@RequestMapping("update")
	public BaseResponse updateRole(@RequestBody Role role) {
		return irole.updateRole(role);
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("page")
	public LayuiResponse lookRole(@RequestBody Map<String,Object> param) {
		Integer page = (Integer) param.get("page");
		Integer limit = (Integer) param.get("limit");
		String name = (String) param.get("name");
		PageInfo<Role> rolePage = new PageInfo<Role>();
		rolePage.setPage(page);
		rolePage.setRows(limit);
		Role condtion = new Role();
		condtion.setRoleName(name);
		rolePage.setCondtion(condtion );
		BaseResponse baseResponse = irole.lookRole(rolePage);
		LayuiResponse layuiResponse = LayuiResponse.initByBaseResponse(baseResponse, (PageInfo)baseResponse.getData());
		return layuiResponse;
	}
	
	
	/**
	 *  查看与编辑权限
	 * @param rolePage
	 * @return
	 */
	@RequestMapping("look")
	public BaseResponse lookRoleDetail(@RequestBody Role role) {
		return irole.lookRoleDetail(role);
	}
	
	
	
	
	
	
}
