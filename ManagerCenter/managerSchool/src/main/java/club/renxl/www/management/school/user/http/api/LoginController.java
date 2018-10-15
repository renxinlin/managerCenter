package club.renxl.www.management.school.user.http.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.renxl.www.management.school.user.dao.domain.User;
import club.renxl.www.management.school.user.service.ILogin;
import club.renxl.www.response.BaseResponse;

/**
 * 登录管理
 * @author win10
 * @date 2018/10/15
 *
 */
@RestController
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private ILogin ilogin;
	
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@RequestMapping("in")
	public BaseResponse login(HttpServletRequest request,@RequestBody User user) {
		return ilogin.login(request,user);
	}
	
	
	
	/**
	 * 退出登录
	 * @param user
	 * @return
	 */
	@RequestMapping("out")
	public BaseResponse logOut(HttpServletRequest request,@RequestBody User user) {
		return ilogin.loginOut(request,user);
	}
	
	
	/**
	 * 获取登录用户
	 * @param user
	 * @return
	 */
	@RequestMapping("user")
	public BaseResponse getUser(HttpServletRequest request) {
		return ilogin.getUser(request);
		
	}
}
