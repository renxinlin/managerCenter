package club.renxl.www.management.school.user.http.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.renxl.www.management.school.user.dao.domain.Permission;
import club.renxl.www.management.school.user.dao.domain.User;
import club.renxl.www.management.school.user.service.IUser;
import club.renxl.www.pageInfo.PageInfo;
import club.renxl.www.pageInfo.PageUtil;
import club.renxl.www.response.BaseResponse;
import club.renxl.www.response.LayuiResponse;

/**
 * 用户管理
 * @author renxl
 * @date 2018/09/30
 *
 */
@RestController
@RequestMapping("userManager")
public class UserController {
	@Autowired
	private IUser iuser; 

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@RequestMapping("add")
	public BaseResponse addUser(@RequestBody User user) {
		return iuser.addUser(user);
		
	}
	
	
	

	/**
	 *  分页用户
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("page")
	public LayuiResponse pageUser(@RequestBody Map<String,Object> param) {
		Integer page = (Integer) param.get("page");
		Integer limit = (Integer) param.get("limit");
		String name = (String) param.get("name");
		String path =  (String) param.get("path");
		
		PageInfo<User> pageInfo = new PageInfo<User>();

		pageInfo.setPage(page);
		pageInfo.setRows(limit);
		User condtion = new User();
		 
		pageInfo.setCondtion(condtion );
		BaseResponse pageUser = iuser.pageUser(pageInfo);
		LayuiResponse layuiResponse = LayuiResponse.initByBaseResponse(pageUser,
				(PageInfo<User>) pageUser.getData());
		return layuiResponse;
		
	}

}
