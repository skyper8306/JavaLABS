package com.zhunusov;

public class Main {

    public static void main(String[] args) {
        if(args.length!=1){
            System.exit(-1);
        }
        UniversalPropReader upr = new UniversalPropReader(args[0]);
        upr.print();
        System.out.println(upr.getProperty("git"));
    }
}
