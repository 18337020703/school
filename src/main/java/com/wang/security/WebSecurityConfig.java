package com.wang.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.dao.UserMapper;
import com.wang.entity.Merchants;
import com.wang.entity.Session;
import com.wang.entity.Student;
import com.wang.entity.User;
import com.wang.service.MerchantsService;
import com.wang.service.StudentService;
import com.wang.service.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserSecurity userSecurity;
    @Autowired
    UserMapper userMapper;
    @Autowired
    StudentService studentService;
    @Autowired
    MerchantsService merchantsService;

    /**
     * 输入密码进行MD5加密
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 将CustomFilterInvocationSecurityMetadataSource()装配Bean
     * @return
     */
    @Bean
    CustomFilterInvocationSecurityMetadataSource cfisms(){
        return new CustomFilterInvocationSecurityMetadataSource();
    }

    /**
     * 将CustomAccessDecisionManager() 装配Bean
     * @return
     */
    @Bean
    CustomAccessDecisionManager cadm(){
        return new CustomAccessDecisionManager();
    }
    /**
     * 关于用户的相关信息操作
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userSecurity);
    }

    /**
     * 登录结果以及权限处理
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    /**
                     * 将装配好的 cfisms() 和 cadm() 自定义的方法放到securityConfig里
                     * @param o
                     * @param <O>
                     * @return
                     */
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                //        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$安全设置￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥");
                        o.setSecurityMetadataSource(cfisms());
                        o.setAccessDecisionManager(cadm());
                        return o;
                    }
                })
                .and()
                //.sessionManagement()
                //.invalidSessionUrl("/login.html")
                //.and()
                .formLogin()
                .loginPage("/public/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                                        HttpServletResponse httpServletResponse,
                                                        Authentication authentication) throws IOException, ServletException {


                    //    System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM登录成功啦！！！MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
                        /**
                         * 获取用户的登陆信息
                         */
                        User principal = (User) authentication.getPrincipal();
                        /**
                         * 根据用户名查询当前用户的所有信息
                         */
                       //Student user = studentService.findAllByName(principal.getUsername());
                        Student user = studentService.findById(principal.getId());
                        Merchants merchants = merchantsService.findById(principal.getId());
                        if (user !=null){
                            httpServletRequest.getSession().setAttribute("student",user);
                            httpServletResponse.sendRedirect("/school/page/index.html");
                        }else {
                            httpServletRequest.getSession().setAttribute("merchants",merchants);
                            httpServletResponse.sendRedirect("/school/merchants/merchantsIndex.html");
                        }
                        /**
                         * 把登录信息用redis存放到session里
                         */

                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        httpServletResponse.setStatus(200);
                        Map<String,Object> map = new HashMap<>();
                        map.put("status",200);
                        map.put("msg",principal);
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                                        HttpServletResponse httpServletResponse,
                                                        AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        httpServletResponse.setStatus(401);
                        Map<String,Object> map = new HashMap<>();
                        map.put("status",401);
                        if(e instanceof LockedException){
                            httpServletResponse.sendRedirect("/school/error/401.html");
                            map.put("msg","账户被锁定，请重新登录呐");
                        }else if (e instanceof BadCredentialsException){
                            map.put("msg","密码错误，请重新登录呐");
                        }else if (e instanceof DisabledException){
                            map.put("msg","账号被禁用，登录失败呐");
                        }else if (e instanceof AccountExpiredException){
                            map.put("msg","账号已过期，登录失败呐");
                        }else if (e instanceof CredentialsExpiredException){
                            map.put("msg","密码过期，登录失败呐");
                        }else{
                            map.put("msg","登录失败");
                        }
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/public/login.html")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        System.out.println("注销重定向");
                        httpServletResponse.sendRedirect("/school/public/login.html");
                        System.out.println("注销成功");
                    }
                })
                .and()
                .headers().frameOptions().disable()//放行iframe 解决 X-Frame-Options 设为“DENY”，不允许载入任何框架
                .and()
                .csrf().disable();
    }

    /**
     * 静态资源的放行
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/public/**");
        web.ignoring().antMatchers("/res/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/img/**");
        web.ignoring().antMatchers("/upload/**");
        web.ignoring().antMatchers("/admin/**");
    }
}
