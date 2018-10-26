package com.soft.chapter2;

/**
 * 银行排队叫号程序第一版
 *
 * @author zxlei1
 * @date 2018/8/24 17:08
 */
public class TicketWindow extends Thread {

    static int max_value = 0;//最大的号码

    @Override
    public void run() {
        while (true) {
            if (max_value > 50) {
                break;
            }
            System.out.println(currentThread().getName() + ":" + max_value++);
        }
    }

    public static void main(String[] args) {
        Thread t1 = new TicketWindow();
        Thread t2 = new TicketWindow();
        Thread t3 = new TicketWindow();
        t1.start();
        t2.start();
        t3.start();
    }
}
