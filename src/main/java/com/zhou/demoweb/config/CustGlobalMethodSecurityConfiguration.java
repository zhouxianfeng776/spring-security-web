package com.zhou.demoweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Jsr250MethodSecurityMetadataSource;
import org.springframework.security.access.annotation.SecuredAnnotationSecurityMetadataSource;
import org.springframework.security.access.expression.method.ExpressionBasedAnnotationAttributeFactory;
import org.springframework.security.access.method.DelegatingMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.access.prepost.PrePostAnnotationSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.core.GrantedAuthorityDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: CustGlobalMethodSecurityConfiguration
 * Author:   Administrator
 * Date:     2020/3/21 13:21
 * Description:
 */
//@Configuration
public class CustGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {




    /*@Bean
    public MethodSecurityMetadataSource methodSecurityMetadataSource() {
        List<MethodSecurityMetadataSource> sources = new ArrayList<>();
        ExpressionBasedAnnotationAttributeFactory attributeFactory = new ExpressionBasedAnnotationAttributeFactory(
                getExpressionHandler());
        MethodSecurityMetadataSource customMethodSecurityMetadataSource = customMethodSecurityMetadataSource();
        if (customMethodSecurityMetadataSource != null) {
            sources.add(customMethodSecurityMetadataSource);
        }

        boolean hasCustom = customMethodSecurityMetadataSource != null;
        boolean isPrePostEnabled = prePostEnabled();
        boolean isSecuredEnabled = securedEnabled();
        boolean isJsr250Enabled = jsr250Enabled();

        if (!isPrePostEnabled && !isSecuredEnabled && !isJsr250Enabled && !hasCustom) {
            throw new IllegalStateException("In the composition of all global method configuration, " +
                    "no annotation support was actually activated");
        }

        if (isPrePostEnabled) {
            sources.add(new PrePostAnnotationSecurityMetadataSource(attributeFactory));
        }
        if (isSecuredEnabled) {
            sources.add(new SecuredAnnotationSecurityMetadataSource());
        }
        if (isJsr250Enabled) {
            GrantedAuthorityDefaults grantedAuthorityDefaults =
                    getSingleBeanOrNull(GrantedAuthorityDefaults.class);
            Jsr250MethodSecurityMetadataSource jsr250MethodSecurityMetadataSource = this.context.getBean(Jsr250MethodSecurityMetadataSource.class);
            if (grantedAuthorityDefaults != null) {
                jsr250MethodSecurityMetadataSource.setDefaultRolePrefix(
                        grantedAuthorityDefaults.getRolePrefix());
            }
            sources.add(jsr250MethodSecurityMetadataSource);
        }
        return new DelegatingMethodSecurityMetadataSource(sources);
    }*/
}