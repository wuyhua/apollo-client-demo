package com.example.study.dubboExample.controller;

import com.example.study.dubboExample.DESUtils;
import com.example.study.dubboExample.dto.Boy;
import com.google.common.collect.Lists;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/9/9 8:38
 **/
@RestController
@RequestMapping(value = "/thread")
public class ThreadController {

    private Object deadLockA = new Object();
    private Object deadLockB = new Object();
    private static ConcurrentHashMap<Integer, Boy> boyMap = new ConcurrentHashMap<Integer, Boy>();
    private static AtomicInteger count = new AtomicInteger(0);

    private volatile boolean loopflag = false;

    @RequestMapping(value = "/deadlock")
    public String deadlock() {
        try {
            Thread threadA = new Thread(() -> {
                try {
                    doDeadLockA();
                } catch (Exception e) {
                }
            });
            threadA.setName("deadlock-threadA");
            threadA.start();

            Thread threadB = new Thread(() -> {
                try {
                    doDeadLockB();
                } catch (Exception e) {
                }
            });
            threadB.setName("deadlock-threadB");
            threadB.start();
        } catch (Exception e) {
        }
        return "SUCCESS";
    }


    @RequestMapping(value = "/loop/start")
    public String loopstart() {
        CountDownLatch latch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "线程进入死循环");
            while (!loopflag) {
                Integer i = (Integer) count.getAndIncrement();
                boyMap.put(i, new Boy("boy" + i, i, "hoby" + i));
            }
            System.out.println(Thread.currentThread().getName() + "线程退出死循环");
            latch.countDown();
        });
        thread.setName("loopstart-thread");
        thread.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
        }
        System.out.println("当前BoyMap的数量" + boyMap.size());
        //重置
        loopflag = false;
        boyMap.clear();
        return "SUCCESS";
    }

    @RequestMapping(value = "/loop/stop")
    public String loopstop() {
        loopflag = true;
        return "SUCCESS";
    }


    private void doDeadLockA() throws Exception {
        synchronized (deadLockA) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "获得A锁准备获取B锁");
            synchronized (deadLockB) {
                System.out.println(Thread.currentThread().getName() + "获得A锁/B锁成功");
            }
        }
    }

    private void doDeadLockB() throws Exception {
        synchronized (deadLockB) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "获得B锁准备获取A锁");
            synchronized (deadLockA) {
                System.out.println(Thread.currentThread().getName() + "获得B锁/A锁成功");
            }
        }
    }


    public static void main(String[] args) {

//        String name = DESUtils.getDecryptString("kanrpM0tztw=");
        String name = DESUtils.getEncryptString("app");
        System.out.println("结果：" + name);

        String pass = DESUtils.getDecryptString("jtTZWKYNyQUe88trcoNIIg==");
        System.out.println("结果：" + pass);
        List<String> stringList = Lists.newArrayList("aa", "bb", "cc");
        List<String> stringStream = stringList
                .stream()
                .map(DESUtils::getEncryptString)
                .collect(Collectors.toList());
        System.out.println("结果：" + stringStream);
    }

}
