package club.renxl.www.management.school.interceptors;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 跨域请求
 * @author renxl
 * @date 
 *
 */
@Component
public class AccessInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));// 跨域请求客户端限制
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");//指定请求方式
		response.setHeader("Access-Control-Max-Age", "1728000");//miao 设置无需预请求的时间间隔
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Request-Headers", "x-requested-with");//ajax
		response.setHeader("Access-Control-Request-Method", "GET,POST");
		response.setHeader("XDomainRequestAllowed", "1");
		response.setHeader("Access-Control-Expose-Headers", "REDIRECT,CONTENTPATH");// 暴露响应头被js访问权限

		return true;
	}

}
