package com.soft.chapter2;

/**
 * 多线程测试
 * 线程的交替运行
 *
 * @author zxlei1
 * @date 2018/8/24 16:40
 */
public class MultThreadTest extends Thread {

    private final static int DEFAULT_VALUE=100;

    private int maxValue=0;

    private String threadName="";

    public MultThreadTest(String threadName){
       this(threadName,DEFAULT_VALUE);
    }

    public MultThreadTest(String threadName, int defaultValue){
        this.maxValue=defaultValue;
        this.threadName=threadName;
    }

    @Override
    public void run() {
        int i=0;
        while (i<maxValue){
            i++;
            System.out.println("Thread:"+threadName+":"+i);
        }
    }

    public static void main(String [] args){
        MultThreadTest t1=new MultThreadTest("t1");
        MultThreadTest t2=new MultThreadTest("t2",200);
        t1.start();
        t2.start();
    }
}
