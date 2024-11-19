package com.xncoder.devtalker;

import java.io.IOException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AccessCorsFilter implements Filter {
//	private String clientAppURL = "http://localhost:4200/*";
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response2 = (HttpServletResponse) response;
		HttpServletRequest request2 = (HttpServletRequest) request;
		
		String originHeader = request2.getHeader("origin");
		response2.setHeader("Access-Control-Allow-Origin", originHeader);
		response2.setHeader("Access-Control-Allow-Methods", "POST, GET ,PUT, DELETE, OPTIONS");
		response2.setHeader("Access-Control-Max-Age", "3600");
		response2.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		response2.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.setContentType("application/json");

		response2.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-PINGOTHER, X-Requested-With, Accept, X-Custom-Header");

		
		if("OPTIONS".equalsIgnoreCase(request2.getMethod())) {
			response2.setStatus(HttpServletResponse.SC_OK);
		}else {
			chain.doFilter(request, response);
		}
	}
}
