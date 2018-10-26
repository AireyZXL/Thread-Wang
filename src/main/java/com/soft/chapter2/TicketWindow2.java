package com.soft.chapter2;

/**
 * 通过 Runnable 接口实现第三版
 *
 * @author zxlei1
 * @date 2018/8/24 17:20
 */
public class TicketWindow2 implements Runnable {

    private int max_value=0;

    @Override
    public void run() {
        while (true){
            if (max_value>50){
                break;
            }
            System.out.println(Thread.currentThread().getName()+":"+max_value++);
        }

    }

    public static void main(String [] args){
        TicketWindow2 ticket=new TicketWindow2();
        Thread t1=new Thread(ticket);
        Thread t2=new Thread(ticket);
        Thread t3=new Thread(ticket);
        t1.start();
        t2.start();
        t3.start();
    }
}
