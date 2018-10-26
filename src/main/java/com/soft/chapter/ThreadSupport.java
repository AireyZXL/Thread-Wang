package com.soft.chapter;

/**
 * 顺序执行,不会交替执行
 */
public class ThreadSupport {
    public static void main(String[] args) {
        int i = 100;
        while (i > 0) {
            System.out.println("current i value is :" + i--);
        }
        System.out.println("===========================");
        while (i < 100) {
            System.out.println("current i value is :" + i++);
        }
    }
}
