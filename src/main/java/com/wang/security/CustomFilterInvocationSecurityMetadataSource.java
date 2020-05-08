package com.wang.security;

import com.wang.dao.MenuMapper;
import com.wang.entity.Menu;
import com.wang.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 自定义筛选器调用安全元数据
 */
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    MenuMapper menuMapper;

    /**
     * 参数为一个FilterInvocation,可以从中提取出当前请求的url
     * 返回值为 Collection<ConfigAttribute> 表示当前请求当前url所需的角色
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
      //  System.out.println("**************************************************************************************获取请求************************************************************************");
        /**
         * 当前请求的url
         */
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
     //   System.out.println("当前请求的路径为requestUrl——————>"+requestUrl);
        /**
         * 查询所有url
         */
        List<Menu> allMenus = menuMapper.getAllMenus();
        //System.out.println("从数据库里面查出所有路径进行比对——————>"+allMenus);
        /**
         * 遍历资源信息，获取当前URL所需要的角色并赶返回，如果不存在则返回登录
         */
        for (Menu menu : allMenus){
            if (antPathMatcher.match(menu.getPattern(),requestUrl)){
         //       System.out.println("进行路径与其可访问路径的比对：可访问路径为——————>"+menu.getPattern()+"请求路径为——————>"+requestUrl);
                List<Role> roles = menu.getRoles();
         //       System.out.println("我的所有权限为roles——————>"+roles+"大小为——————>"+roles.size());
                String[] roleArr = new String[roles.size()];
                for (int i = 0; i<roleArr.length; i++ ){
                    roleArr[i] = roles.get(i).getName();
                }
        //        System.out.println("把我所有的权限放到一个集合里面roleArr——————>"+roleArr);
                return SecurityConfig.createList(roleArr);
            }
        }
        return SecurityConfig.createList("ROLE_ANONYMOUS");
    }

    /**
     * 用来返回所有定义好的权限资源，如果不需要校正返回null即可
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 该方法返回类对象是否需要校验
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
