package com.soft.chapter5;

/**
 * 线程JOIN方法的使用
 * 临时加入一个线程，等到该线程执
 * 行结束之后才能运行主线程
 *
 * @author zxlei1
 * @date 2018/9/5 9:30
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i++ < 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1: " + i);
                }
            }
        };
        t1.start();
        t1.join();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                int j = 0;
                while (j++ < 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t2: " + j);
                }
            }
        };
        t2.start();
        //t2.join();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(100);
            System.out.println("main:" + i);
        }
    }

}
