package club.renxl.www.management.school.user.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;

import club.renxl.www.common.constants.Constant;
import club.renxl.www.common.constants.Separator;
import club.renxl.www.common.constants.Symbol;
import club.renxl.www.common.utlis.StringToIntUtil;
import club.renxl.www.enums.UserResponse;
import club.renxl.www.management.school.user.dao.PermissionMapper;
import club.renxl.www.management.school.user.dao.RoleMapper;
import club.renxl.www.management.school.user.dao.RolePermissionMapper;
import club.renxl.www.management.school.user.dao.domain.Permission;
import club.renxl.www.management.school.user.dao.domain.PermissionExample;
import club.renxl.www.management.school.user.dao.domain.Role;
import club.renxl.www.management.school.user.dao.domain.RoleExample;
import club.renxl.www.management.school.user.dao.domain.RolePermission;
import club.renxl.www.management.school.user.dao.domain.RolePermissionExample;
import club.renxl.www.management.school.user.dao.domain.RolePermissionExample.Criteria;
import club.renxl.www.management.school.user.service.IRole;
import club.renxl.www.pageInfo.PageInfo;
import club.renxl.www.pageInfo.PageUtil;
import club.renxl.www.response.BaseResponse;

/**
 * 角色/角色权限管理
 * 
 * @author renxl
 * @date 2018/09/29
 * @version 1.0.0
 *
 */
