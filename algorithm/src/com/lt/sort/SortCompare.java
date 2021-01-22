package com.lt.sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liangtao
 * @description 配许算法比较
 * @date 2021年01月20 13:50
 **/
public class SortCompare {
    private static Map<String, Example<Double>> sortMap = new HashMap<>();

    static {
        sortMap.put("insert", new Insert<>());
        sortMap.put("select", new Select<>());
        sortMap.put("shell", new Shell<>());
        sortMap.put("merge1", new MergeImpl1<>());
        sortMap.put("merge2", new MergeImpl2<>());
        sortMap.put("merge2", new MergeImpl2<>());
        sortMap.put("quick", new Quick<>());
        sortMap.put("heap", null);
    }

    public static double time(String alg, Double[] a) {
        long start = System.currentTimeMillis();
        sortMap.get(alg).sort(a);
        return (start - System.currentTimeMillis()) / 1000.0;
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        args = new String[]{"merge1", "quick", "2009", "20"};
        for (int i = 0; i < 20; i++) {
            StdOut.println("--------------");
            String alg1 = args[0];
            String alg2 = args[1];
            int N = Integer.parseInt(args[2]);
            int T = Integer.parseInt(args[3]);
            double t1 = timeRandomInput(alg1, N, T);
            double t2 = timeRandomInput(alg2, N, T);
            StdOut.printf("For %d random Doubles\n %s is", N, alg1);
            StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
        }

    }
}
