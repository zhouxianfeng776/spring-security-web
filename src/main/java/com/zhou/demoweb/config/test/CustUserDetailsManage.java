package com.zhou.demoweb.config.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: CustUserDetailsManage
 * Author:   Administrator
 * Date:     2020/3/22 0:05
 * Description:
 */
@Component
@Order
@Slf4j
public class CustUserDetailsManage implements UserDetailsService, InitializingBean {

    @Autowired
    PasswordEncoder passwordEncoder;

    private Map<String,User.UserBuilder> map ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("UserDetails开始");
        long begin = System.currentTimeMillis();
        UserDetails user = map.get(username).build();
        log.info("UserDetails耗时{}毫秒",System.currentTimeMillis()-begin);
        return user;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String password = passwordEncoder.encode("2");
        User.UserBuilder build = User.withUsername("2").password(password).roles("USER1").authorities("ROLE_USER1");
        map=new HashMap<>();
        map.put("2",build);
    }
}