package com.wang.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * 创建一个工具类，来拿到整个工厂对象，一般在自定义类中使用工厂中的bean
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    /**
     * 获取工厂对象
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }
    //根据id获取bean
    public static Object getBean(String id){
        return context.getBean(id);
    }
    //根据类型获取bean
    public static Object getBean(Class clazz){
        return context.getBean(clazz);
    }
    //根据id和类型获取bean
    public static Object getBean(String id,Class clazz){
        return context.getBean(id,clazz);
    }
}
