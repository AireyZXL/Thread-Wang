package com.soft.chapter3;

/**
 * 同步重构方法
 *
 * @author zxlei1
 * @date 2018/8/27 14:50
 */
public class TicketWindow4 implements Runnable {

    private int max_value = 0;

    @Override
    public void run() {
        while (true) {
            if (ticket()) {
                break;
            }
        }
    }

    private synchronized boolean ticket() {
        if (max_value > 100) {
            return true;
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + max_value++);
        return false;
    }

    public static void main(String[] args) {
        TicketWindow4 tw4 = new TicketWindow4();
        Thread t1 = new Thread(tw4);
        Thread t2 = new Thread(tw4);
        Thread t3 = new Thread(tw4);
        t1.start();
        t2.start();
        t3.start();
    }
}
