package com.soft.chapter4;

/**
 * 改进版的生产者和消费者模式
 *
 * @author zxlei1
 * @date 2018/8/28 17:43
 */
public class ProducerAndCustomer2 {

    public static void main(String[] args) {
        final NumberFactory2 factory2 = new NumberFactory2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    factory2.create();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    factory2.consume();
                }
            }
        }).start();
    }

}

class NumberFactory2 {

    private int i = 0;

    private Object lock = new Object();

    private boolean created = false;

    public void create() {
        synchronized (lock) {
            if (!created) {
                i++;
                System.out.println("create:" + i);
                lock.notify();
                created = true;
            } else {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void consume() {
        synchronized (lock) {
            if (created) {
                System.out.println("consume:" + i);
                created = false;
                lock.notify();
            } else {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

