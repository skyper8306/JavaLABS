package com.zhunusov;

import java.util.Random;

public class PoissonDistribution {
    static int getPoissonRandom(double lambda) {
        Random r = new Random();
        double L = Math.exp(-lambda);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

    static boolean isEqual(double x, double y, double eps) {
        return Math.abs(x - y) < eps;
    }

    static boolean isPoissonDistribution(long[] arr, int lambda) {
        double sum = 0, sum2 = 0, mx, dx;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            sum2 += arr[i] * arr[i];
        }

        sum /= arr.length;
        mx = Math.round(sum);
        sum2 /= arr.length;
        dx = Math.round(sum2 - sum * sum);

        System.out.println(mx);
        System.out.println(dx);

        if (lambda == mx && lambda == dx) {
            return true;
        }
        return false;
    }
}
