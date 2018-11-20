package com.soft.chapter3;

/**
 * TODO
 *
 * @author zxlei1
 * @date 2018/10/29 16:16
 */
public class TestWhile implements Runnable{

    private static int max_value = 0;

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
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":lock..." + max_value++);
                }
            }
        }else {
            while (true){
                synchronized (lock){
                    try {
                        Thread.sleep(1000);
                        System.out.println("1111111111");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void change() throws InterruptedException {
        Thread.sleep(300);
        this.flag = false;
    }

    public static void main(String [] args) throws InterruptedException {
        TestWhile tk1 = new TestWhile();
        Thread t1 = new Thread(tk1);
        Thread t2 = new Thread(tk1);
        t1.start();
        tk1.change();
        t2.start();
    }
}
