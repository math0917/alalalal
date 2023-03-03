import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, h;
    static int[][][] tomato;
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int days = 0;
    static int ripe = 0;
    static int cantRipe = 0;
    static Queue<int[]> queue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        tomato = new int[m][n][h];
        queue = new ArrayDeque<>();
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                    if (tomato[i][j][k] == 1) {
                        ripe += 1;

                        queue.add(new int[]{i, j, k});
                    } else if (tomato[i][j][k] == -1) {
                        cantRipe += 1;
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            if (ripe == n * m * h - cantRipe) {
                System.out.println(days);
                System.exit(0);
            }
            days ++;
            int daySize = queue.size();
            int beforeRipe = ripe;
            for (int p = 0; p < daySize; p++) {
                int[] info = queue.poll();
                for (int k = 0; k < dx.length; k++) {
                    int row = info[0] + dx[k];
                    int col = info[1] + dy[k];
                    int height = info[2] + dz[k];
                    if (canGo(row, col, height)) {
                        if (tomato[row][col][height] == 0) {
                            ripe += 1;
                            tomato[row][col][height] = 1;
                            queue.add(new int[]{row, col, height});
                        }
                    }
                }
            }

            if (ripe == beforeRipe && ripe != n * m * h - cantRipe) {
                System.out.println(-1);
                System.exit(0);
            }
        }

        System.out.println(-1);




    }

    private static boolean canGo(int row, int col, int height) {
        return 0 <= row && row < m && 0 <= col && col < n && 0 <= height && height < h;
    }
}