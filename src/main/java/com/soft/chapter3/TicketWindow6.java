package com.soft.chapter3;

/**
 * static锁验证
 *
 * @author zxlei1
 * @date 2018/8/28 15:38
 */
public class TicketWindow6 implements Runnable {

    private static int max_value = 0;

    private Object lock = new Object();

    private boolean flag = true;

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (TicketWindow6.class) {
                    if (max_value > 5000) {
                        break;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":lock..." + max_value++);
                }
            }
        } else {
            while (true) {
                if (ticket()) {
                    break;
                }
            }
        }
    }


    private synchronized static boolean ticket() {
        if (max_value > 5000) {
            return true;
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": method..." + max_value++);
        return false;
    }

    public void change() throws InterruptedException {
        Thread.sleep(30);
        this.flag = false;
    }

    public static void main(String[] args) throws InterruptedException {
        TicketWindow6 tk5 = new TicketWindow6();
        Thread t1 = new Thread(tk5);
        Thread t2 = new Thread(tk5);
        t1.start();
        tk5.change();
        t2.start();
    }
}
