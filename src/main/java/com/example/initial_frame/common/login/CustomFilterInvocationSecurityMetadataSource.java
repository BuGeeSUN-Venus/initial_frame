package com.example.initial_frame.common.login;

import com.example.initial_frame.bean.login.Resources;
import com.example.initial_frame.bean.login.Role;
import com.example.initial_frame.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author dw
 * @ClassName CustomFilterInvocationSecurityMetadataSource
 * @Description 要实现动态配置权限，首先需要自定义 FilterInvocationSecurityMetadataSource：
 * 自定义 FilterInvocationSecurityMetadataSource 主要实现该接口中的 getAttributes 方法，该方法用来确定一个请求需要哪些角色。
 * @Date 2020/4/15 11:36
 * @Version 1.0
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource  implements FilterInvocationSecurityMetadataSource {

    // 创建一个AntPathMatcher，主要用来实现ant风格的URL匹配。
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    LoginService loginService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 从参数中提取出当前请求的URL
        String requestUrl = ((FilterInvocation) object).getRequestUrl();

        // 从数据库中获取所有的资源信息，即本案例中的Resources表以及Resources所对应的role
        // 在真实项目环境中，开发者可以将资源信息缓存在Redis或者其他缓存数据库中。
        List<Resources> allResources = loginService.getAllResources();

        // 遍历资源信息，遍历过程中获取当前请求的URL所需要的角色信息并返回。
        for (Resources resource : allResources) {
            if (antPathMatcher.match(resource.getPattern(), requestUrl)) {
                List<Role> roles = resource.getRoles();
                if(!CollectionUtils.isEmpty(roles)){
                    List<ConfigAttribute> allRoleNames = roles.stream()
                            .map(role -> new SecurityConfig(role.getName().trim()))
                            .collect(Collectors.toList());
                    return allRoleNames;
                }
            }
        }
        // 如果当前请求的URL在资源表中不存在相应的模式，就假设该请求登录后即可访问，即直接返回 ROLE_LOGIN.
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    // 该方法用来返回所有定义好的权限资源，Spring Security在启动时会校验相关配置是否正确。
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        // 如果不需要校验，那么该方法直接返回null即可。
        return null;
    }

    // supports方法返回类对象是否支持校验。
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

}
