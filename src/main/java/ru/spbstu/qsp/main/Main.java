package ru.spbstu.qsp.main;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.spbstu.qsp.codereview.MathUtils;
import ru.spbstu.qsp.source.MatrixOperations;

public class Main {
    private static final double[] testVector = getRandomVector(1000);
    private static final double[][] testMatrix = getRandomMatrix(1000);

    private static final ExecutorService _executor = Executors.newFixedThreadPool(2);

    private static class TestCommands implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                MatrixOperations.addMatrixToMatrix(testMatrix, testMatrix);
                MatrixOperations.subMatrixToMatrix(testMatrix, testMatrix);
                MatrixOperations.multMatrixToConst(testMatrix, 0.5);
                MatrixOperations.multMatrixToVector(testMatrix, testVector);
                MatrixOperations.multMatrixToMatrix(testMatrix, testMatrix);
                MatrixOperations.multVectorToVector(testVector, testVector);
                MathUtils.meanOfArray(testVector);
                MathUtils.momentOfArray(testVector, 2);
                MathUtils.stndDevOfArray(testVector, 0.5);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

    }

    private static double[] getRandomVector(int size) {
        Random rnd = new Random();
        double[] result = new double[size];
        for (int i = 0; i < result.length; i++) {
            result[i] = rnd.nextDouble();
        }
        return result;
    }

    private static double[][] getRandomMatrix(int size) {
        double[][] result = new double[size][size];
        for (int i = 0; i < result.length; i++) {
            result[i] = getRandomVector(size);
        }
        return result;
    }

    public static void main(String[] args) {
        _executor.execute(new TestCommands());
        _executor.execute(new TestCommands());
        _executor.execute(new TestCommands());
    }
}
