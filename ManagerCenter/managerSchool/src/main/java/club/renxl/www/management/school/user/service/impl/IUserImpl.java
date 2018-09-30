package club.renxl.www.management.school.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import club.renxl.www.management.school.user.dao.UserMapper;
import club.renxl.www.management.school.user.dao.domain.User;
import club.renxl.www.management.school.user.service.IUser;
import club.renxl.www.pageInfo.PageInfo;
import club.renxl.www.response.BaseResponse;

public class IUserImpl implements IUser {
	@Autowired
	private UserMapper userMapper;
	/*
	 * (non-Javadoc)
	 * @see club.renxl.www.management.school.user.service.IUser#addUser(club.renxl.www.management.school.user.dao.domain.User)
	 */
	@Override
	public BaseResponse addUser(User user) {
		// 参数校验
		boolean legal		 = validateUserWhenAdd(user);
		if(!legal) {
			return BaseResponse.argsError("参数校验失败");
		}
		
		// 业务处理
		int resultNum = userMapper.insert(user);
		
		// 返回结果
		if(resultNum <= 0) {
			return BaseResponse.error("");
		}
		return BaseResponse.success("添加用户成功");
	}

	private boolean validateUserWhenAdd(User user) {
		 if(StringUtils.isEmpty(user)) {
			 return false;
		 }
		return true;
	}

	@Override
	public BaseResponse deleteUser(User user) {
		// 参数校验
		boolean legal		 = validateUserWhenDelete(user);
		if(!legal) {
			return BaseResponse.argsError("参数校验失败...");
		}
		
		// 业务处理
		int resultNum = userMapper.deleteByPrimaryKey(user.getId());
		
		
		// 返回结果
		if(resultNum <= 0) {
			// TODO 记录日志
			return BaseResponse.error("删除用户失败");
		}
		return BaseResponse.success("删除用户成功");
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

	@Override
	public BaseResponse deletesUser(List<User> users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse lookUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse pageUser(PageInfo<User> pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
