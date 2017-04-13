package com.myspring.test;

import com.myspring.bean.Address;
import com.myspring.bean.User;
import com.myspring.core.BeanFactory;
import com.myspring.utils.ClassPathXmlApplicationContext;

/**
 * Created by bupt on 4/12/17.
 */
public class Test {
    public static void main(String[] args) {
        testIoc();
    }

    public static void testIoc(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("/home/bupt/code/java/iocdemo/src/main/resources/ApplicationContext.xml");
        User user = (User)beanFactory.getBean("user");
        System.out.println(user);
        Address address = (Address)beanFactory.getBean("address");
        System.out.println(address);
    }
}
