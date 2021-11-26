package com.kutlu.hotel.util;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private TokenManager tokenManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		String userName = null;//username dedim ama aslında buraya varsa maili atıyor daha sonra bunları düzenleyeceğim.
		String token = null;
		if (authHeader != null && authHeader.contains("Bearer")) {
			token = authHeader.substring(7);
			try {
				userName = tokenManager.getUserFromToken(token);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		if (userName != null && token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			if(tokenManager.tokenValidate(token)) {
				//2.parametre credentials 3.parametre authority
				UsernamePasswordAuthenticationToken upassToken = new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
				upassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upassToken);
			}
			
		}
		filterChain.doFilter(request, response);
	}

}
