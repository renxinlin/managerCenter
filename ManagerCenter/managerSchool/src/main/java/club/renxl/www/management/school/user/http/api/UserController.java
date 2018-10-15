package club.renxl.www.management.school.user.http.api;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.renxl.www.management.school.user.dao.domain.User;
import club.renxl.www.management.school.user.service.IUser;
import club.renxl.www.pageInfo.PageInfo;
import club.renxl.www.response.BaseResponse;
import club.renxl.www.response.LayuiResponse;

/**
 * 用户管理
 * 
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
	 *  分页用户
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("page")
	public LayuiResponse pageUser(@RequestBody Map<String,Object> param) {
		// 获取查询参数 
		Integer page 					= (Integer) param.get("page");
		Integer limit				   = (Integer) param.get("limit");
		String userName				 = (String) param.get("username");
		String nickName 			=  (String) param.get("username");
		String userPhone 		   =  (String) param.get("userPhone");
		String schoolName 		  =  (String) param.get("schoolName");
		String schoolProvince =  (String) param.get("schoolProvince");
		
		
		// 创建业务框架查询条件
		PageInfo<User> pageInfo = new PageInfo<User>();
		if (!StringUtils.isEmpty(page)) {
			pageInfo.setPage(page);
		}
		if(!StringUtils.isEmpty(limit)) {
			pageInfo.setRows(limit);
		}
		// 查询条件初始化
		User condtion 				  = new User();
 		condtion			.setUsername(userName);
 		condtion			.setNickName(nickName);
 		condtion		  .setUserPhone(userPhone);
 		condtion		.setSchoolName(schoolName);
 		condtion.setSchoolProvince(schoolProvince);
		pageInfo		   .setCondtion(condtion );
		
		
		// 处理业务并转化成layui所需前端
		BaseResponse pageUser = iuser.pageUser(pageInfo);
		LayuiResponse layuiResponse = LayuiResponse.initByBaseResponse(pageUser, (PageInfo<User>) pageUser.getData());
		return layuiResponse;
		
	}
	
	
	/**
	 * 	用户详情查看
	 * @param user 传入userId
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@RequestMapping("look")
	public BaseResponse lookUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		return iuser.lookUser(user);
	}
	
	
	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */	 

	@RequestMapping("add")
	public BaseResponse addUser(HttpServletRequest request, @RequestBody User user) {
		return iuser.addUser(request, user);

	}
	
	
	/**
	 * 	更新用户;不可更新密码，只有用户自己可以更新密码
	 * 
	 * @param user 传入用户|角色位非必填选项
	 * @return
	 */
	@RequestMapping("update")
	public BaseResponse updateUser(HttpServletRequest request, @RequestBody User user) {
		return iuser.updateUser(request, user);
	}
	
	
	/**
	 * 	用户删除
	 * @param user 传入userId
	 * @return
	 */
	@RequestMapping("delete")
	public BaseResponse deleteUser(@RequestBody User user) {
		return iuser.deleteUser(user);

	}

	
	


}
