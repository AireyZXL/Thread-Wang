package com.soft.chapter;

/**
 * 第一个多线程程序，交叉打印
 *
 * @author zxlei1
 * @date 2018/8/24 15:30
 */
public class FirstThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + i++);
                }
            }
        }).start();

        int i = 100;

        while (i > 0) {
            System.out.println(Thread.currentThread().getName() + ":" + i--);
        }
    }

}