@Service
public class IRoleImpl implements IRole {
	/**
	 * 操作资源
	 */
	private static final String OPERATION_RESOURCES = "0";
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private PermissionMapper permissionMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * club.renxl.www.management.school.user.service.IRole#lookRole(club.renxl.www.
	 * pageInfo.PageInfo)
	 */
	@Override
	public BaseResponse lookRole(PageInfo<Role> rolePage) {
		// 初始化查询条件
		RoleExample example = new RoleExample();
		initConditionByParam(example, rolePage);

		// 分页查询
		PageHelper.startPage(rolePage.getPage(), rolePage.getRows());
		List<Role> roles = roleMapper.selectByExample(example);
		com.github.pagehelper.PageInfo<Role> pageInfo = new com.github.pagehelper.PageInfo<Role>(roles);

		// 转化成框架结果
		PageUtil<Role> pu = new PageUtil<Role>();
		PageInfo<Role> bizResult = pu.getPageInfoByPageInfo(pageInfo, rolePage.getCondtion());
		return BaseResponse.success(bizResult);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * club.renxl.www.management.school.user.service.IRole#lookRoleDetail(club.renxl
	 * .www.management.school.user.dao.domain.Role)
	 */
	@Override
	public BaseResponse lookRoleDetail(Role role) {
		// TODO 需要在所有的权限中标记出已经选中的权限
		// 参数校验
		boolean legal = validateParamWhenLookDetail(role);
		if (!legal) {
			return BaseResponse.argsError("后台交互失败...");
		}

		// 业务处理
		// 角色查询
		role = roleMapper.selectByPrimaryKey(role.getId());
		
		// 权限查询
		PermissionExample example						 = new PermissionExample();
		club.renxl.www.management.school.user.dao.domain.PermissionExample.Criteria
		createCriteria2 								= example.createCriteria();
		createCriteria2						  .andTypeEqualTo(OPERATION_RESOURCES);
		List<Permission> permissions   = permissionMapper.selectByExample(example);
		
		// 角色权限查询
		RolePermissionExample example1 					  = new RolePermissionExample();
		Criteria createCriteria 						    = example1.createCriteria();
		createCriteria				 					.andRoleIdEqualTo(role.getId());
		List<RolePermission> selecteds = rolePermissionMapper.selectByExample(example1);
		
		// 标记角色拥有的权限
		for (Permission permission : permissions) {
			permission.setLAY_CHECKED(Constant.RIGHTT_BOOLEAN);
			for (RolePermission selected : selecteds) {
				if (selected.getPermissionId().intValue() == permission.getId().intValue()) {
					permission.setLAY_CHECKED(Constant.LEFT_BOOLEAN);
					break;
				}
			}
		}

		// 返回网页所需结果
		role.setPermissions(permissions);
		return BaseResponse.success(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * club.renxl.www.management.school.user.service.IRole#addRole(club.renxl.www.
	 * management.school.user.dao.domain.Role)
	 */
	@Override
	@Transactional
	public BaseResponse addRole(Role role) {
		// 校验参数
		boolean legal = validateRoleWhenAdd(role);
		if (!legal) {
			return BaseResponse.argsError("表单填写校验失败...");
		}

		// 业务处理:
		// 角色
		role = addFieldToRoleWhenAdd(role);
		roleMapper.insertSelective(role);
		// 角色权限
		if (role.getRolePermissions() != null && !role.getRolePermissions().isEmpty()) {
			role = addRolePermissionIdByRole(role);
			rolePermissionMapper.insertSelectiveBatch(role.getRolePermissions());
		}

		// 结果返回
		return BaseResponse.success("添加成功...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * club.renxl.www.management.school.user.service.IRole#deleteRole(club.renxl.www
	 * .management.school.user.dao.domain.Role)
	 */
	@Override
	@Transactional
	public BaseResponse deleteRole(Role role) {
		// 校验参数
		boolean legal = validateRoleWhenDelete(role);
		if (!legal) {
			return BaseResponse.argsError("获取角色失败...");
		}

		// 业务处理:
		// 角色删除
		roleMapper.deleteByPrimaryKey(role.getId());
		// 角色权限删除
		RolePermissionExample example = new RolePermissionExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andRoleIdEqualTo(role.getId());
		rolePermissionMapper.deleteByExample(example);

		// 结果返回
		return BaseResponse.success("删除成功...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * club.renxl.www.management.school.user.service.IRole#deleteRoles(club.renxl.
	 * www.management.school.user.dao.domain.Role)
	 */
	@Override
	@Transactional
	public BaseResponse deleteRoles(Role role) {
		// 参数校验
		if (role == null || StringUtils.isEmpty(role.getRoleName())) {
			return BaseResponse.argsError("角色获取失败...");
		}

		// 通过name获取待删除id集合
		String[] idStrs = role.getRoleName().split(Separator.COMMA);
		int[] idInts = StringToIntUtil.stringArrayToIntArray(idStrs);
		// apache commons-lang3
		Integer[] idIntegers = ArrayUtils.toObject(idInts);
		List<Integer> list = Arrays.asList(idIntegers);

		// 业务处理
		// 删除角色
		RoleExample roleExample = new RoleExample();
		club.renxl.www.management.school.user.dao.domain.RoleExample.Criteria createCriteria2 = roleExample
				.createCriteria();
		createCriteria2.andIdIn(list);
		int deleteNum = roleMapper.deleteByExample(roleExample);
		// 删除角色关联到的这条权限
		RolePermissionExample example = new RolePermissionExample();
		club.renxl.www.management.school.user.dao.domain.RolePermissionExample.Criteria createCriteria = example
				.createCriteria();
		createCriteria.andRoleIdIn(list);
		rolePermissionMapper.deleteByExample(example);

		// 结果处理
		if (deleteNum == 0) {
			return BaseResponse.error(UserResponse.DELETE_NULL_ERROR.getCode(), "权限删除失败...");
		}
		return BaseResponse.success("权限删除成功...");
	}

	@Override
	public BaseResponse updateRole(Role role) {
		// 校验参数
		boolean legal = validateRoleWhenUpdate(role);
		if (!legal) {
			return BaseResponse.argsError("表单填写校验失败...");
		}

		// 业务处理:
		// 角色
		role = addFieldToRoleWhenUpdate(role);
		roleMapper.updateByPrimaryKeySelective(role);
		// 角色权限
		// 角色权限删除
		RolePermissionExample example = new RolePermissionExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andRoleIdEqualTo(role.getId());
		rolePermissionMapper.deleteByExample(example);
		// 角色权限增加
		if (role.getRolePermissions() != null || !role.getRolePermissions().isEmpty()) {
			role = addRolePermissionIdByRole(role);
			rolePermissionMapper.insertSelectiveBatch(role.getRolePermissions());
		}

		// 结果返回
		return BaseResponse.success("添加成功...");
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 初始化分页查询条件
	 * 
	 * @param example
	 * @param rolePage
	 */
	private void initConditionByParam(RoleExample example, PageInfo<Role> rolePage) {
		Role condtion = rolePage.getCondtion();
		club.renxl.www.management.school.user.dao.domain.RoleExample.Criteria createCriteria = example.createCriteria();
		if (!StringUtils.isEmpty(condtion) && !StringUtils.isEmpty(condtion.getRoleName())) {
			createCriteria.andRoleNameLike(Symbol.PERCENT + condtion.getRoleName() + Symbol.PERCENT);
		}
	}

	/**
	 * 查看详情时候的校验
	 * 
	 * @param role
	 * @return
	 */
	private boolean validateParamWhenLookDetail(Role role) {
		if (StringUtils.isEmpty(role)) {
			return false;
		}

		if (StringUtils.isEmpty(role.getId())) {
			return false;
		}

		if (role.getId() <= 0) {
			return false;
		}

		return true;
	}

	/**
	 * 增加角色的校验
	 * 
	 * @param role
	 * @return
	 */
	private boolean validateRoleWhenAdd(Role role) {
		if (role.getRoleName() == null) {
			return false;
		}

		return true;
	}

	/**
	 * 增加角色时候的参数补充
	 * 
	 * @param role
	 * @return
	 */
	private Role addFieldToRoleWhenAdd(Role role) {
		role.setCreateDate(new Date());
		if (StringUtils.isEmpty(role.getStatus())) {
			role.setStatus(Constant.LEFT_STRING);
		}
		// TODO 补充用户参数
		// role.setCreateUserId(createUserId);
		return role;
	}

	/**
	 * 删除角色时候的权限验证
	 * 
	 * @param role
	 * @return
	 */
	private boolean validateRoleWhenDelete(Role role) {
		if (role == null) {
			return false;
		}

		if (role.getId() == null || role.getId() <= 0) {
			return false;
		}

		return true;
	}

	/**
	 * 更新权限的参数补充
	 * 
	 * @param role
	 * @return
	 */
	private Role addFieldToRoleWhenUpdate(Role role) {
		role.setModifyDate(new Date());
		return role;
	}

	/**
	 * 更新权限的验证
	 * 
	 * @param role
	 * @return
	 */
	private boolean validateRoleWhenUpdate(Role role) {
		if (StringUtils.isEmpty(role)) {
			return false;
		}

		if (StringUtils.isEmpty(role.getId())) {
			return false;
		}

		return true;
	}

	/**
	 * 初始化角色权限对应的角色id
	 * 
	 * @param role
	 * @return
	 */
	private Role addRolePermissionIdByRole(Role role) {
		List<RolePermission> rolePermissions = role.getRolePermissions();
		for (RolePermission rolePermission : rolePermissions) {
			rolePermission.setRoleId(role.getId());
		}
		return role;
	}

}
