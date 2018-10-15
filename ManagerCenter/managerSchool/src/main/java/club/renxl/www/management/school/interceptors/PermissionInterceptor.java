package club.renxl.www.management.school.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.renxl.www.management.util.SessionUtil;

import club.renxl.www.management.school.user.dao.domain.User;

/**
 * 权限拦截器
 * @author renxl
 * @date 2018/10/15
 *
 */

public class PermissionInterceptor  extends HandlerInterceptorAdapter {

	@SuppressWarnings("unused")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			User user = SessionUtil.getUser(request);
			String uri = request.getRequestURI();
			System.out.println(uri);
			if(true) {
				return true;			
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
}
