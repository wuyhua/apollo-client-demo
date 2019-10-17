package com.example.study.dubboExample;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/9/18 11:04
 **/
public class ShutDemo {

    //当前正在运行的任务数量
    private final static AtomicInteger runningJobNum = new AtomicInteger(0);

    static {
        //此钩子判断让所有提现线程任务执行完毕
        //JVM会等addShutdownHook中的Thread执行结束后才关闭
        Runtime.getRuntime().addShutdownHook(new MonitorMideaPayWallteTask());
        //守护线程是为其他线程的运行提供便利的线程。守护线程不会阻止程序的终止，守护在JVM停止一定会执行，但不保证被执行完
        //如果没有其他用户线程在运行，那么它随JVM停止，这个线程不一定会执行完毕
        //与钩子对比：钩子线程执行完毕之后，才会JVM停止；
        //执行顺序：JVM即将停止，执行守护线程，同时也执行钩子线程，等待钩子线程执行完毕，JVM停止
        // 守护线程 和 钩子线程 ，这两者之间执行没有必然联系
//        Thread monitorTask = new MonitorMideaPayWallteTask();
//        monitorTask.setDaemon(true);
//        monitorTask.start();
    }


    public static void main(String[] args) {

        for (int i = 0; i<10 ; i++){
            new Thread(()->{
                try {
                    int count = runningJobNum.incrementAndGet();
                    if (count%9==0){
                        int o = 1/0;
                    }else {
                        Thread.sleep(count*500);
                    }
                }catch (Exception e){
                    System.out.println(Thread.currentThread().getName() + "发生异常");
                }finally {
                    runningJobNum.decrementAndGet();
                }
            },"thread" + i).start();

        }
        System.out.println("## 任务数量：" + runningJobNum.intValue());
    }

    static class MonitorMideaPayWallteTask extends Thread {
        @Override
        public void run() {
            try {
                while (true){
                    if (runningJobNum.intValue() == 0){
                        System.out.println("## 已经没有执行中的提现请求任务，可以关闭系统");
                        break;
                    }else {
                        System.out.println("## 还有提现请求任务正在运行，不可关闭系统!!剩余任务数量：{}" + runningJobNum.intValue());
                        Thread.sleep(5000);
                    }
                }
            } catch (Throwable e) {
                System.out.println("## Something goes wrong when stopping system :" + e);
            } finally {
                System.out.println("系统安全关闭.");
            }
        }
    }
}
