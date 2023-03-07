import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static Queue<int[]> queue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0});
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (poll[0] == n - 1 && poll[1] == m - 1) {
                System.out.println(poll[2]);
                break;
            }
            int idx = 0;
            while (idx++ < map[poll[0]][poll[1]]) {
                int row = poll[0] + dx[0] * idx;
                int col = poll[1] + dy[0] * idx;
                if (0 <= row && row < n && 0 <= col && col < m && !visited[row][col]) {
                    visited[row][col] = true;
                    queue.add(new int[]{row, col, poll[2] + 1});
                }
                row = poll[0] + dx[1] * idx;
                col = poll[1] + dy[1] * idx;
                if (0 <= row && row < n && 0 <= col && col < m && !visited[row][col]) {
                    visited[row][col] = true;
                    queue.add(new int[]{row, col, poll[2] + 1});
                }
            }
        }
    }
}