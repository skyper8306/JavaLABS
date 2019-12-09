package com.zhunusov;

import java.util.Random;
public class Main
{
    public static void main(String[] args) {
        Random r = new Random();
        if(args.length != 1){
            System.exit(-1);
        }
        int number = Integer.parseInt(args[0]);

        /*do{
            System.out.println("Please enter a positive integer!");
            while(!sc.hasNextInt()){
                System.out.println("That not an integer");
                sc.next();
            }
            number = sc.nextInt();
        }while(number <= 0);*/
        System.out.println("Thank you! Got " + number);

        int t;
        int[][] arrayInt = new int[number][number];
        for(int i = 0; i < number; i++) {
            t = r.nextInt(number);
            arrayInt[i][t] = 1;
            System.out.print("rand="+(t+1)+"\t\t");
            for(int j = 0; j < number; j++) {
                System.out.print(arrayInt[i][j] + " ");
            }
            System.out.println("");
        }
        //sc.close();
    }
}