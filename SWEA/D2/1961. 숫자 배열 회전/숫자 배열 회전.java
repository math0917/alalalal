

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int[][] matrix;
    private static int[][] matrixRotate90;
    private static int[][] matrixRotate180;
    private static int[][] matrixRotate270;
    public static void main(String[] args) throws Exception{
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t < testCase + 1; t++) {
            int n = Integer.parseInt(br.readLine());
            matrix = new int[n][n];
            matrixRotate90 = new int[n][n];
            matrixRotate180 = new int[n][n];
            matrixRotate270 = new int[n][n];

            for (int row = 0; row < n; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < n; col++) {
                    matrix[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < n / 2; i++) {
                rotate90(matrix, matrixRotate90, i, n - 1 - i);
            }
            for (int i = 0; i < n / 2; i++) {
                rotate90(matrixRotate90, matrixRotate180, i, n - 1 - i);
            }
            for (int i = 0; i < n / 2; i++) {
                rotate90(matrixRotate180, matrixRotate270, i, n - 1 - i);
            }
            if (n % 2 == 1) {
                matrixRotate90[n / 2][n / 2] = matrix[n / 2][n / 2];
                matrixRotate180[n / 2][n / 2] = matrix[n / 2][n / 2];
                matrixRotate270[n / 2][n / 2] = matrix[n / 2][n / 2];
            }
            System.out.printf("#%d\n", t);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.printf("%d", matrixRotate90[i][j]);
                }
                System.out.printf(" ");
                for (int j = 0; j < n; j++) {
                    System.out.printf("%d", matrixRotate180[i][j]);
                }
                System.out.printf(" ");
                for (int j = 0; j < n; j++) {
                    System.out.printf("%d", matrixRotate270[i][j]);
                }
                System.out.println();
            }
        }
    }

    private static void rotate90(int[][] matrix, int[][] matrixRotate90, int startIdx, int finishIdx) {
        upPart(matrix, matrixRotate90, startIdx, finishIdx);
        rightPart(matrix, matrixRotate90, startIdx, finishIdx);
        downPart(matrix, matrixRotate90, startIdx, finishIdx);
        leftPart(matrix, matrixRotate90, startIdx, finishIdx);
    }

    private static void leftPart(int[][] matrix, int[][] matrixRotate90, int startIdx, int finishIdx) {
        for (int row = startIdx; row < finishIdx; row++) {
            matrixRotate90[startIdx][row] = matrix[startIdx + finishIdx - row][startIdx];
        }
    }

    private static void downPart(int[][] matrix, int[][] matrixRotate90, int startIdx, int finishIdx) {
        for (int col = finishIdx; col > 0; col--) {
            matrixRotate90[col][startIdx] = matrix[finishIdx][col];
        }
    }

    private static void rightPart(int[][] matrix, int[][] matrixRotate90, int startIdx, int finishIdx) {
        for (int row = startIdx; row < finishIdx; row++) {
            matrixRotate90[finishIdx][startIdx + finishIdx - row] = matrix[row][finishIdx];
        }
    }

    private static void upPart(int[][] matrix, int[][] matrixRotate90, int startIdx, int finishIdx) {
        for (int col = startIdx; col < finishIdx; col++) {
            matrixRotate90[col][finishIdx] = matrix[startIdx][col];
        }
    }
}
