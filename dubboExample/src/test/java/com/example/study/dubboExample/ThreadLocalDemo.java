package com.example.study.dubboExample;

import com.example.study.dubboExample.dto.Boy;
import com.sun.corba.se.spi.ior.ObjectKey;

import java.lang.ref.WeakReference;

/**
 * @Desc  ThreadLocal 的内存泄漏
 * @Author wuyh
 * @Date 2019/9/19 15:29
 **/
public class ThreadLocalDemo {


    private static ThreadLocal<Boy> threadLocalMain = new ThreadLocal<>();


    public static void main(String[] args) {

        //创建ThreadLocal的类
        ReferenceThread referenceThread = new ReferenceThread();
        //调用，main线程的ThreadLocalMap，就会有Entry<K,V>
        //K指向referenceThread 内部的 subThreadLocal
        //注意这里的subThreadLocal 没有使用static修饰，属于对象的成员变量
        referenceThread.run();
        //释放
        referenceThread = null;
        //GC回收
        //referenceThread 对象被回收，referenceThread内部 subThreadLocal没有引用了
        System.gc();

        Boy boy = new Boy();
        boy.setName("main");
        System.out.println("boy：" + boy);
        System.out.println("threadLocalMain：" + threadLocalMain);

        threadLocalMain.set(boy);

        Thread currentThread = Thread.currentThread();
        currentThread.getName();
        //dubug 进去可以看到
        //main线程的ThreadLocalMap，Entry<K,V>中，还有对象referenceThread内部 subThreadLocal
        //value有值，但是reference已经为null，发生内存泄漏
        threadLocalMain.get();


    }

    static class ReferenceThread {

        private static ThreadLocal<Boy> subThreadLocal = new ThreadLocal<>();

        public void run() {
            Boy boy = new Boy();
            boy.setName("main_ReferenceThread");
            subThreadLocal.set(boy);
            System.out.println(Thread.currentThread().getName() + "，获取结果：" + subThreadLocal + "_" + subThreadLocal.get());
//            subThreadLocal.remove();
        }
    }

}
