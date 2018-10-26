package com.soft.chapter4;

/**
 * 简易的生产者消费者模式
 *
 * @author zxlei1
 * @date 2018/8/28 17:35
 */
public class ProducerAndCustomer {

    public static void main(String [] args){
        final NumberFactory factory=new NumberFactory();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    factory.create();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    factory.consume();
                }
            }
        }).start();
    }

}

class NumberFactory {

    private int i = 0;

    private Object lock = new Object();

    public void create(){
        synchronized (lock){
            System.out.println("create...i-"+i++);
        }
    }

    public void consume(){
        synchronized (lock){
            System.out.println("consume...i-"+i);
        }
    }

}
