import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] light;

    static PriorityQueue<int[]> queue;
    static int[] dx = {0, 1, 0, -1, 1, -1, -1, 1};
    static int[] dy = {-1, 0, 1, 0, 1, -1, 1, -1};
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n][m][2];
        light = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                light[i][j] = (line.charAt(j) == '/') ? 0 : 1;
            }
        }







//        System.out.println(count[0][0][1]);

        queue = new PriorityQueue<>(Comparator.comparing(a -> a[2]));

        queue.add(new int[]{0, 0, light[0][0] == 0 ? 1 : 0, 1});
        visited[0][0][0] = true;
        visited[0][0][1] = true;
        while (!queue.isEmpty()) {
            int[] thisTurn = queue.poll();
            int thisRow = thisTurn[0];
            int thisCol = thisTurn[1];
            if (thisRow == n - 1 && thisCol == m - 1 && thisTurn[3] == 1) {
                System.out.println(thisTurn[2]);
                System.exit(0);
            }
            int thisLength = thisTurn[2];
            int thisDir = thisTurn[3];

            for (int k = 0; k < 4; k++) {
                int row = thisRow + dx[k];
                int col = thisCol + dy[k];
                if (0 <= row && row < n && 0 <= col && col < m) {
                    if (visited[row][col][(thisDir+1)%2]) continue;
                    visited[row][col][(thisDir+1)%2] = true;
                    if (thisDir == light[row][col]) {
                        queue.add(new int[]{row, col, thisLength + 1, (thisDir + 1) % 2});
                    } else {
                        queue.add(new int[]{row, col, thisLength, (thisDir + 1) % 2});

                    }

                }
            }
            if (thisDir == 1) {
                for (int k = 4; k < 6; k++) {
                    int row = thisRow + dx[k];
                    int col = thisCol + dy[k];
                    if (0 <= row && row < n && 0 <= col && col < m) {

                        if (visited[row][col][thisDir]) continue;
                        visited[row][col][thisDir] = true;
                        if (thisDir == light[row][col]) {
                            queue.add(new int[]{row, col, thisLength , thisDir});
                        } else {
                            queue.add(new int[]{row, col, thisLength + 1, thisDir });

                        }

                    }
                }
            } else{
                for (int k = 6; k < 8; k++) {
                    int row = thisRow + dx[k];
                    int col = thisCol + dy[k];
                    if (0 <= row && row < n && 0 <= col && col < m) {

                        if (visited[row][col][thisDir]) continue;
                        visited[row][col][thisDir] = true;
                        if (thisDir == light[row][col]) {
                            queue.add(new int[]{row, col, thisLength , thisDir});
                        } else {
                            queue.add(new int[]{row, col, thisLength + 1, thisDir });

                        }

                    }
                }
            }

        }

        System.out.println("NO SOLUTION");


    }
}