package club.renxl.www.management.school.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renxl.www.management.util.SessionUtil;

import club.renxl.www.common.utlis.MD5;
import club.renxl.www.management.school.user.dao.UserMapper;
import club.renxl.www.management.school.user.dao.domain.User;
import club.renxl.www.management.school.user.dao.domain.UserExample;
import club.renxl.www.management.school.user.dao.domain.UserExample.Criteria;
import club.renxl.www.management.school.user.service.ILogin;
import club.renxl.www.response.BaseResponse;

@Service
public class ILoginImpl implements ILogin {
	private static final int ONLY_ONE_ELEMENT_LIST_INDEX = 0;
	@Autowired
	private UserMapper userMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * club.renxl.www.management.school.user.service.ILogin#login(club.renxl.www.
	 * management.school.user.dao.domain.User)
	 */
	@Override
	public BaseResponse login(User user) {
		// 参数校验
		if (user == null || user.getUsername() == null || user.getPassword() == null) {
			return BaseResponse.error("登录失败");
		}
		//TODO 验证码校验
		
		
		// 数据查询
		UserExample example = new UserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUsernameEqualTo(user.getUsername());
		List<User> results = userMapper.selectByExample(example);
		if (results != null && !results.isEmpty()) {
			return BaseResponse.error("用户名/密码校验失败...");
		}
		
		// 密码校验
		User sysUserInfo = results.get(ONLY_ONE_ELEMENT_LIST_INDEX);
		boolean verify =false;
		try {
			verify = MD5.verify(user.getPassword(), sysUserInfo.getPassword());
		} catch (Exception e) {
			verify = false;
		}
		if (verify) {
			// 跳转首页
			sysUserInfo.setPassword(null);
			SessionUtil.setUser(sysUserInfo);
			return BaseResponse.success("登录成功...");

		} else {
			// 跳转登录页面
			return BaseResponse.error("用户名/密码校验失败...");

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * club.renxl.www.management.school.user.service.ILogin#loginOut(club.renxl.www.
	 * management.school.user.dao.domain.User)
	 */
	@Override
	public BaseResponse loginOut(User user) {
		if(user == null || user.getUsername() == null) {
			return BaseResponse.error("退出系统失败...");
		}
		SessionUtil.getSessionAttribute(user.getUsername());
		User sessionUser = SessionUtil.getUser();
		if (user.getUsername().equals(sessionUser.getUsername())) {
			
			SessionUtil.removerUser();
			return BaseResponse.success("已经退出...");

		}
		return BaseResponse.error("退出系统失败...");
	}

}
