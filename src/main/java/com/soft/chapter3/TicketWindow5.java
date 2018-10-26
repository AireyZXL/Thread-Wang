package com.soft.chapter3;

/**
 * this锁验证
 *
 * @author zxlei1
 * @date 2018/8/28 15:38
 */
public class TicketWindow5 implements Runnable {

    private int max_value = 0;

    private Object lock = new Object();

    private boolean flag = true;

    @Override
    public void run() {
        if (flag) {
            while (true) {
                //换成this则是同一把锁
                synchronized (lock) {
                    if (max_value > 500) {
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


    private synchronized boolean ticket() {
        if (max_value > 500) {
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
        TicketWindow5 tk5 = new TicketWindow5();
        Thread t1 = new Thread(tk5);
        Thread t2 = new Thread(tk5);
        t1.start();
        tk5.change();
        t2.start();
    }
}
