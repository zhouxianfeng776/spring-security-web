package com.zhou.demoweb.config;

import com.zhou.demoweb.config.test.AppFilterInvocationSecurityMetadataSource;
import com.zhou.demoweb.config.test.LogFilter;
import com.zhou.demoweb.config.test.RoleBasedVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SecurityConfig
 * Author:   Administrator
 * Date:     2020/3/8 19:12
 * Description:
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().successForwardUrl("/login/success")// 表单登录。跳转到security默认的登录表单页
                // http.httpBasic() //basic登录
                .and()
                .authorizeRequests() // 对请求授权
                .antMatchers("/noAuth").permitAll() //允许所有人访问/noAuth
                .accessDecisionManager(accessDecisionManager())
                .anyRequest() // 任何请求
                .authenticated()// 需要身份认证
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(
                            O fsi) {
                        fsi.setSecurityMetadataSource(mySecurityMetadataSource(fsi.getSecurityMetadataSource()));
                        return fsi;
                    }
                })
                .and().addFilterBefore(new LogFilter(), UsernamePasswordAuthenticationFilter.class)
                .rememberMe().and().sessionManagement().maximumSessions(1)
                //启用前后端分离时开启即可
                //.exceptionHandling().authenticationEntryPoint(new CustAuthenticationEntryPoint())
        ;
    }


//    @Bean
//    @Override
//    public UserDetailsService userDetailsService()  {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("1").password("1").roles("USER").build());
//        return manager;
//    }

    @Bean
    public AppFilterInvocationSecurityMetadataSource mySecurityMetadataSource(FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource) {
        AppFilterInvocationSecurityMetadataSource securityMetadataSource = new AppFilterInvocationSecurityMetadataSource(filterInvocationSecurityMetadataSource);
        return securityMetadataSource;
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                new WebExpressionVoter(),
                new RoleVoter(),
                new RoleBasedVoter());
        return new UnanimousBased(decisionVoters);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //PasswordEncoderFactories.createDelegatingPasswordEncoder()
        return new BCryptPasswordEncoder(); //指定4-31位的长度
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        //默认的数据脚本 org/springframework/security/core/userdetails/jdbc/users.ddl
        //auth.jdbcAuthentication().withDefaultSchema();

    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(14);
        long begin = System.currentTimeMillis();
        String encode = bCryptPasswordEncoder.encode("1");
        System.out.println(encode);
        System.out.println(System.currentTimeMillis()-begin);
    }
}