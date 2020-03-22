package com.zhou.demoweb.config.test;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: CustAuthenticationEntryPoint
 * Author:   Administrator
 * Date:     2020/3/21 22:27
 * Description:
 */
public class CustAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("text/plain; charset=utf-8");
        response.getWriter().write("ssss认证失败。。");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
    /**
     * 		if (isLoginUrlRequest(request) || loginError || logoutSuccess) {
     * 			String loginPageHtml = generateLoginPageHtml(request, loginError,
     * 					logoutSuccess);
     * 			response.setContentType("text/html;charset=UTF-8");
     * 			response.setContentLength(loginPageHtml.getBytes(StandardCharsets.UTF_8).length);
     * 			response.getWriter().write(loginPageHtml);
     *
     * 			return;
     *                }
     */
}