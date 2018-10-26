package com.soft.chapter6;

import java.util.LinkedList;
import java.util.List;

/**
 * 简易线程池
 *
 * @author zxlei1
 * @date 2018/9/5 17:15
 */
public class ThreadPoolManager {

    public static void main(String[] args) {
        final RunnableTask task = new RunnableTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "....is execute ....");
            }
        };
        ThreadPoolManager poolManager = new ThreadPoolManager();
        for (int i = 0; i < 10; i++) {
            poolManager.execute(task);
        }
    }

    static interface RunnableTask {
        public void run();
    }

    private int max_thread_size = 0;
    private int min_thread_size = 0;
    private int active_thread_size = 0;
    /**
     * 任务队列
     */
    private List<RunnableTask> runnableList = null;
    private ThreadTask[] tasks = null;

    public ThreadPoolManager() {
        this(8, 2, 6);
    }

    public ThreadPoolManager(int max_thread_size, int min_thread_size, int active_thread_size) {
        this.max_thread_size = max_thread_size;
        this.min_thread_size = min_thread_size;
        this.active_thread_size = active_thread_size;
        init();
    }

    private void init() {
        runnableList = new LinkedList<RunnableTask>();
        tasks = new ThreadTask[min_thread_size];
        for (int i = 0; i < min_thread_size; i++) {
            tasks[i] = new ThreadTask("-" + i, runnableList);
            tasks[i].start();
        }
    }

    public void execute(RunnableTask task) {
        synchronized (runnableList) {
            ((LinkedList<RunnableTask>) runnableList).addLast(task);
            runnableList.notify();
        }
    }


    private class ThreadTask extends Thread {
        private String name = "";
        private List<RunnableTask> runnableList = null;

        public ThreadTask(String name, List<RunnableTask> runnableList) {
            super(name);
            this.name = name;
            this.runnableList = runnableList;
        }

        @Override
        public void run() {
            while (true) {
                RunnableTask task = null;
                synchronized (runnableList) {
                    while (runnableList.isEmpty()) {
                        try {
                            runnableList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = ((LinkedList<RunnableTask>) runnableList).removeFirst();
                }
                task.run();
            }
        }
    }
}
