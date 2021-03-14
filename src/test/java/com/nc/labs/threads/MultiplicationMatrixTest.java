package com.nc.labs.threads;

import org.junit.Assert;
import org.junit.Test;

/**
 * The class checks the operation of multithreaded matrix multiplication
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class MultiplicationMatrixTest {
    /**
     * Object of the MultiplicationMatrix class
     */
    private MultiplicationMatrix multiplicationMatrix;

    /**
     * Test for testing multithreaded matrix multiplication with positive values
     */
    @Test
    public void multiplicationPositive () {
        int[][] firstMatrix = {{1, 2, 5}, {3, 4, 6}};
        int[][] secondMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        int[][] actual = {{44, 52, 60}, {61, 74, 87}};
        int[][] expected = multiplicationMatrix.multiplication(firstMatrix, secondMatrix);

        Assert.assertEquals(expected, actual);
    }

    /**
     * Test for testing multithreaded matrix multiplication with negative values
     */
    @Test
    public void multiplicationNegative () {
        int[][] firstMatrix = {{-13, -28, -50, -48, -30}};
        int[][] secondMatrix = {{-234, -12, -42, -21},
                                {-45, -55, -61, -14},
                                {-71, -85, -91, -64},
                                {-1, -4, -76, -34},
                                {-23, -56, -13, -9}};

        int[][] actual = {{8590, 7818, 10842, 5767}};
        int[][] expected = multiplicationMatrix.multiplication(firstMatrix, secondMatrix);

        Assert.assertEquals(expected, actual);
    }

    /**
     * Test for testing multithreaded matrix multiplication with different values
     */
    @Test
    public void multiplicationAll () {
        int[][] firstMatrix = {{4, 54, 23}, {68, -3, 96}, {98, -54, 44}};
        int[][] secondMatrix = {{24, 76, -65}, {-44, 73, 34}, {88, 82, -59}};

        int[][] actual = {{-256, 6132, 219}, {10212, 12821, -10186}, {8600, 7114, -10802}};
        int[][] expected = multiplicationMatrix.multiplication(firstMatrix, secondMatrix);

        Assert.assertEquals(expected, actual);
    }
}
