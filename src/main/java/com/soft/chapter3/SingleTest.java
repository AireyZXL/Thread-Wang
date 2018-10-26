package com.soft.chapter3;

/**
 * 改进版的懒汉式单例模式
 * 既考虑效率又考虑安全
 *
 * @author zxlei1
 * @date 2018/8/28 16:58
 */
public class SingleTest {

    private static SingleTest instance = null;

    private SingleTest() {

    }

    public static SingleTest newInstance() {
        if (null == instance) {
            synchronized (SingleTest.class) {
                if (null == instance) {
                    instance = new SingleTest();
                }
            }
        }
        return instance;
    }

}
