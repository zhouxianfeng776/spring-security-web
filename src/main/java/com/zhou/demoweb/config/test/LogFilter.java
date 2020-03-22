package com.zhou.demoweb.config.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: LogFilter
 * Author:   Administrator
 * Date:     2020/3/22 0:45
 * Description:
 */
@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("开始");
        long begin = System.currentTimeMillis();
        chain.doFilter(request,response);
        log.info("耗时{}毫秒",System.currentTimeMillis()-begin);

    }
}