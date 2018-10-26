package com.soft.chapter;

/**
 * 线程的运行步骤
 * 1.继承Thread类
 * 2.重写run()方法
 * 3.调用start()方法
 *
 * @author zxlei1
 * @date 2018/8/24 15:45
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        int i = 0;
        while (i < 60) {
            System.out.println(Thread.currentThread().getName() + ":" + i++);
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}
