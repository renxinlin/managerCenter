package club.renxl.www.management.school.user.http.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.renxl.www.management.school.user.dao.domain.Permission;
import club.renxl.www.management.school.user.service.IMenu;
import club.renxl.www.management.school.user.service.IPermission;
import club.renxl.www.pageInfo.PageInfo;
import club.renxl.www.response.BaseResponse;
import club.renxl.www.response.LayuiResponse;

/**
 * 权限管理 维护权限表 分为操作权限和菜单树两个维度
 * 
 * @author renxl
 * @date 2018/09/26
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
	private Logger log = LoggerFactory.getLogger(PermissionController.class);
	@Autowired
	private IPermission iPermission;

	@Autowired
	private IMenu iMenu;

	/**
	 * 获取菜单树结构
	 * 
	 * @return
	 */
	@RequestMapping("menu")
	public BaseResponse menuTree() {
		return iMenu.lookTree();
	}

	/**
	 * 增加权限
	 */
	@RequestMapping("add")
	public BaseResponse addPermission(@RequestBody Permission permission) {
	//	log.debug(JSONUtils.toJSONString(new SystemLogEntity(this.getClass().getName(), "addPermission", "", "")));
		return iPermission.addPermission(permission);

	}

	/**
	 * 修改权限
	 */
	@RequestMapping("update")
	public BaseResponse updatePermission(@RequestBody Permission permission) {
		return iPermission.updatePermission(permission);

	}

	/**
	 * 删除权限
	 */
	@RequestMapping("delete")
	public BaseResponse deletePermission(@RequestBody Permission permission) {
		return iPermission.deletePermission(permission);

	}

	/**
	 * 批量 删除权限
	 * id集合通过name承载传递
	 */
	@RequestMapping("deletes")
	public BaseResponse deletesPermission(@RequestBody Permission permission) {
		return iPermission.deletesPermission(permission);

	}

	/**
	 * 权限查看
	 */
	@RequestMapping("look")
	public BaseResponse lookPermission(@RequestBody Permission permission) {
		return iPermission.lookPermission(permission);

	}

	/**
	 * 权限分页
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("page")
	public LayuiResponse pagePermission(@RequestBody Map<String, Object> param) {
		Integer page = (Integer) param.get("page");
		Integer limit = (Integer) param.get("limit");
		String name = (String) param.get("name");
		String path =  (String) param.get("path");
		PageInfo<Permission> permissioPage = new PageInfo<Permission>();
		permissioPage.setPage(page);
		permissioPage.setRows(limit);
		Permission condtion = new Permission();
		condtion.setName(name);
		condtion.setPath(path);
		permissioPage.setCondtion(condtion );
		BaseResponse pagePermission = iPermission.pagePermission(permissioPage);
		// 转换为layui所需格式
		LayuiResponse layuiResponse = LayuiResponse.initByBaseResponse(pagePermission,
				(PageInfo<Object>) pagePermission.getData());
		return layuiResponse;

	}

}
