package com.soft.chapter5;

/**
 * yield的用法
 * 线程的 yield 方法就是短暂放弃 CPU 执行权，但是它刹那点就和其他线程争抢 CPU 执行权
 *
 * @author zxlei1
 * @date 2018/9/4 18:41
 */
public class YieldTest {

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i++ <= 2000) {
                    System.out.println("iiiiiiiiiiii---------" + i);
                }
            }
        };
        t1.start();
        t1.yield();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int j = 0;
                while (j++ < 1000) {
                    System.out.println("jjjjjjjjjjj+++++++++++" + j);
                }
            }
        };
        t2.start();
    }


}
