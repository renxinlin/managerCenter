package club.renxl.www.management.school.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.renxl.www.management.util.SessionUtil;

import club.renxl.www.management.school.user.dao.domain.User;

/**
 * 登录拦截器
 * 
 * @author renxl
 * @date 2018/10/15
 *
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Value("${manager.school.redictUrl}")
	private String redictUrl;

	// 对于请求是ajax请求重定向问题的处理方法
	public void reDirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理

		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
			// 告诉ajax我是重定向
			response.setHeader("REDIRECT", "REDIRECT");
			// 告诉ajax我重定向的路径
			response.setHeader("CONTENTPATH",
					"http://127.0.0.1:8020/management_weibo/sys/login.html?__hbt=1539566342731");
		} else {
			response.setHeader("REDIRECT", "REDIRECT");
			// 告诉ajax我重定向的路径
			response.setHeader("CONTENTPATH",
					"http://127.0.0.1:8020/management_weibo/sys/login.html?__hbt=1539566342731");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			 
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean notLogin = true;
		if(request.getMethod().equals("OPTIONS")) {
			return true;
		}
		try {
			User user = SessionUtil.getUser(request);
			if (!StringUtils.isEmpty(user)) {
				notLogin = !notLogin;
			}

			if (notLogin) {
				// TODO 添加到配置服务器
				reDirect(request, response);
				// response.sendRedirect(redictUrl);
				System.out.println(redictUrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean result = !notLogin;
		return result;
	}

}
