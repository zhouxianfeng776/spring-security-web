package com.zhou.demoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.DelegatingMethodSecurityMetadataSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: LoginSuccessController
 * Author:   Administrator
 * Date:     2020/3/8 18:51
 * Description:
 */
@RestController()
@RequestMapping("/login")
public class LoginSuccessController {

    @RequestMapping(value = "/success",method = {RequestMethod.GET,RequestMethod.POST})
    public String getMenuAndUserInfo(){
        return "成功返回菜单和用户信息";
    }

    /**
     * PreAuthorize 里面权限控制的多种写法
     * 1.@PreAuthorize("hasRole('ADMIN')")，@PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
     * 2.@PreAuthorize("#n == authentication.name")
     * Contact findContactByName(@Param("n") String name);
     *3.@PreAuthorize("#contact.name == authentication.name")
     * public void doSomething(Contact contact);
     * 4.@PreAuthorize("hasAuthority('ROLE_TELLER')")
     * @return
     */
    //@PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/test")
    public String testAuthory() throws Exception {
        return "测试权限";
    }

    //@PreAuthorize("hasRole('USER1')")
    @GetMapping(value = "/test1")
    public String testAuthory1(){
        return "测试权限";
    }

}