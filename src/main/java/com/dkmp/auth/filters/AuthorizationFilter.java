package com.dkmp.auth.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {
	
	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession();

		String requestURI = httpRequest.getRequestURI();
		if (requestURI.contains("/login.xhtml") && (httpSession != null && httpSession.getAttribute("username") != null))
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/home.xhtml");
		else if (requestURI.contains("/login.xhtml")
				|| (httpSession != null && httpSession.getAttribute("username") != null)
				|| requestURI.contains("/public/") || requestURI.contains("javax.faces.resource"))
			chain.doFilter(request, response);
		else
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.xhtml");
	}

	@Override
	public void destroy() {
	}
}
