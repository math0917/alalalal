import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Main {
    static int n, m;
    static char[][] map;
    static Queue<int[]> queue = new ArrayDeque<>();

    static Queue<int[]> dQueue = new ArrayDeque<>();
    static int[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '*') {
                    queue.add(new int[]{i, j});
                    visited[i][j] = 2;
                } else if (map[i][j] == 'S') {
                    dQueue.add(new int[]{i, j});
                    visited[i][j] = 1;
                }
            }
        }

        int count = 1;
        while (dQueue.size() > 0) {
            int queueSize = queue.size();
            while (queueSize-- > 0) {
                int[] thisTurn = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int row = thisTurn[0] + dx[k];
                    int col = thisTurn[1] + dy[k];
                    if (0 <= row && row < n && 0 <= col && col < m && visited[row][col] <= 1 && map[row][col] == '.') {
                        visited[row][col] = 2;
                        queue.add(new int[]{row, col});
                    }
                }
            }

            int dQueueSize = dQueue.size();
            while (dQueueSize-- > 0) {
                int[] thisTurn = dQueue.poll();
                for (int k = 0; k < 4; k++) {
                    int row = thisTurn[0] + dx[k];
                    int col = thisTurn[1] + dy[k];
                    if (0 <= row && row < n && 0 <= col && col < m && visited[row][col] == 0 ) {
                        if (map[row][col] == '.') {
                            visited[row][col] = 1;
                            dQueue.add(new int[]{row, col});
                        } else if (map[row][col] == 'D') {
                            System.out.println(count);
                            System.exit(0);
                        }

                    }
                }
            }
            count++;
        }
        System.out.println("KAKTUS");

    }
}