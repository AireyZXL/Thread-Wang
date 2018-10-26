package com.soft.chapter5;

/**
 * 守护线程
 *
 * @author zxlei1
 * @date 2018/9/4 18:25
 */
public class DaemonThreadTest {

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i++ <= 1000) {
                    System.out.println("jjjjjjjjjjjjj:" + i);
                }
            }
        };
        t1.setDaemon(true);
        t1.start();

        int i = 0;
        while (i++ < 10) {
            System.out.println("iiiiiiiiiiiii:" + i);
        }


    }

}
