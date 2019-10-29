package com.zhunusov;

public class Main {

    public static void main(String[] args) {
        MyStringBuilder builder = new MyStringBuilder();
        builder.append("123");
        builder.append("456");
        builder.append("789");
        System.out.println(builder);
        builder.delete(1,8);
        System.out.println(builder);
        builder.undo();
        System.out.println(builder);
        builder.reverse();
        System.out.println(builder);
        builder.undo();
        System.out.println(builder);
        builder.undo();
        System.out.println(builder);
        builder.undo();
        System.out.println(builder);
    }
}
