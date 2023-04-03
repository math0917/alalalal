import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Solution {
    static int testCase;
    static int[][] adjacent;
    static int n;
    static int result1;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            adjacent = new int[n][n];
            result1 = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    adjacent[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < n; i++) {
                cal(i);
            }
            System.out.println("#"+t+" "+result1);
        }
    }

    private static void cal(int i) {
        visited = new boolean[n];
        visited[i] = true;
        int result = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, 0});
        while (!queue.isEmpty()) {
            int[] thisTurn = queue.poll();
            int thisNum = thisTurn[0];
            int thisLen = thisTurn[1];
            for (int j = 0; j < n; j++) {
                if (adjacent[thisNum][j] == 1 && !visited[j]) {
                    visited[j] = true;
                    result += thisLen + 1;
                    queue.add(new int[]{j, thisLen + 1});
                }
            }
        }
        result1 = Math.min(result, result1);
    }
}