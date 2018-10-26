package com.soft.chapter;

/**
 * 父类实现算法，子类实现细节
 *
 * @author zxlei1
 * @date 2018/8/24 15:52
 */
abstract class DiaGram {

    protected char c;
    public DiaGram(char c){
        this.c=c;
    }

    abstract protected void print(int size);

    abstract protected void printCount(String msg);

    public final void display(String msg){
        int len=msg.getBytes().length;
        print(len);
        printCount(msg);
        print(len);
    }
}

class starDiaGram extends DiaGram{

    public starDiaGram(char c){
       super(c);
    }

    @Override
    protected void print(int size) {
        for (int i = 0; i < size + 2; i++) {
            System.out.print(c);
        }
        System.out.println();
    }

    @Override
    protected void printCount(String msg) {
        System.out.print("*");
        System.out.print(msg);
        System.out.println("*");
    }
}

public class TemplateTest{
    public static void main(String [] args){
        DiaGram d1=new starDiaGram('*');
        d1.display("wangwenjun");
    }
}