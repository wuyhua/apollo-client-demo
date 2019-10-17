package com.example.study.dubboExample;

import com.example.study.dubboExample.dto.Boy;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.ref.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/9/12 10:23
 **/
public class ReferenceTest extends DubboExampleApplicationTests {

    ThreadLocal<Object> local = new ThreadLocal<>();

    @Test
    public void doTest(){
        Object o = new Object();
        local.set(o);
        SoftReference<Object> soft = new SoftReference<>(o);
        System.gc();
        o = null;
        System.out.println(o);              //输出null
        System.out.println(soft.get());     //java.lang.Object@55a609dd 没有被回收
    }

    @Test
    public void testBa(){
        int count = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count,() -> {
            // cyclicBarrier.await(); 执行 5次 计数之后执行这个方法
            System.out.println("主线程开始执行");
        });

        for (int i = 0; i <count ; i++ ){
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "开始等待");
                    cyclicBarrier.await();
                    //主线程执行完成之后再往下执行
                    System.out.println(Thread.currentThread().getName() + "等待结束，开始执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"threadname"+ i).start();
        }
    }


    @Test
    public void testSemaphore(){
        int count = 5;  //初始化信号量 5个容量
        Semaphore semaphore = new Semaphore(count);

        for ( int i = 0; i <count*2; i++){
            new Thread(()->{
                try {
                    semaphore.acquire(1); //获取一个信号量
                    System.out.println(Thread.currentThread().getName() + " 成功持有信号量");
                    //暂停一会，模拟程序执行时间 1s
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " 释放当前持有");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(1); //释放一个信号量
                }
            },"threadName" + i).start();
        }
        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
    }



}
