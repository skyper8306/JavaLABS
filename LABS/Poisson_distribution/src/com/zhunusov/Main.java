package com.zhunusov;

public class Main {

    public static void main(String[] args) {
        long[] array = new long[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = PoissonDistribution.getPoissonRandom(4);
            System.out.println(array[i]);
        }
        System.out.println(PoissonDistribution.isPoissonDistribution(array, 4));
    }
}
