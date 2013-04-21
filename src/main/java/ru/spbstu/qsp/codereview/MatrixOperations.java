package ru.spbstu.qsp.codereview;

public class MatrixOperations {

	/** private constructor to prevent instantiation */
	private MatrixOperations() {
	}

	/**
	 * Method provides multiplication of matrix and constant
	 * 
	 * @param matrix
	 *            source matrix of double
	 * @param value
	 *            constant double parameter
	 * @return result matrix
	 */

	public static double[][] multMatrixToConst(double[][] matrix, double value) {
		if (matrix == null)
			return null;
		if (matrix.length == 0 || matrix[0].length == 0)
			return null;
		int rowNum = matrix.length;
		int colNum = matrix[0].length;
		double[][] result = new double[rowNum][colNum];
		for (int i = 0; i < rowNum; i++)
			for (int j = 0; j < colNum; j++)
				result[i][j] = matrix[i][j] * value;
		return result;

	}

	/**
	 * Method provides multiplication of matrix and vector
	 * 
	 * @param matrix
	 *            double[][] source matrix of double
	 * @param vector
	 *            double[] source vector of double
	 * @return
	 */
	public static double[] multMatrixToVector(double[][] matrix, double[] vector) {
		if (matrix == null || vector == null)
			return null;
		if (matrix.length == 0 || matrix[0].length == 0 || vector.length == 0)
			return null;
		int rowNum = matrix.length;
		int colNum = matrix[0].length;
		if (colNum != vector.length)
			return null;

		double[] result = new double[rowNum];
		for (int i = 0; i < rowNum; i++) {
			result[i] = 0.;
			for (int j = 0; j < colNum; j++) {
				result[i] += matrix[i][j] * vector[j];
			}
		}
		return result;
	}

	/**
	 * Method provides multiplication of two matrices
	 * 
	 * @param matrixA
	 *            first source matrix of double
	 * @param matrixB
	 *            second source matrix of double
	 * @return
	 */

	public static double[][] multMatrixToMatrix(double[][] matrixA,
			double[][] matrixB) {
		if (matrixA == null || matrixB == null)
			return null;
		if (matrixA.length == 0 || matrixA[0].length == 0
				|| matrixB.length == 0 || matrixB[0].length == 0)
			return null;
		int rowNumA = matrixA.length;
		int colNumA = matrixA[0].length;
		int colNumB = matrixB[0].length;
		if (colNumA != matrixB.length)
			return null;

		double[][] result = new double[rowNumA][colNumB];
		for (int i = 0; i < rowNumA; i++) {
			for (int k = 0; k < colNumB; k++) {
				double temp = 0.;
				for (int j = 0; j < colNumA; j++) {
					temp += matrixA[i][j] * matrixB[j][k];
				}
				result[i][k] = temp;
			}
		}
		return result;
	}

	/**
	 * Method provides sum of two matrices
	 * 
	 * @param matrixA
	 *            first source matrix
	 * @param matrixB
	 *            second source matrix
	 * @return
	 */
	public static double[][] addMatrixToMatrix(double[][] matrixA,
			double[][] matrixB) {
		if (matrixA == null || matrixB == null)
			return null;
		if (matrixA.length == 0 || matrixA[0].length == 0
				|| matrixB.length == 0 || matrixB[0].length == 0)
			return null;
		int rowNum = matrixA.length;
		int colNum = matrixA[0].length;
		if (rowNum != matrixB.length || colNum != matrixB[0].length)
			return null;

		double[][] result = new double[rowNum][colNum];
		for (int i = 0; i < rowNum; i++)
			for (int j = 0; j < colNum; j++)
				result[i][j] = matrixA[i][j] + matrixB[i][j];
		return result;
	}

	/**
	 * Method provides sub of two matrices
	 * 
	 * @param matrixA
	 *            first source matrix of double
	 * @param matrixB
	 *            second source matrix of double
	 * @return
	 */
	public static double[][] subMatrixToMatrix(double[][] matrixA,
			double[][] matrixB) {
		if (matrixA == null || matrixB == null)
			return null;
		if (matrixA.length == 0 || matrixA[0].length == 0
				|| matrixB.length == 0 || matrixB[0].length == 0)
			return null;
		int rowNum = matrixA.length;
		int colNum = matrixA[0].length;
		if (rowNum != matrixB.length || colNum != matrixB[0].length)
			return null;

		double[][] result = new double[rowNum][colNum];
		for (int i = 0; i < rowNum; i++)
			for (int j = 0; j < colNum; j++)
				result[i][j] = matrixA[i][j] - matrixB[i][j];
		return result;
	}

	/**
	 * Method provides multiplication of two vectors
	 * 
	 * @param vectorA
	 *            first source vector of double
	 * @param vectorB
	 *            second source vector of double
	 * @return
	 */
	public static double[][] multVectorToVector(double[] vectorA,
			double[] vectorB) {
		if (vectorA == null || vectorB == null)
			return null;
		if (vectorA.length == 0 || vectorB.length == 0)
			return null;
		if (vectorA.length != vectorB.length)
			return null;
		int rowNum = vectorA.length;
		int colNum = vectorB.length;

		double[][] result = new double[rowNum][colNum];
		for (int i = 0; i < rowNum; i++)
			for (int j = 0; j < colNum; j++)
				result[i][j] = vectorA[i] * vectorB[j];
		return result;
	}
}
