package com.soft.chapter;

/**
 * 策略模式的实现
 *
 * @author zxlei1
 * @date 2018/8/24 17:42
 */
public class StrategyTest {
    public static void main(String[] args) {
        //没有任何策略时的结果
        Calculator c = new Calculator(30, 24);
        System.out.println(c.result());
        //传入减法策略模式
        Calculator c1 = new Calculator(10, 30, new SubStrategy());
        System.out.println(c1.result());
        //看到这里就可以看到策略模式强大了，算法可以随意设置，系统的结构并不会发生任何变化
        Calculator c2 = new Calculator(30, 40, new CalcStrategy() {

            @Override
            public int calc(int x, int y) {
                return ((x + 10) - (y - 2)) * 4;
            }
        });
        System.out.println(c2.result());

    }


}

/**
 * 策略接口，主要是规范或者让结构程序知道如何调用
 */
interface CalcStrategy {
    int calc(int x, int y);
}

class Calculator {
    private int x = 0;
    private int y = 0;
    private CalcStrategy calcStrategy = null;

    public Calculator(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Calculator(int x, int y, CalcStrategy calcStrategy) {
        this(x, y);
        this.calcStrategy = calcStrategy;
    }

    public int calc(int x, int y) {
        return x + y;
    }

    /**
     * 只需关注接口，并且将接口用到的入参传递进去即可，
     * 并不关心到底具体要如何进行业务封装
     *
     * @return
     */
    public int result() {
        if (calcStrategy != null) {
            return calcStrategy.calc(x, y);
        }
        return calc(x, y);
    }
}

class AddStrategy implements CalcStrategy {

    @Override
    public int calc(int x, int y) {
        return x + y;
    }
}

class SubStrategy implements CalcStrategy {

    @Override
    public int calc(int x, int y) {
        return x - y;
    }
}