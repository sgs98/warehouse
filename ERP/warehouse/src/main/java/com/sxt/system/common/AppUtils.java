package com.sxt.system.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * 当实现了ApplicationContextAware这后，  IOC容器初始化时会回调setApplicationContext把IOC容器对象转到里面
 *
 */

@Component
public class AppUtils  implements ApplicationContextAware {

    private static ApplicationContext context;


    public static ApplicationContext getContext(){
        return context;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }
}
