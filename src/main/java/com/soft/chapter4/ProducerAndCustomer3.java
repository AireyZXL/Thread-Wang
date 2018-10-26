package com.soft.chapter4;

/**
 * 多线程下的生产者消费者
 * 会有死锁的情况发生。。。
 *
 * @author zxlei1
 * @date 2018/8/31 11:51
 */
public class ProducerAndCustomer3 {

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

        final Factory factory = new Factory();

        for (int i = 0; i < count; i++) {
            new Thread(new Producer(factory)).start();
            new Thread(new Consumer(factory)).start();
        }


    }


}


/**
 * 封装了数据生产工厂，该工厂中提供了生产和消费的方法
 */
class Factory {

    private int i = 0;

    private boolean created = false;

    public void create() {
        synchronized (this) {
            if (created) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println(Thread.currentThread().getName() + "-create-" + i);
                notify();
                this.created = true;
            }
        }

    }

    public void consume() {
        synchronized (this) {
            if (created) {
                System.out.println(Thread.currentThread().getName() + "-consume-" + i);
                notify();
                this.created = false;
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * 生产者与消费者的基类
 */
abstract class AbsFactory implements Runnable {
    protected Factory factory = null;

    public AbsFactory(Factory factory) {
        this.factory = factory;
    }

    abstract protected void execute();

    @Override
    public void run() {
        while (true) {
            execute();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends AbsFactory {

    public Producer(Factory factory) {
        super(factory);
    }

    @Override
    protected void execute() {
        factory.create();
    }
}

class Consumer extends AbsFactory {

    public Consumer(Factory factory) {
        super(factory);
    }

    @Override
    protected void execute() {
        factory.consume();
    }
}