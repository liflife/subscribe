package com.lxf.stock.test;

public class TestJMM {
    private static volatile int a;

    private static void setA(int a){
        a=a;
    }
    public static void main(String[] args) {
        new Thread(()->{
            while (a>99){
                System.out.println(a);
                break;
            }

        }).start();
        new Thread(()->{
            setA(100);
            System.out.println(a);
        }).start();
    }
}
