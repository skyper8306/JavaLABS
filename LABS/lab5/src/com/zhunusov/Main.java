package com.zhunusov;

public class Main {

    public static void main(String[] args) {
        UniversalPropReader upr = new UniversalPropReader("1.properties");
        upr.print();
        System.out.println(upr.getProperty("git"));
    }
}
