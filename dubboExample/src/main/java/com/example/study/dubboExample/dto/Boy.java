package com.example.study.dubboExample.dto;

import java.io.Serializable;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/9/10 8:37
 **/
public class Boy implements Serializable {

    private static final long serialVersionUID = 511657882537651217L;

    private String name;

    private Integer age;

    private String hoby;

    public Boy() {}

    public Boy(String name, Integer age, String hoby) {
        this.name = name;
        this.age = age;
        this.hoby = hoby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHoby() {
        return hoby;
    }

    public void setHoby(String hoby) {
        this.hoby = hoby;
    }
}
