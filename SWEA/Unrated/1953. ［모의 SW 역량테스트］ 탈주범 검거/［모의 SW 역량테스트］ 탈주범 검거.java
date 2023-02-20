
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int testCase;
    static int n,m,r,c, k;
    static int[][] tunnel;
    static Map<Integer, Integer> map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            tunnel = new int[n][m];
            map = new HashMap<>();
            map.put(1, 15);
            map.put(2, 5);
            map.put(3, 10);
            map.put(4, 3);
            map.put(5, 6);
            map.put(6, 12);
            map.put(7, 9);

            visited = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    tunnel[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{r, c, 1});
            visited[r][c] = true;
            while (!queue.isEmpty()) {
                int[] thisTurn = queue.poll();
                int thisRow = thisTurn[0];
                int thisCol = thisTurn[1];
                int thisLength = thisTurn[2];
                if (thisLength == k) {
                    continue;
                }
                int bit = map.get(tunnel[thisRow][thisCol]);

                for (int i = 0; i < 4; i++) {
                    if (((1 << i) & bit) != 0) {
                        int row = thisRow + dx[i];
                        int col = thisCol + dy[i];
                        int canGo = (i + 2) % 4;
                        if (0 <= row && row < n && 0 <= col && col < m) {
                            if (tunnel[row][col] > 0) {
                                if ((map.get(tunnel[row][col]) & (1 << canGo)) != 0 && !visited[row][col]) {
                                    visited[row][col] = true;
                                    queue.add(new int[]{row, col, thisLength + 1});
                                }
                            }

                        }

                    }
                }

            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    count += visited[i][j] ? 1 : 0;
                }
            }
            System.out.println("#"+t+" "+count);


        }
    }
}
