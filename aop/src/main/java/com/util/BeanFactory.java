package com.util;

import com.config.SpringConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanFactory {
    @Autowired
    private static ApplicationContext applicationContext;
    private static BeanFactory _instanse;

    private BeanFactory() {
        BeanFactory.applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    }

    public synchronized static BeanFactory getInstance() {
        if(_instanse == null) {
            _instanse = new BeanFactory();
        }
        return _instanse;
    }
    public Object getBean(String name) {
        return BeanFactory.applicationContext.getBean(name);
    }

}
