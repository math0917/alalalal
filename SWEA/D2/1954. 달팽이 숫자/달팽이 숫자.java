

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static int testCase;
    static int n;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int num;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            visited = new boolean[n][n];
            visited[0][0] = true;
            num = 1;
            dfs(0, 0,0);
            System.out.println("#" + t);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    private static void dfs(int thisRow, int thisCol,int dir) {
        if (num == (int) Math.pow(n, 2) + 1) {
            return;
        }

        if (map[thisRow][thisCol] == 0) {
            map[thisRow][thisCol] = num++;
        }

        int row = thisRow + dx[dir];
        int col = thisCol + dy[dir];
        if (!(0 <= row && row < n && 0 <= col && col < n) || visited[row][col]) {
            dfs(thisRow, thisCol, (dir + 1) % 4);
        } else {
            visited[row][col] = true;
            dfs(row, col, dir);
        }
    }
}
