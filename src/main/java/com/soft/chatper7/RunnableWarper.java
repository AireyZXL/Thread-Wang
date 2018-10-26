package com.soft.chatper7;

/**
 * 线程任务的包装类
 *
 * @author zxlei1
 * @date 2018/9/6 15:05
 */
public abstract class RunnableWarper implements Runnable {

    private ThreadListener threadListener = null;

    private final static ThreadListener DEFAULT_LISHENER = new ThreadListener() {

        private String name = Thread.currentThread().getName();

        @Override
        public Object threadStart(Object[] args) {
            System.out.println(name + " start ...... ");
            return null;
        }

        @Override
        public Object threadRunning(Object[] args) {
            System.out.println(name + " running ...... ");
            return null;
        }

        @Override
        public Object threadFinish(Object[] args) {
            System.out.println(name + " finish ...... ");
            return null;
        }

        @Override
        public Object threadException(Object[] args) {
            System.out.println(name + " exception ...... ");
            return null;
        }
    };

    public RunnableWarper() {
        this(DEFAULT_LISHENER);
    }

    public RunnableWarper(ThreadListener listener) {
        this.threadListener = listener;
    }

    @Override
    public void run() {
        try {
            threadListener.threadStart(null);
            handler();
            threadListener.threadFinish(null);

        } catch (Exception e) {
            threadListener.threadException(null);
        }

    }

    abstract public void handler();
}
