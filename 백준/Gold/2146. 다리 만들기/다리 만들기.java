

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[][] visited;
    static int groundCount = 0;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};
    static Queue<int[]> queue;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        input();
        init();
        bfs();
        System.out.println(result);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int[] thisTurn = queue.poll();
            int thisTurnRow = thisTurn[0], thisTurnCol = thisTurn[1], thisTurnLength = thisTurn[2];
            for (int i = 0; i < 4; i++) {
                int row = thisTurnRow + dx[i];
                int col = thisTurnCol + dy[i];
                if (0 <= row && row < n && 0 <= col && col < n) {
                    if (map[row][col] == 0) {
                        map[row][col] = map[thisTurnRow][thisTurnCol];
                        visited[row][col] = thisTurnLength + 1;
                        if (thisTurnLength + 1 >= result) {
                            continue;
                        }
                        queue.add(new int[]{row, col, thisTurnLength + 1});
                    } else {
                        if (map[row][col] != map[thisTurnRow][thisTurnCol]) {
                            result = Math.min(result, visited[row][col] + visited[thisTurnRow][thisTurnCol] - 2);
                        }
                    }
                }
            }
        }

    }

    private static void init() {
        queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && visited[i][j] == 0) {
                    map[i][j] = ++groundCount;
                    visited[i][j] = 1;
                    dfs(i, j);
                }
            }
        }
    }

    private static void dfs(int thisRow, int thisCol) {
        queue.add(new int[]{thisRow, thisCol, 1});
        for (int i = 0; i < 4; i++) {
            int row = thisRow + dx[i];
            int col = thisCol + dy[i];
            if (0 <= row && row < n && 0 <= col && col < n) {
                if (visited[row][col] == 0 && map[row][col] == 1) {
                    visited[row][col] = 1;
                    map[row][col] = groundCount;
                    dfs(row, col);
                }
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
