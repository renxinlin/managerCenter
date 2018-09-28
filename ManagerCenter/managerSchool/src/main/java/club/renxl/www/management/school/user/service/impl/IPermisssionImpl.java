package club.renxl.www.management.school.user.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;

import club.renxl.www.common.constants.Separator;
import club.renxl.www.common.constants.Symbol;
import club.renxl.www.common.utlis.StringToIntUtil;
import club.renxl.www.enums.UserResponse;
import club.renxl.www.management.school.user.dao.PermissionMapper;
import club.renxl.www.management.school.user.dao.RolePermissionMapper;
import club.renxl.www.management.school.user.dao.domain.Permission;
import club.renxl.www.management.school.user.dao.domain.PermissionExample;
import club.renxl.www.management.school.user.dao.domain.PermissionExample.Criteria;
import club.renxl.www.management.school.user.dao.domain.RolePermissionExample;
import club.renxl.www.management.school.user.service.IPermission;
import club.renxl.www.pageInfo.PageInfo;
import club.renxl.www.pageInfo.PageUtil;
import club.renxl.www.response.BaseResponse;

/**
 * 权限管理
 * 
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
	private  Executor sysExecutor;
	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * club.renxl.www.management.school.user.service.IPermission#addPermission(club.
	 * renxl.www.management.school.user.dao.domain.Permission)
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
	 * 
	 * @see
	 * club.renxl.www.management.school.user.service.IPermission#updatePermission(
	 * club.renxl.www.management.school.user.dao.domain.Permission)
	 */
	@Override
	public BaseResponse updatePermission(Permission permission) {
		// 参数处理
		if (permission == null || permission.getId() == null) {
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
	 * 
	 * @see
	 * club.renxl.www.management.school.user.service.IPermission#deletePermission(
	 * club.renxl.www.management.school.user.dao.domain.Permission)
	 */
	@Override
	@Transactional
	public BaseResponse deletesPermission(Permission permission) {
		
		// TODO 测试事务是否生效
		// 参数处理 用name承载id的集合
		if (permission == null || permission.getName() == null) {
			return BaseResponse.argsError("权限选择失败");
		}
		
		
		// 通过name获取待删除id集合
		String[] idStrs = permission.getName().split(Separator.COMMA);
		int[] idInts = StringToIntUtil.stringArrayToIntArray(idStrs);
		// apache commons-lang3
		Integer[] idIntegers = ArrayUtils.toObject(idInts);
		List<Integer> list = Arrays.asList(idIntegers);

		
		// 业务处理
		// 删除角色关联到的这条权限
		RolePermissionExample example 				= new RolePermissionExample();
		club.renxl.www.management.school.user.dao.domain
		.RolePermissionExample.Criteria createCriteria = example .createCriteria();
		createCriteria				   					  .andPermissionIdIn(list);
		rolePermissionMapper							 .deleteByExample(example);
		// 删除权限
		PermissionExample permissionExample 			 = new PermissionExample();
		Criteria createCriteria2 			  = permissionExample.createCriteria();
		createCriteria2												.andIdIn(list);
		int deleteNum       = permissionMapper.deleteByExample(permissionExample );
		  
		
		// 结果处理
		if (deleteNum == 0) {
			return BaseResponse.error(UserResponse.DELETE_NULL_ERROR.getCode(), "权限删除失败...");
		}
		return BaseResponse.success("权限删除成功...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * club.renxl.www.management.school.user.service.IPermission#deletePermission(
	 * club.renxl.www.management.school.user.dao.domain.Permission)
	 */
	@Override
	@Transactional
	public BaseResponse deletePermission(Permission permission) {
		
		
		// TODO 测试事务是否生效
		// 参数处理
		if (permission == null || permission.getId() == null) {
			return BaseResponse.argsError("权限选择失败");
		}

		
		// 业务处理
		// 删除角色关联到的这条权限
		RolePermissionExample example 				= new RolePermissionExample();
		club.renxl.www.management.school.user.dao.domain
		.RolePermissionExample.Criteria createCriteria = example .createCriteria();
		createCriteria				   .andPermissionIdEqualTo(permission.getId());
		rolePermissionMapper							 .deleteByExample(example);
		// 删除权限
		int deleteNum = permissionMapper	.deleteByPrimaryKey(permission.getId());
		
		
		// 线程操作来源于张开涛-京东的启发,但丢失了事务;
		// 对于接口异步调用可以如此;对db操作除非线程内增加幂等操作
//		sysExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				rolePermissionMapper.deleteByExample(example);
//				// 删除权限
//				permissionMapper.deleteByPrimaryKey(permission.getId());
//			}
//			
//		});
		
		
		// 结果处理
		if (deleteNum == 0) {
			return BaseResponse.error(UserResponse.DELETE_NULL_ERROR.getCode(), "权限删除失败...");
		}
		return BaseResponse.success("权限删除成功...");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * club.renxl.www.management.school.user.service.IPermission#lookPermission(club
	 * .renxl.www.management.school.user.dao.domain.Permission)
	 */
	@Override
	public BaseResponse lookPermission(Permission permission) {
		// 参数处理
		if (permission == null || permission.getId() == null) {
			return BaseResponse.argsError("权限选择失败");
		}

		// 业务处理
		Permission result = permissionMapper.selectByPrimaryKey(permission.getId());

		// 结果处理
		if (result == null) {
			return BaseResponse.error("数据不存在...");
		}
		return BaseResponse.success(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * club.renxl.www.management.school.user.service.IPermission#pagePermission(club
	 * .renxl.www.pageInfo.PageInfo)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public BaseResponse pagePermission(PageInfo<Permission> permissionPage) {
		// 初始化查询条件
		PermissionExample example = new PermissionExample();
		example = initSelectCondition(example, permissionPage.getCondtion());

		// 分页查询
		PageHelper.startPage(permissionPage.getPage(), permissionPage.getRows());
		List<Permission> results = permissionMapper.selectByExample(example);
		com.github.pagehelper.PageInfo pageInfo = new com.github.pagehelper.PageInfo<Permission>(results);

		// 转化成框架结果
		PageUtil<Permission> pu = new PageUtil<Permission>();
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
	 * 
	 * @param example
	 * @param permission 可以根据名称查询
	 * @return
	 */
	private PermissionExample initSelectCondition(PermissionExample example, Permission permission) {
		Criteria createCriteria = example.createCriteria();
		if (permission != null && !StringUtils.isEmpty(permission.getName())) {
			createCriteria.andNameLike(Symbol.PERCENT + permission.getName() + Symbol.PERCENT);
		}
		
		if (permission != null && !StringUtils.isEmpty(permission.getPath())) {
			createCriteria.andPathLike(Symbol.PERCENT + permission.getPath() + Symbol.PERCENT);
		}
		
		
		// 只查询操作资源;不查询菜单
		createCriteria.andTypeEqualTo(OPERATION_RESOURCE);
		return example;
	}

}
