package com.doMain;

import com.util.BeanFactory;

public class DemoMain {
    public static void main(String[] args) {
        BeanFactory beanFactory = BeanFactory.getInstance();
        String a = (String)beanFactory.getBean("constString");
        System.err.println(a);
    }
}
