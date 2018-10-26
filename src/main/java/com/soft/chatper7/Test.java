package com.soft.chatper7;

/**
 * 测试类
 *
 * @author zxlei1
 * @date 2018/9/6 15:20
 */
public class Test extends RunnableWarper {
    @Override
    public void handler() {
        System.out.println("bussiness handler");
    }

    public static void main(String [] args){
        Thread t=new Thread(new Test());
        t.start();
        Thread t1=new Thread(new Test());
        t1.start();

    }
}
