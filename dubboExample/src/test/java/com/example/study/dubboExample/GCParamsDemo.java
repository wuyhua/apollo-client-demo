package com.example.study.dubboExample;

import com.example.study.dubboExample.dto.Boy;

/**
 * @Desc
 * @Author wuyh
 * @Date 2019/9/20 9:10
 **/
public class GCParamsDemo {

    //JDK1.7和JDK1.8中，默认的搭配方式
    //(1)、CMS收集器 -- 新生代默认激活UseParNewGC收集器
    //"-XX:+UseConcMarkSweepGC"：指定使用CMS后，会默认使用ParNew作为新生代收集器；
    //"-XX:+UseParNewGC"：强制指定使用ParNew；
    //"-XX:ParallelGCThreads"：指定垃圾收集的线程数量，ParNew默认开启的收集线程与CPU的数量相同；
    //"-XX:CMSInitiatingOccupancyFraction"：70%。设置CMS预留内存空间；
    //当老年代达到70%时，触发CMS垃圾回收
    //这个参数设置之后，可以控制老年代剩下的空间，例如这里是30%，稍微大于等于s0/s1区比较合理。
    //因为并发清除的过程中，假如这个时候s0/s1区满了，进入老年代，这时候老年代剩余空间是可以装下s0/s1的垃圾
    //如果装不下的话，就会发生"Concurrent Mode Failure"失败，
    //进而又一次发生Full GC，并且使用备用的Serial Old收集器，导致系统的临时卡顿，可能引起更多问题

    //(2)、Serial收集器  --老年代激活 Serial Old收集器
    //"-XX:+UseSerialGC"：添加该参数来显式的使用串行垃圾收集器；

    //(3)、Parallel Old收集器 -- 新生代激活 Parallel Scavenge收集器
    //"-XX:+UseParallelOldGC"：指定使用Parallel Old收集器；
    //"-XX:MaxGCPauseMillis"：控制最大垃圾收集停顿时间
    //"-XX:GCTimeRatio"：设置垃圾收集时间占总时间的比率
    //"-XX:+UseAdptiveSizePolicy"：开启这个参数后，就不用手工指定一些细节参数

    //(4)、UseParNewGC收集器  --老年代默认激活 Serial Old收集器
    //"-XX:+UseParNewGC"：强制指定使用ParNew；

    //JVM设置
    //-xms10m -xmx10m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+UseParallelOldGC

    public static void main(String[] args) {
        Boy boy = new Boy();
        boy = null;
        System.gc();
    }

}
