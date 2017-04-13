package com.myspring.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bupt on 4/12/17.
 */
public class XmlConfig {
    public static Map<String, Bean> getConfig(String path) {
        Map<String, Bean> configMap = new HashMap<String, Bean>();
        Document doc = null;
        SAXReader reader = new SAXReader();
        File file = new File(path);
        try {
            doc = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("check your xml parh");
        }

        String xpath = "//bean";
        List<Element> list = doc.selectNodes(xpath);
        if (list != null) {
            for (Element beanEle : list) {
                Bean bean = new Bean();
                String id = beanEle.attributeValue("id");
                String className = beanEle.attributeValue("class");
                bean.setId(id);
                bean.setClassName(className);
                System.out.println("id " + id);
                System.out.println("classname " + className);

                List<Element> proList = beanEle.elements("property");
                if (proList != null) {
                    for (Element proEle : proList) {
                        Property property = new Property();
                        String propName = proEle.attributeValue("name");
                        String propValue = proEle.attributeValue("value");
                        String propRef = proEle.attributeValue("ref");

                        property.setName(propName);
                        property.setValue(propValue);
                        property.setRef(propRef);

                        bean.getProperties().add(property);
                    }
                }
                if (configMap.containsKey(id)) {
                    throw new RuntimeException("bean id repeat!" + id);
                }
                configMap.put(id, bean);

            }
        }
        return configMap;
    }
}
