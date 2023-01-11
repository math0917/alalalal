

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static int N;
    public static int[][] matrix;
    public static int[][] dp;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static Queue<List<Integer>> queue;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t < testCase + 1; t++) {
            N = Integer.parseInt(br.readLine());
            matrix = new int[N][N];
            dp = new int[N][N];
            for (int i = 0; i < N; i++) {
                char[] chars = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = chars[j] - '0';
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
            dp[0][0] = 0;
            queue = new LinkedList<>();
            queue.add(Arrays.asList(0,0,0));

            while (!(queue.isEmpty())) {
                List<Integer> thisTurn = queue.poll();

                int thisTurnRow = thisTurn.get(0), thisTurnCol = thisTurn.get(1), thisTurnLen = thisTurn.get(2);
                for (int i = 0; i < 4; i++) {
                    int row = thisTurnRow + dx[i];
                    int col = thisTurnCol + dy[i];

                    if (0 <= row && row < N && 0 <= col && col < N) {
                        if (dp[row][col] > thisTurnLen + matrix[row][col]) {
                            dp[row][col] = thisTurnLen + matrix[row][col];
                            queue.add(Arrays.asList(row, col, dp[row][col]));
                        }
                    }
                }
            }

            System.out.printf("#%d %d\n", t, dp[N-1][N-1]);


        }
    }

}
