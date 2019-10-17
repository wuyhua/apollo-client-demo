package com.example.study.dubboExample;


import java.util.concurrent.locks.*;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/9/15 14:25
 **/
public class BlockingQueueDemo {

    private static Lock lock = new ReentrantLock();


    public static void main(String[] args) {

        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                conditionA.await();
                System.out.println(Thread.currentThread().getName() + "继续执行！");
            } catch (InterruptedException e) {
            }finally {
                lock.unlock();
            }
        },"thread-A1").start();

        new Thread(() -> {
            lock.lock();
            try {
                conditionA.await();
                System.out.println(Thread.currentThread().getName() + "继续执行！");
            } catch (InterruptedException e) {
            }finally {
                lock.unlock();
            }
        },"thread-A2").start();

        try { Thread.sleep(100); } catch (InterruptedException e) {}

        new Thread(() -> {
            lock.lock();
            try {
                conditionA.signal();
                System.out.println(Thread.currentThread().getName() + "开始唤醒！");
            } catch (Exception e) {
            }finally {
                lock.unlock();
            }
        },"thread-A-Signal").start();

        try { Thread.sleep(Integer.MAX_VALUE); } catch (InterruptedException e) {}
    }

}
