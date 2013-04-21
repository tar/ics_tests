package ru.spbstu.qsp.codereview;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathUtils {
    final public static String MEAN_STR = "_mean";
    final public static String SD_STR = "_sd";
    final public static String SDLOG_STR = "_sdLog";
    final public static String CILOW_STR = "_lb";
    final public static String CIHIGH_STR = "_ub";
    final public static String COUNT_STR = "_count";
    final public static String MOMENT_1_STR = "_moment_1";
    final public static String MOMENT_2_STR = "_moment_2";
    final public static String MOMENT2_STR = "_moment2";
    
    /** private constructor to prevent instantiation */
    private MathUtils() {
    }
    
    public static double momentOfArray(Double[] ar, int order) {
        if (ar == null || ar.length == 0 || order == 0)
            return Double.NaN;
        int count = 0;
        double sum = 0;
        for (int i = 0; i < ar.length; i++)
            if (ar[i] != null && (ar[i].compareTo(new Double(1.e-18))<0)) {
                sum += Math.pow(ar[i], order);
                count++;
            }
        System.out.println(sum);
        if (count == 0)
            return Double.NaN;
        return Math.pow(sum / count, 1.0 / Math.abs(order));
    }

    public static double meanOfArray(Double[] ar) {
        if (ar == null || ar.length == 0)
            return Double.NaN;
        int count = 0;
        double sum = 0;
        for (int i = 0; i < ar.length; i++)
            if (ar[i] != null) {
                sum += ar[i];
                count++;
            }
        if (count == 0)
            return Double.NaN;
        return sum / count;
    }

    public static double momentOfArray(double[] ar, int order) {
        if (ar == null || ar.length == 0 || order == 0)
            return Double.NaN;
        int count = 0;
        double sum = 0;
        for (int i = 0; i < ar.length; i++)
            if ((!Double.isInfinite(ar[i])) && (!Double.isNaN(ar[i]))
                    && !(ar[i] <= 1.e-18)) {
                sum += Math.pow(ar[i], order);
                count++;
            }
        if (count == 0)
            return Double.NaN;
        return Math.pow(sum / count, 1.0 / Math.abs(order));
    }

    public static double meanOfArray(double[] ar) {
        if (ar == null || ar.length == 0)
            return Double.NaN;
        double sum = 0;
        int counter = 0;
        for (int i = 0; i < ar.length; i++)
            if (!Double.isInfinite(ar[i]) && !Double.isNaN(ar[i])) {
                counter++;
                sum += ar[i];
            }
        if (counter <= 1)
            return Double.NaN;
        return sum / counter;
    }

    public static double stndDevOfArray(Double[] vals) {
        if (vals == null)
            return Double.NaN;
        int count = vals.length;
        if (count == 0)
            return Double.NaN;
        if (count < 2)
            return 0;
        double mean = meanOfArray(vals);
        return stndDevOfArray(vals, mean);
    }

    public static Double stndDevOfArray(Double[] vals, double mean) {
        if (vals == null)
            return Double.NaN;
        if (vals.length == 0)
            return Double.NaN;
        if (vals.length < 2)
            return 0.0;
        int count = 0;
        double std = 0.;
        for (int i = 0; i < vals.length; i++)
            if (vals[i] != null) {
                double dev = vals[i] - mean;
                std += dev * dev;
                count++;
            }
        if (count <= 1)
            return Double.NaN;
        return Math.sqrt(std / (count - 1));
    }

    public static Double stndDevOfArray(double[] vals, double mean) {
        if (vals == null)
            return Double.NaN;
        if (vals.length == 0)
            return Double.NaN;
        if (vals.length < 2)
            return 0.0;
        double std = 0.;
        int counter = 0;
        for (int i = 0; i < vals.length; i++) {
            double dev = vals[i] - mean;
            if (!Double.isInfinite(dev) && !Double.isNaN(dev)) {
                counter++;
                std += dev * dev;
            }
        }
        if (counter <= 1)
            return Double.NaN;
        return Math.sqrt(std / (counter - 1));
    }

    /**
     * Returns list of n indices combinations size k.
     * */
    public static List<int[]> evaluateCombinations(int n, int k) {
        List<int[]> res = new ArrayList<int[]>();
        if (n <= k)
            return res;
        int[] comb = new int[k];
        for (int i = 0; i < k; i++)
            comb[i] = i;
        int index = k - 1;
        while (true) {
            res.add(Arrays.copyOf(comb, comb.length));
            index = k - 1;
            while (comb[index] == (n - k + index)) {
                index--;
                if (index < 0)
                    return res;
            }
            comb[index]++;
            for (int j = index + 1; j < k; j++)
                comb[j] = comb[index] + j - index;
        }
    }
}
