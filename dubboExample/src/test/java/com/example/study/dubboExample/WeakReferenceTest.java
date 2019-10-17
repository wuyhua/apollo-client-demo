package com.example.study.dubboExample;

import com.example.study.dubboExample.dto.Boy;

import java.lang.ref.WeakReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/9/25 17:47
 **/
public class WeakReferenceTest{

    static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        int num = 5;

        for (int i = 0; i < num; i++) {
            Thread thread = new Thread(new LockTask(lock));
            thread.setName("lockThread-" + i);
            thread.start();
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
        }

    }

    static class LockTask implements Runnable{

        private ReentrantLock lock;

        public LockTask(ReentrantLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始获取锁");
            lock.lock();
            try {
                //模拟不释放
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {

            }finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "释放锁");
        }
    }


}
