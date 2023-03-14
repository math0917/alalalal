import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m, k;
    static int[][] ground;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        ground = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int val = m;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ground[i][j] == 0 && !visited[i][j]) {
                    int count = 1;

                    Queue<int[]> queue = new ArrayDeque<>();
                    visited[i][j] = true;
                    queue.add(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] poll = queue.poll();
                        for (int t = 0; t < 4; t++) {
                            int row = poll[0] + dx[t];
                            int col = poll[1] + dy[t];
                            if (0 <= row && row < n && 0 <= col && col < n) {
                                if (ground[row][col] == 0 && !visited[row][col]) {
                                    visited[row][col]= true;
                                    count ++;
                                    queue.add(new int[]{row, col});
                                }
                            }
                        }
                    }
                    if (count % k == 0) {
                        m -= count / k;
                    } else {
                        m -= count/k + 1;
                    }
                }
            }
        }
        if (m < 0 || val == m) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println("POSSIBLE");
            System.out.println(m);
        }
    }
}