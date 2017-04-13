package com.myspring.utils;

import com.myspring.config.Bean;
import com.myspring.config.Property;
import com.myspring.config.XmlConfig;
import com.myspring.core.BeanFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by bupt on 4/12/17.
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Object> ioc;
    private Map<String,Bean> config;


    public ClassPathXmlApplicationContext(String path) {
        ioc = new HashMap<String,Object>();
        config = XmlConfig.getConfig(path);
        if(config != null){
            for(Entry<String,Bean> entry:config.entrySet()){
                String beanId = entry.getKey();
                Bean bean = entry.getValue();

                Object object = createBean(bean);
                ioc.put(beanId,object);
            }
        }
    }

    private Object createBean(Bean bean){
        String beanId = bean.getId();
        String className = bean.getClassName();
        Class c = null;
        Object object = null;

        try {
            c = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("inlegal classname" + className);
        }

        try {
            object = c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("lack no parameter constructor");
        }

        if(bean.getProperties() != null){
            for(Property property:bean.getProperties()){
                if(property.getValue() != null){
                    Method setMethod = BeanUtil.getSetterMethod(object, property.getName());
                    try {
                        setMethod.invoke(object,property.getValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if(property.getRef() != null){
                    Method setMethod = BeanUtil.getSetterMethod(object, property.getName());
                    Object obj = ioc.get(property.getRef());
                    if(obj == null)
                        throw new RuntimeException("cannot find object" + property.getRef());
                    else {
                        try {
                            setMethod.invoke(object,obj);
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RuntimeException("inlegal property!");
                        }
                    }
                }
            }
        }

        return object;
    }

    public Object getBean(String beanName) {
        return ioc.get(beanName);
    }
}
