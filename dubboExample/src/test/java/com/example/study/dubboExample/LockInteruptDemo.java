package com.example.study.dubboExample;

import org.apache.tomcat.jni.Time;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/9/18 16:48
 **/
public class LockInteruptDemo {

    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        lock.tryLock(10,TimeUnit.SECONDS);
        lock.lock();
        InteruptThread interuptThread = new InteruptThread();
        Thread thread = new Thread(interuptThread, "interuptThread");
        thread.start();
        TimeUnit.SECONDS.sleep(10);
//        thread.interrupt();
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + "结束");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    static class InteruptThread implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "开始获取锁");
                lock.lock();
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + "被中断");
                e.printStackTrace();
                return;
            }
            try {
                //模拟执行业务代码
                System.out.println(Thread.currentThread().getName() + "获取锁成功");
            }catch (Exception e){

            }finally {
                System.out.println(Thread.currentThread().getName() + "释放锁");
                lock.unlock();
            }
        }
    }
}
