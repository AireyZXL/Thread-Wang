package com.soft.chapter4;

/**
 * 多线程下的生产者消费者改进版
 *
 *
 * @author zxlei1
 * @date 2018/8/31 11:51
 */
public class ProducerAndCustomer4 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage:java com.wenhuisoft.chapter4.ProducerCustomer number");
            System.out.println("Please restart again....");
            System.exit(0);
        }
        int count = 0;
        try {
            count = Integer.parseInt(args[0]);
        } catch (Throwable e) {
            System.out.println("Please enter a integer type number...");
            System.exit(0);
        }

        final Factory4 factory = new Factory4();

        for (int i = 0; i < count; i++) {
            new Thread(new Producer4(factory)).start();
            new Thread(new Consumer4(factory)).start();
        }


    }


}


/**
 * 封装了数据生产工厂，该工厂中提供了生产和消费的方法
 */
class Factory4 {

    private int i = 0;

    private boolean created = false;

    public synchronized void create() {
        while (created) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        i++;
        System.out.println(Thread.currentThread().getName() + "-create-" + i);
        this.created = true;
        notifyAll();
    }

    public synchronized void consume() {
        while (created) {
            System.out.println(Thread.currentThread().getName() + "-consume-" + i);
            this.created = false;
            notifyAll();
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

/**
 * 生产者与消费者的基类
 */
abstract class AbsFactory4 implements Runnable {
    protected Factory4 factory = null;

    public AbsFactory4(Factory4 factory) {
        this.factory = factory;
    }

    abstract protected void execute();

    @Override
    public void run() {
        while (true) {
            execute();
           /* try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}

class Producer4 extends AbsFactory4 {

    public Producer4(Factory4 factory) {
        super(factory);
    }

    @Override
    protected void execute() {
        factory.create();
    }
}

class Consumer4 extends AbsFactory4 {

    public Consumer4(Factory4 factory) {
        super(factory);
    }

    @Override
    protected void execute() {
        factory.consume();
    }
}