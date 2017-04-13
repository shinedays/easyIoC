package com.myspring.config;

/**
 * Created by bupt on 4/12/17.
 */
public class Property {
    String name = "";
    private String value = "";
    private String ref = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
