package com.soft.chapter3;

/**
 * 同步代码块
 *
 * @author zxlei1
 * @date 2018/8/24 18:20
 */
public class TicketWindow3 implements Runnable {

    private int max_value=0;

    private Object lock=new Object();


    @Override
    public void run() {
        while (true){
            synchronized (lock){
                if (max_value>100){
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+":"+max_value++);
            }
        }
    }



    public static void main(String [] args){
        TicketWindow3 tw=new TicketWindow3();
        Thread t1=new Thread(tw);
        Thread t2=new Thread(tw);
        Thread t3=new Thread(tw);
        t1.start();
        t2.start();
        t3.start();
    }
}
