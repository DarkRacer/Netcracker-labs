package com.nc.labs.threads;

/**
 * Class for multithreaded matrix multiplication
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class MultiplicationMatrix extends Thread {
    /**
     * Number of threads
     */
    private static int threadsCount = 5;

    /**
     * First matrix
     */
    private int[][] firstMatrix;

    /**
     * Second matrix
     */
    private int[][] secondMatrix;

    /**
     * Result matrix
     */
    private int[][] resultMatrix;

    /**
     * Start row
     */
    private int startRow;

    /**
     * End row
     */
    private int endRow;

    /**
     * Constructor of the multithreaded matrix multiplication class
     * @param firstMatrix first matrix
     * @param secondMatrix second matrix
     * @param resultMatrix result matrix
     * @param startRow start row
     * @param endRow end row
     */
    public MultiplicationMatrix(final int[][] firstMatrix, final int[][] secondMatrix, final int[][] resultMatrix,
                                final int startRow, final int endRow) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resultMatrix = resultMatrix;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    /**
     * Method for multithreaded matrix multiplication
     * @param firstMatrix first matrix
     * @param secondMatrix second matrix
     * @return result matrix after multiplication
     */
    public static int[][] multiplication(final int[][] firstMatrix, final int[][] secondMatrix) {
        if (firstMatrix == null || firstMatrix.length == 0 || firstMatrix[0] == null || firstMatrix[0].length == 0) {
            throw new IllegalArgumentException("First matrix not corrected");
        }
        if (secondMatrix == null || secondMatrix.length == 0 || secondMatrix[0] == null || secondMatrix[0].length == 0) {
            throw new IllegalArgumentException("Second matrix not corrected");
        }
        if (firstMatrix[0].length != secondMatrix.length) {
            throw new IllegalArgumentException("Matrix is not consistent");
        }

        int[][] resultMatrix = new int[firstMatrix.length][secondMatrix[0].length];

        if (threadsCount > firstMatrix.length)
            threadsCount = firstMatrix.length;

        int count = firstMatrix.length / threadsCount;
        int additional = firstMatrix.length % threadsCount;

        Thread[] threads = new Thread[threadsCount];
        int start = 0;
        for (int i = 0; i < threadsCount; i++) {
            int cnt = ((i == 0) ? count + additional : count);
            threads[i] = new MultiplicationMatrix(firstMatrix, secondMatrix, resultMatrix, start, start + cnt - 1);
            start += cnt;
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        return resultMatrix;
    }

    /**
     * Stream method for multiplying matrix rows
     */
    @Override
    public void run() {
        for (int row = startRow; row <= endRow ; row++) {
            for (int col = 0; col < resultMatrix[row].length; col++) {
                resultMatrix[row][col] = calcOneValue(row, col);
            }
        }
    }

    /**
     * Method for calculating the cell of the resulting matrix
     * @param row row matrix
     * @param column column matrix
     * @return cell value of the resulting matrix
     */
    private int calcOneValue(final int row, final int column) {
        int value = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            value += firstMatrix[row][i] * secondMatrix[i][column];
        }
        return value;
    }
}
