

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int[][] matrix;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int t = 1; t < 11; t++) {
            int testCase = Integer.parseInt(br.readLine());
            matrix = new int[100][100];
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.printf("#%d %d\n", testCase, findMax(matrix));
        }
    }

    private static int findMax(int[][] matrix) {
        return Integer.max(Integer.max(rowMax(matrix), colMax(matrix)), diagonalMax(matrix));
    }

    private static int diagonalMax(int[][] matrix) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < 100; i++) {
            sum1 += matrix[i][i];
            sum2 += matrix[i][99 - i];
        }
        return Integer.max(sum1, sum2);
    }

    private static int rowMax(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 100; i++) {
            int sum = 0;
            for (int j = 0; j < 100; j++) {
                sum += matrix[i][j];
            }
            max = Integer.max(sum, max);
        }
        return max;
    }
    private static int colMax(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 100; i++) {
            int sum = 0;
            for (int j = 0; j < 100; j++) {
                sum += matrix[j][i];
            }
            max = Integer.max(sum, max);
        }
        return max;
    }


}
