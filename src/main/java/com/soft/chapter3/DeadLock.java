package com.soft.chapter3;

/**
 * 死锁
 *
 * @author zxlei1
 * @date 2018/8/28 17:15
 */
public class DeadLock {

    public static void main(String [] args){
        final Dead dead=new Dead();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    dead.methodA();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    dead.methodB();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }





}

class Dead{

    private Object lock=new Object();//自定义一个锁

    private int x=0;

    public void methodA(){
        synchronized (lock){
            synchronized (this){
                System.out.println("method A..."+(x++));
            }
        }
    }

    public void methodB(){
        synchronized (this){
            synchronized (lock){
                System.out.println("methodB..."+(x++));
            }
        }
    }

}