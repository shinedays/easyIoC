package com.myspring.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Created by bupt on 4/12/17.
 */
public class BeanUtil {

    public static Method getSetterMethod(Object object,String name){
        Method setMethod;
        try {
            System.out.println("method name " + name);
            PropertyDescriptor pd = new PropertyDescriptor(name,object.getClass());
            setMethod = pd.getWriteMethod();
        } catch (IntrospectionException e) {
            e.printStackTrace();
            throw  new RuntimeException("no this method" + name);
        }
        return setMethod;
    }
}
