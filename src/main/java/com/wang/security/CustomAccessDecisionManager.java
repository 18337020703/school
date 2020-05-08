package com.wang.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAccessDecisionManager implements AccessDecisionManager {
    /**
     * 判断当前登录的用户是否具备当前请求URL所需要的角色信息，如果不具备就抛出异常
     * @param authentication   包含当前登录用户的信息
     * @param o                是一个FilterInvocation对象，可以获取当前请求对象
     * @param collection       为Filter Invocation Security metadata source 中的 getAttribus方法的返回值 即当前请求所需要的角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        /**
         * 对信息进行比对，如果需要的角色是ROLE_LOGIN则表示登录后可以访问
         */
      //  System.out.println("-------------------------------------------------------------信息对比------------------------------------------------------------------");
      //  System.out.println("角色信息对比中...");
      //  System.out.println("当前请求对象-o——————>"+o);
      //  System.out.println("当前请求需要的角色为-collection——————>"+collection);
        Collection<? extends GrantedAuthority> auths =authentication.getAuthorities();
       // System.out.println("我的角色auths——————>"+auths);
        for (ConfigAttribute configAttribute : collection){
            if ("ROLE_ANONYMOUS".equals(configAttribute) && authentication instanceof UsernamePasswordAuthenticationToken) {
                return;
            }
            for (GrantedAuthority authority : auths){
                if (configAttribute.getAttribute().equals(authority.getAuthority())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
