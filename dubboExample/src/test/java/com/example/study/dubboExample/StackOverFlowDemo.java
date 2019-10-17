package com.example.study.dubboExample;

import com.example.study.dubboExample.dto.Boy;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/9/18 22:19
 **/
public class StackOverFlowDemo {


    public static void main(String[] args) {
//    JVM配置    -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
        AtomicInteger integer = new AtomicInteger(0);
        List<String> list = new ArrayList<>();
        try {
            while (true){
                list.add(String.valueOf(integer).intern());
            }
        }catch (Throwable e){
            e.printStackTrace();
            throw e;
        }

    }

}
