package club.renxl.www.management.school.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.renxl.www.management.util.SessionUtil;

import club.renxl.www.common.constants.Constant;
import club.renxl.www.common.utlis.MD5;
import club.renxl.www.enums.UserResponse;
import club.renxl.www.management.school.user.dao.RoleMapper;
import club.renxl.www.management.school.user.dao.UserMapper;
import club.renxl.www.management.school.user.dao.UserRoleMapper;
import club.renxl.www.management.school.user.dao.domain.Role;
import club.renxl.www.management.school.user.dao.domain.RoleExample;
import club.renxl.www.management.school.user.dao.domain.User;
import club.renxl.www.management.school.user.dao.domain.UserExample;
import club.renxl.www.management.school.user.dao.domain.UserExample.Criteria;
import club.renxl.www.management.school.user.dao.domain.UserRole;
import club.renxl.www.management.school.user.dao.domain.UserRoleExample;
import club.renxl.www.management.school.user.service.IUser;
import club.renxl.www.pageInfo.PageInfo;
import club.renxl.www.pageInfo.PageUtil;
import club.renxl.www.response.BaseResponse;
@Service
public class IUserImpl implements IUser {
	
	private final static Logger log = LoggerFactory.getLogger(IUserImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	  
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	 
	@Autowired
	private ThreadPoolTaskExecutor executor;
	/*
	 * (non-Javadoc)
	 * @see club.renxl.www.management.school.user.service.IUser#addUser(club.renxl.www.management.school.user.dao.domain.User)
	 */
	@Override
	@Transactional
	public BaseResponse addUser(HttpServletRequest request,User user) {
		// 参数校验
		boolean legal		 = validateUserWhenAdd(user);
		if(!legal) {
			return BaseResponse.argsError("参数校验失败");
		}
		
		// 业务处理1: 用户处理
		// 密码加密
		try {
			String password = MD5.md5(user.getPassword());
			user				   .setPassword(password);
		} catch (Exception e) {
			return BaseResponse.error(UserResponse.PASSWORD_CAHNGGE_ERROR.getCode(),UserResponse.PASSWORD_CAHNGGE_ERROR.getMsg());
		}
		int resultNum = userMapper.insert(user);
		
		
		// 业务处理2: 角色处理
		if (!CollectionUtils			.isEmpty(user.getUserRoles())) {
			List<UserRole> finallyData = initUserRolesWithUserInfo(request,user);
			if(!CollectionUtils					.isEmpty(finallyData)) {
				userRoleMapper			   .insertWithBatch(finallyData);
			}
		}
		
		
		// 返回结果
		if(resultNum <= 0) {
			return BaseResponse.error(UserResponse.INSERT_NULL_ERROR.getCode(),UserResponse.INSERT_NULL_ERROR.getMsg());
		}
		return BaseResponse.success("添加用户成功");
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see club.renxl.www.management.school.user.service.IUser#deleteUser(club.renxl.www.management.school.user.dao.domain.User)
	 */
	@Override
	@Transactional
	public BaseResponse deleteUser(User user) {
		// 参数校验;可能存在横向越权吗？
		boolean legal		 = validateUserWhenDelete(user);
		if(!legal) {
			return BaseResponse.argsError("参数校验失败...");
		}
		
		// 业务处理1 :用户删除
		int resultNum = userMapper.deleteByPrimaryKey(user.getId());
		
		// 业务处理2: 用户角色删除
		UserRoleExample example							 = new UserRoleExample();
		club.renxl.www.management.school.user.dao.domain.UserRoleExample.Criteria 
		deleteCondition 							  = example.createCriteria();
		deleteCondition							 .andUserIdEqualTo(user.getId());
		userRoleMapper								  .deleteByExample(example );
		
		// 返回结果
		if(resultNum <= 0) {
			// TODO 记录日志
			return BaseResponse.error("删除用户数量为零");
		}
		return BaseResponse.success("删除用户成功");
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see club.renxl.www.management.school.user.service.IUser#deletesUser(java.util.List)
	 */
	@Override
	public BaseResponse deletesUser(List<User> users) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see club.renxl.www.management.school.user.service.IUser#updateUser(club.renxl.www.management.school.user.dao.domain.User)
	 */
	@Override
	@Transactional
	public BaseResponse updateUser(HttpServletRequest request, User user) {
		boolean legal		 = validateUserWhenUpdate(user);
		if(!legal) {
			return BaseResponse.argsError("参数校验失败...");
		}
		
		// 业务处理1 :用户删除
		int resultNum = userMapper.updateByPrimaryKeySelective(user);
		
		if (!CollectionUtils			.isEmpty(user.getUserRoles())) {
			
			// 业务处理2: 用户角色删除
			UserRoleExample example							 = new UserRoleExample();
			club.renxl.www.management.school.user.dao.domain.UserRoleExample.Criteria 
			deleteCondition 							  = example.createCriteria();
			deleteCondition							 .andUserIdEqualTo(user.getId());
			userRoleMapper								  .deleteByExample(example );
		
			// 业务处理3: 用户角色新增
			List<UserRole> finallyData = initUserRolesWithUserInfo(request, user);
			if(!CollectionUtils					.isEmpty(finallyData)) {
				userRoleMapper			   .insertWithBatch(finallyData);
			}
		}
		
		// 返回结果
		if(resultNum <= 0) {
			// TODO 记录日志
			return BaseResponse.error("更新用户数量为零");
		}
		return BaseResponse.success("更新用户成功");
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see club.renxl.www.management.school.user.service.IUser#lookUser(club.renxl.www.management.school.user.dao.domain.User)
	 */
	@Override
	public BaseResponse lookUser( User user) throws InterruptedException, ExecutionException {
		
		// 参数校验
		boolean legal  = validateUserWhenLookDatail(user);
		if(!legal) {
			return BaseResponse.argsError("参数校验失败...");
		}
		final User threadUser				  =new User();
		BeanUtils		.copyProperties(user, threadUser);
		
		// 业务处理1: 查询用户信息
		Future<User> futureUser = executor.submit(new Callable<User>() {
			@Override
			public User call() throws Exception {
				return  userMapper.selectByPrimaryKey(threadUser.getId());
			}
			
		});
		
		// 业务处理2:查询全部角色
		Future<List<Role>> futureAllRoles = executor.submit(new Callable<List<Role>>() {
			@Override
			public List<Role> call() throws Exception {
				RoleExample example 				  = new RoleExample();
				List<Role> allRoles = roleMapper.selectByExample(example);
				return allRoles;
			}
		});
		
		// 业务处理2: 查询用户具有的角色
		Future<List<UserRole>> futureUserRoles = executor.submit(new Callable<List<UserRole>>() {
			@Override
			public List<UserRole> call() throws Exception {
				UserRoleExample userRoleExample 				   = new UserRoleExample();
				club.renxl.www.management.school.user.dao.domain.UserRoleExample.Criteria 
				condition 								= userRoleExample.createCriteria();
				condition							 .andUserIdEqualTo(threadUser.getId());
				List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
				return userRoles;
			}
		});
		
		// 获取线程执行结果
		user = futureUser.get();
		List<Role> allRoles = futureAllRoles.get();
		List<UserRole> userRoles = futureUserRoles.get();

		// 标记已经拥有的权限
		for (Role role : allRoles) {
			role.setLAY_CHECKED(Constant.RIGHTT_BOOLEAN);
			for (UserRole selected : userRoles) {
				if (selected.getRoleId().intValue() == role.getId().intValue()) {
					role.setLAY_CHECKED(Constant.LEFT_BOOLEAN);
					break;
				}
			}
		}
		
		// 返回结果
		user.setRoles(allRoles);
		return BaseResponse.success(user);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see club.renxl.www.management.school.user.service.IUser#pageUser(club.renxl.www.pageInfo.PageInfo)
	 */
	@Override
	public BaseResponse pageUser(PageInfo<User> pageInfo) {
		// 校验参数
		boolean legal 	= validateParamWhenPage(pageInfo);
		if (!legal) {
			return BaseResponse.argsError("参数校验失败...");
		}
		
		// 分页查询
		User condtion																 = pageInfo.getCondtion();
		UserExample example 								  = initSqlConditionByPageInfoCondition(condtion);
		PageHelper										   .startPage(pageInfo.getPage(), pageInfo.getRows());
		List<User> results											   = userMapper.selectByExample(example );
		com.github.pagehelper.PageInfo<User> pageInfoData = new com.github.pagehelper.PageInfo<User>(results);

		// 转化成框架结果
		PageUtil<User> pu 												   = new PageUtil<User>();
		PageInfo<User> bizResult = pu.getPageInfoByPageInfo(pageInfoData, pageInfo.getCondtion());
		return BaseResponse.success(bizResult);
	}
	/////////////////////////////////////////////////////////功能方法区////////////////////////////////////////////////////////////////
	
	
	/**
	 *	 返回插入带有的用户id
	 * @param user
	 */
	private List<UserRole> initUserRolesWithUserInfo(HttpServletRequest request,User user) {
		List<UserRole> userRoles 	    = user.getUserRoles();
		List<UserRole> returnList = new ArrayList<UserRole>();
		Long userId 			  			   = user.getId();
		
		// 补充参数
		for (UserRole userRole : userRoles) {
			if (StringUtils		 .isEmpty(userRole.getRoleId()) 
					||  userRole.getRoleId().intValue() <= 0) {
				continue;
			}
			
			// 无法获取用户信息不影响业务逻辑处理
			userRole										 .setUserId(userId);
			try {
				userRole		.setCreateUserId(SessionUtil.getUser(request).getId());
				userRole.setCreateUserName(SessionUtil.getUser(request).getUsername());
			} catch (Exception e) {
				// TODO system log entity
				log.error(" ==> initUserRolesWithUserInfo " + e.getMessage());
			}
			
			// 返回体添加正确元素
			returnList.add(userRole);
		}
		return returnList;
	}

	/**
	 * 	新增用户的校验
	 * @param user
	 * @return
	 */
	private boolean validateUserWhenAdd(User user) {
		if(StringUtils.isEmpty(user)) {
			 return false;
		}
		
		// 用户名
		if(StringUtils.isEmpty(user.getUsername())) {
			 return false;
		}
		// 用户密码
		if(StringUtils.isEmpty(user.getPassword())) {
			 return false;
		}
	 
		// 后台用户对应的学校id
		if(StringUtils.isEmpty(user.getSchoolId())) {
			 return false;
		}
		
		// 后台用户对应的学校名称
		if(StringUtils.isEmpty(user.getSchoolName())) {
			 return false;
		}
		
		return true;
	}

	
	private boolean validateUserWhenDelete(User user) {
		if(StringUtils.isEmpty(user)) {
			return false;
		}
		if(StringUtils.isEmpty(user.getId())) {
			return false;
		}
		if(user.getId() <= 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 	更新用户时的校验
	 * @param user
	 * @return
	 */
	private boolean validateUserWhenUpdate(User user) {
		if(StringUtils.isEmpty(user)) {
			return false;
		}
		
		if(StringUtils.isEmpty(user.getId())) {
			return false;

		}
		
		if(user.getId() <= 0) {
			return false;
		}
		
		// 密码只能通过邮箱,短信等方式
		user.setPassword(null);
		return true;
	}
	/**
	 * 
	 * @param user 用户ID
	 * @return
	 */
	private boolean validateUserWhenLookDatail(User user) {
		if(StringUtils.isEmpty(user)) {
			return false;
		
		}
		if(StringUtils.isEmpty(user.getId())) {
			return false;

		}
		if(user.getId() <= 0) {
			return false;

		}
		return true;
	}


	
	
	/**
	 * 分页查询的参数校验
	 * @param pageInfo
	 * @return
	 */
	private boolean validateParamWhenPage(PageInfo<User> pageInfo) {
		if(StringUtils.isEmpty(pageInfo)) {
			return false;
		}
		
		// TODO 判断数据的规则是否正确
		
		return true;
	}
	
	/**
	 * 	初始化分页查询条件
	 * @param condtion 用户查询参数
	 * @return
	 */
	private UserExample initSqlConditionByPageInfoCondition(User condtion) {
		UserExample example = new UserExample();
		// 判断是否需要传递查询参数
		if	(StringUtils.isEmpty(condtion)) {
			return example;
		}
		
		// 对需要的条件进行like equals 等查询
		// or 的写法
		Criteria condition  = example.createCriteria();
		Criteria condition1 = example.createCriteria();

		if(!StringUtils.isEmpty(condtion.getRealName())) {
			condition.andRealNameLike("%" + condtion.getRealName() + "%");
			
		}
		
		// 登录名
		if(!StringUtils.isEmpty(condtion.getUsername())) {
			condition.andUsernameLike("%" + condtion.getUsername() + "%");
		}
		
		// 用户姓名
		if(!StringUtils.isEmpty(condtion.getNickName())) {
			condition1.andNickNameLike("%" + condtion.getNickName() + "%");
		}
		
		// 学校名称
		if(!StringUtils.isEmpty(condtion.getSchoolName())) {
			condition.andSchoolNameLike("%" + condtion.getSchoolName() + "%");
			condition1.andSchoolNameLike("%" + condtion.getSchoolName() + "%");
		}
		
		// 手机号 非十三位 近似查询 
		if(!StringUtils.isEmpty(condtion.getUserPhone())) {
			condition.andUserPhoneLike("%" + condtion.getUserPhone() + "%");
			condition1.andUserPhoneLike("%" + condtion.getUserPhone() + "%");
		}
		
		
		
		
		// 学校省份
		if(!StringUtils.isEmpty(condtion.getSchoolProvince())) {
			condition.andSchoolProvinceLike("%" + condtion.getSchoolProvince() + "%");
			condition1.andSchoolProvinceLike("%" + condtion.getSchoolProvince() + "%");
		}
		return example;
	}

}
