/*package com.example.demo.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

///* 表示全部拦截
@WebFilter(filterName = "Filter", urlPatterns = "/*")
public class Filter implements javax.servlet.Filter {

	// 标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
	// String NO_LOGIN = "您还未登录";
	// 不需要登录就可以访问的路径(比如:注册登录等)
	String[] includeUrls = new String[] { "/login", ".js", ".css", "/me" };

	// 初始化调用的
	// 当服务器 被启动的时候，调用
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter生成！");
      
	}

	// 拦截的方法
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession(false);
		String uri = request.getRequestURI();

		System.out.println("filter url:" + uri);
		// 是否需要过滤
		boolean needFilter = isNeedFilter(uri);
		if (!needFilter) {
			// 不需要过滤直接传给下一个过滤器
			filterChain.doFilter(servletRequest, servletResponse);
			System.out.println("不需要过滤：" + uri);
		} else {
			// 需要过滤器
			// session中包含user对象,则是登录状态
			if (session != null && session.getAttribute("realname") != null) {
				System.out.println("realname:" + session.getAttribute("realname"));
				filterChain.doFilter(request, response);
			} else {
				// String requestType = request.getHeader("X-Requested-With");
				// 判断是否是ajax请求
//                if(requestType!=null && "XMLHttpRequest".equals(requestType)){
//                    response.getWriter().write(this.NO_LOGIN);
//                }else{
				// 重定向到登录页(需要在static文件夹下建立此html文件)
				// response.sendRedirect(request.getContextPath()+"login");
				response.sendRedirect("login");
				// System.out.println("！！！");
				// }
				// return;
			}
		}

	}

	public boolean isNeedFilter(String uri) {

		for (String includeUrl : this.includeUrls) {
			if (uri.contains(includeUrl)) {
				return false;
			}
		}

		return true;
	}

	// 销毁时候调用的方法
	@Override
	public void destroy() {
		
		System.out.println("Filter销毁！");

	}

}
*/