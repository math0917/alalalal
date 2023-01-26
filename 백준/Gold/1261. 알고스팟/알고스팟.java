

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] maze;
    static int[][] dp;
    static Queue<int[]> queue;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        maze = new int[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            String rowValue = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = rowValue.charAt(j) - '0';
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        queue = new ArrayDeque<>();

        dp[0][0] = 0;
        queue.add(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int row = poll[0] + dx[i];
                int col = poll[1] + dy[i];
                if (0 <= row && row < n && 0 <= col && col < m) {

                    if (dp[row][col] > poll[2] + maze[row][col]) {
                        dp[row][col] = poll[2] + maze[row][col];
                        queue.add(new int[]{row, col, dp[row][col]});
                    }

                }
            }
        }
        System.out.println(dp[n - 1][m - 1]);




    }
}
