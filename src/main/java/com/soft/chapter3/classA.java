package com.soft.chapter3;

/**
 * this锁
 *
 * @author zxlei1
 * @date 2018/8/28 15:27
 */
public class classA {

    public synchronized void A(){
        System.out.println("AAAAAAAAAAAAAAAAA");
        while (true){
            //TODO
        }
    }

    public synchronized void B(){
        System.out.println("BBBBBBBBBBBBBBBBBBB");
        while (true){
            //TODO
        }
    }

    public static void main(String [] args){
        final classA clazz=new classA();

        //启动一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                clazz.A();
            }
        }).start();

        //启动另一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                clazz.B();
            }
        }).start();


    }
}
