package ru.spbstu.qsp.source;

import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

public class MatrixOperationsTest extends TestCase {

	private static final Logger _logger = Logger
			.getLogger(MatrixOperationsTest.class);

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public MatrixOperationsTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MatrixOperationsTest.class);
	}

	@org.junit.Test
	public void testMultMatrixToConst() {
		_logger.info("---------------Multiply matrix to constant----------------");
		double[][] matrix = { { 1.0, 2.0 }, { 3.0, 4.0 } };
		double constant = 1.0;
		double[][] result = MatrixOperations
				.multMatrixToConst(matrix, constant);
		_logger.info("Test matrix and result length");
		assertEquals(matrix.length, result.length);
		_logger.info("Test multiply results");
		assertTrue(equals(matrix, result));
		_logger.info("Test null matrix");
		assertNull(MatrixOperations.multMatrixToConst(null, 2.0));
		_logger.info("Test zero matrix length");
		assertNull(MatrixOperations.multMatrixToConst(new double[0][1], 2.0));
		_logger.info("Test zero matrix inner length");
		assertNull(MatrixOperations.multMatrixToConst(new double[1][0], 2.0));
		_logger.info("---------------------------------------------------------");
	}

	@org.junit.Test
	public void testMultMatrixToVector() {
		_logger.info("----------------Multiply matrix to vector-----------------");
		double[][] matrix = { { 1.0, 2.0 }, { 3.0, 4.0 } };
		double[] vector = { 1.0, 1.0 };
		double[] vectorElement = { 1.0 };
		double[] result = MatrixOperations.multMatrixToVector(matrix, vector);
		double[] expResult = { 3.0, 7.0 };
		_logger.info("Test matrix and result length");
		assertEquals(matrix.length, result.length);
		_logger.info("Test multiply result");
		assertTrue(Arrays.equals(result, expResult));
		_logger.info("Test null matrix");
		assertNull(MatrixOperations.multMatrixToVector(null, vector));
		_logger.info("Test null vector");
		assertNull(MatrixOperations.multMatrixToVector(matrix, null));
		_logger.info("Test zero matrix length");
		assertNull(MatrixOperations
				.multMatrixToVector(new double[0][1], vector));
		_logger.info("Test zero matrix length");
		assertNull(MatrixOperations
				.multMatrixToVector(new double[1][0], vector));
		_logger.info("Test invalid matrix/vector size");
		assertNull(MatrixOperations.multMatrixToVector(matrix, vectorElement));
		_logger.info("---------------------------------------------------------");
	}

	@org.junit.Test
	public void testMultMatrixToMatrix() {
		_logger.info("----------------Multiply matrix to matrix-----------------");
		double[][] matrix1 = { { 1.0, 2.0 }, { 3.0, 4.0 } };
		double[][] matrix2 = { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
		double[][] result = MatrixOperations.multMatrixToMatrix(matrix1,
				matrix2);
		double[][] expResult = { { 9.0, 12.0, 15.0 }, { 19.0, 26.0, 33.0 } };
		_logger.info("Test matrix and result length");
		assertEquals(matrix1.length, result.length);
		_logger.info("Test multiply result");
		assertTrue(equals(result, expResult));
		_logger.info("Test null left matrix");
		assertNull(MatrixOperations.multMatrixToMatrix(null, matrix2));
		_logger.info("Test null right matrix");
		assertNull(MatrixOperations.multMatrixToMatrix(matrix1, null));
		_logger.info("Test zero matrix length");
		assertNull(MatrixOperations.multMatrixToMatrix(new double[0][1],
				matrix2));
		_logger.info("Test zero matrix length");
		assertNull(MatrixOperations.multMatrixToMatrix(new double[1][0],
				matrix2));
		_logger.info("Test invalid matrices size");
		assertNull(MatrixOperations.multMatrixToMatrix(matrix1,
				new double[3][3]));
		_logger.info("---------------------------------------------------------");
	}

	@org.junit.Test
	public void testAddMatrixToMatrix() {
		_logger.info("------------------Sum matrix to matrix-------------------");
		double[][] matrix1 = { { 1.0, 2.0 }, { 3.0, 4.0 } };
		double[][] result = MatrixOperations
				.addMatrixToMatrix(matrix1, matrix1);
		double[][] expResult = { { 2.0, 4.0 }, { 6.0, 8.0 } };
		_logger.info("Test matrix and result length");
		assertEquals(matrix1.length, result.length);
		_logger.info("Test sum result");
		assertTrue(equals(result, expResult));
		_logger.info("Test null left matrix");
		assertNull(MatrixOperations.addMatrixToMatrix(null, matrix1));
		_logger.info("Test null right matrix");
		assertNull(MatrixOperations.addMatrixToMatrix(matrix1, null));
		_logger.info("Test zero matrix length");
		assertNull(MatrixOperations
				.addMatrixToMatrix(new double[0][1], matrix1));
		_logger.info("Test zero matrix length");
		assertNull(MatrixOperations.multMatrixToMatrix(new double[1][0],
				matrix1));
		_logger.info("Test invalid matrices size");
		assertNull(MatrixOperations.multMatrixToMatrix(matrix1,
				new double[3][3]));
		_logger.info("--------------------------------------------------------");
	}

	@org.junit.Test
	public void testSubMatrixToMatrix() {
		_logger.info("------------------Sub matrix to matrix-------------------");
		double[][] matrix1 = { { 1.0, 2.0 }, { 3.0, 4.0 } };
		double[][] result = MatrixOperations
				.subMatrixToMatrix(matrix1, matrix1);
		double[][] expResult = { { 0.0, 0.0 }, { 0.0, 0.0 } };
		_logger.info("Test matrix and result length");
		assertEquals(matrix1.length, result.length);
		_logger.info("Test sub result");
		assertTrue(equals(result, expResult));
		_logger.info("Test null left matrix");
		assertNull(MatrixOperations.addMatrixToMatrix(null, matrix1));
		_logger.info("Test null right matrix");
		assertNull(MatrixOperations.addMatrixToMatrix(matrix1, null));
		_logger.info("Test zero matrix length");
		assertNull(MatrixOperations
				.addMatrixToMatrix(new double[0][1], matrix1));
		_logger.info("Test zero matrix length");
		assertNull(MatrixOperations.multMatrixToMatrix(new double[1][0],
				matrix1));
		_logger.info("Test invalid matrices size");
		assertNull(MatrixOperations.multMatrixToMatrix(matrix1,
				new double[3][3]));
		_logger.info("--------------------------------------------------------");
	}

	@org.junit.Test
	public void testMultVectorToVector() {
		_logger.info("----------------Multiply vector to vector-----------------");
		double[] vector1 = { 1.0, 2.0 };
		double[] vector2 = { 1.0, 2.0 };
		double[][] result = MatrixOperations.multVectorToVector(vector1,
				vector2);
		double[][] expResult = { { 1.0, 2.0 }, { 2.0, 4.0 } };
		_logger.info("Test matrix and result length");
		assertEquals(vector1.length, result.length);
		_logger.info("Test multiply result");
		assertTrue(equals(result, expResult));
		_logger.info("Test null matrix");
		assertNull(MatrixOperations.multVectorToVector(null, vector1));
		_logger.info("Test null vector");
		assertNull(MatrixOperations.multVectorToVector(vector1, null));
		_logger.info("Test zero matrix length");
		assertNull(MatrixOperations.multVectorToVector(new double[0], vector1));
		_logger.info("Test invalid matrix/vector size");
		assertNull(MatrixOperations.multVectorToVector(vector1, new double[5]));
		_logger.info("---------------------------------------------------------");
	}

	private boolean equals(double[][] m1, double[][] m2) {
		for (int i = 0; i < m1.length; i++) {
			if (!Arrays.equals(m1[i], m2[i])) {
				return false;
			}
		}
		return true;
	}
}
