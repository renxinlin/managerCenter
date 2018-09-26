package club.renxl.www.management.school.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import club.renxl.www.enums.UserResponse;
import club.renxl.www.management.school.user.dao.PermissionMapper;
import club.renxl.www.management.school.user.dao.domain.Permission;
import club.renxl.www.management.school.user.dao.domain.PermissionExample;
import club.renxl.www.management.school.user.dao.domain.PermissionExample.Criteria;
import club.renxl.www.management.school.user.service.IPermission;
import club.renxl.www.pageInfo.PageInfo;
import club.renxl.www.pageInfo.PageUtil;
import club.renxl.www.response.BaseResponse;


/**
 * 权限管理
 * @author renxl
 * @date 2018/09/26
 * @verison 1.0.0
 *
 */


@Service
public class IPermisssionImpl implements IPermission {
	/**
	 * 操作资源
	 */
	private static final String OPERATION_RESOURCE = "0";
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	
	

	/*
	 * (non-Javadoc)
	 * @see club.renxl.www.management.school.user.service.IPermission#addPermission(club.renxl.www.management.school.user.dao.domain.Permission)
	 */
	@Override
	public BaseResponse addPermission(Permission permission) {
		// 验证并补充数据
		boolean legal = validateAddPermissionParam(permission);
		if (!legal) {
			return BaseResponse.argsError(permission);
		}

		// db操作保存权限数据并返回结果
		int insertNum = permissionMapper.insertSelective(permission);
		if (insertNum == 0) {
			return BaseResponse.error(UserResponse.INSERT_NULL_ERROR.getCode(), "权限添加失败...");

		}
		return BaseResponse.success("权限添加成功...");
	}


	/*
	 * (non-Javadoc)
	 * @see club.renxl.www.management.school.user.service.IPermission#updatePermission(club.renxl.www.management.school.user.dao.domain.Permission)
	 */
	@Override
	public BaseResponse updatePermission(Permission permission) {
		// 参数处理
		if(permission == null || permission.getId()==null) {
			return BaseResponse.argsError("权限选择失败");
		}
		
		// 业务处理
		int resultNum = permissionMapper.updateByPrimaryKeySelective(permission);
		
		// 结果处理
		if (resultNum == 0) {
			return BaseResponse.error(UserResponse.UPDATE_NULL_ERROR.getCode(), "权限更新失败...");

		}
		return BaseResponse.success("权限更新成功...");
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see club.renxl.www.management.school.user.service.IPermission#lookPermission(club.renxl.www.management.school.user.dao.domain.Permission)
	 */
	@Override
	public BaseResponse lookPermission(Permission permission) {
		// 参数处理
		if(permission == null || permission.getId()==null) {
			return BaseResponse.argsError("权限选择失败");
		}
		
		// 业务处理
		Permission result = permissionMapper.selectByPrimaryKey(permission.getId());
		
		
		// 结果处理
		if(result == null) {
			return BaseResponse.error("数据不存在...");
		}
		return BaseResponse.success(result);
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see club.renxl.www.management.school.user.service.IPermission#pagePermission(club.renxl.www.pageInfo.PageInfo)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public BaseResponse pagePermission(PageInfo<Permission> permissionPage) {
		// 初始化查询条件
		PermissionExample example				 							    = new PermissionExample();
		example 							  = initSelectCondition(example,permissionPage.getCondtion());
		
		
		// 分页查询
		PageHelper						   .startPage(permissionPage.getPage(), permissionPage.getRows());
		List<Permission> results 							  = permissionMapper.selectByExample(example);
		com.github.pagehelper.PageInfo pageInfo = new com.github.pagehelper.PageInfo<Permission>(results);

		
		// 转化成框架结果
		PageUtil<Permission> pu 											 = new PageUtil<Permission>();
		PageInfo<Permission> bizResult = pu.getPageInfoByPageInfo(pageInfo, permissionPage.getCondtion());
		return BaseResponse.success(bizResult);
	}
	
	
	
	
	
	
	
	
	

	/**
	 * 验证增加权限的参数并补充必要参数
	 * 
	 * @param permission
	 * @return
	 */
	private boolean validateAddPermissionParam(Permission permission) {
		// 校验
		if (permission.getPath() == null) {
			return false;
		}
		// 初始化数据;朝操作权限方向拟合
		permission.init();
		return true;
	}
	
	/**
	 * mybatis example:初始化查询条件;此处分页不查询菜单信息
	 * @param example
	 * @param permission 可以根据名称查询
	 * @return
	 */
	private PermissionExample initSelectCondition(PermissionExample example, Permission permission) {
		Criteria createCriteria 	  = example.createCriteria();
		if (permission != null && permission.getName() != null) {
			 createCriteria	  .andNameLike(permission.getName());
		}
		// 只查询操作资源;不查询菜单
		createCriteria		 .andTypeEqualTo(OPERATION_RESOURCE);
		return example;
	}

}
