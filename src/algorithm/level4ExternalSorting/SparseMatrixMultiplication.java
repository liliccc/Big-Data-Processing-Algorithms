package algorithm.level4ExternalSorting;

import java.util.ArrayList;
import java.util.List;

public class SparseMatrixMultiplication {
    /**
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */
    // sparse matrix - most numbers are zero. If not sparse matrix -> O(n^3), using GPU acceleration(good at matrix calculation)
    public int[][] multiply(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return new int[0][0];
        }
        if (B == null || B.length == 0 || B[0].length == 0) {
            return new int[0][0];
        }
        int rowLenA = A.length;
        int columnLenA = A[0].length;
        int columnLenB = B[0].length;
        int[][] C = new int[rowLenA][columnLenB];
        // reduce time complexity from O(n^3) to O(k*n^2)
        List<List<Integer>> nonZeroPoints = new ArrayList<>();
        for (int row = 0; row < columnLenA; row++) {
            nonZeroPoints.add(new ArrayList<>());
            for (int column = 0; column < columnLenB; column++) {
                if (B[row][column] != 0) {
                    nonZeroPoints.get(row).add(column);
                }
            }
        }
        // loop through A matrix
        for (int row = 0; row < rowLenA; row++) {
            for (int columnA = 0; columnA < columnLenA; columnA++) {
                if (A[row][columnA] == 0) {
                    continue;
                }
                // error before: nonZeroPoints.get(row)
                for (int nonZeroColumn : nonZeroPoints.get(columnA)) {
                    C[row][nonZeroColumn] += A[row][columnA] * B[columnA][nonZeroColumn];
                }
            }
        }
        return C;
    }
}
