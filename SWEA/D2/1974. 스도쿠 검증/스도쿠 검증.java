

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    private static int[][] sdoku;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        for (int i = 1; i < testCase + 1; i++) {
            sdoku = new int[9][9];
            for (int row = 0; row < 9; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < 9; col++) {
                    sdoku[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            boolean can = true;
            for (int row = 0; row < 9; row++) {
                if (!(isLineSdoku(row, sdoku, true))) {
                    can = false;
                    break;
                }
            }
            if (!(can)) {
                System.out.printf("#%d 0\n", i);
                continue;
            }
            for (int col = 0; col < 9; col++) {
                if (!(isLineSdoku(col, sdoku, false))) {
                    can = false;
                    break;
                }
            }
            if (!(can)) {
                System.out.printf("#%d 0\n", i);
                continue;
            }
            for (int row = 0; row < 9; row += 3) {
                boolean flag = true;
                for (int col = 0; col < 9; col += 3) {
                    if (!(isSquareSdoku(row, col, sdoku))) {
                        System.out.printf("#%d 0\n", i);
                        flag = false;
                        can = false;
                        break;
                    }
                }
                if (!(flag)) {
                    break;
                }
            }
            if (can) {
                System.out.printf("#%d 1\n", i);
            }

        }
    }

    private static boolean isSquareSdoku(int row, int col, int[][] sdoku) {
        Set<Integer> line = new HashSet<>();
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                line.add(sdoku[i][j]);
            }
        }
        if (line.size() == 9) {
            return true;
        }
        return false;
    }

    private static boolean isLineSdoku(int i, int[][] sdoku, boolean isRow) {
        Set<Integer> line = new HashSet<>();
        if (isRow) {
            for (int col = 0; col < 9; col++) {
                line.add(sdoku[i][col]);
            }
            if (line.size() == 9) {
                return true;
            }
            return false;
        } else {
            for (int row = 0; row < 9; row++) {
                line.add(sdoku[row][i]);
            }
            if (line.size() == 9) {
                return true;
            }
            return false;
        }

    }
}
