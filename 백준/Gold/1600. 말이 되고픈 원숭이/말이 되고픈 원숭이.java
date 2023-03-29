import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int k, n, m;
    static int[][] ground;
    static int[][][] dp;
    static Queue<int[]> queue = new ArrayDeque<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] horseDx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] horseDy = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken()
        );
        n = Integer.parseInt(st.nextToken());
        ground = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[n][m][k+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        dp[0][0][0] = 0;
        queue.add(new int[]{0, 0, 0,0});
        while (!queue.isEmpty()) {
            int[] thisTurn = queue.poll();
            int thisRow = thisTurn[0];
            int thisCol = thisTurn[1];
            int thisK = thisTurn[2];
            int thisMove = thisTurn[3];
//            System.out.println(thisRow + " "+thisCol + " "+thisK+" "+thisMove);
            for (int k = 0; k < 4; k++) {
                int row = thisRow + dx[k];
                int col = thisCol + dy[k];
                if (0 <= row && row < n && 0 <= col && col < m) {
                    if (ground[row][col] != 1) {
                        if (dp[row][col][thisK] > thisMove + 1) {
                            dp[row][col][thisK] = thisMove + 1;
                            queue.add(new int[]{row, col, thisK, thisMove + 1});
                        }
                    }

                }
            }
            if (thisK < k) {
                for (int k = 0; k < 8; k++) {
                    int row = thisRow + horseDx[k];
                    int col = thisCol + horseDy[k];
                    if (0 <= row && row < n && 0 <= col && col < m) {
                        if (ground[row][col] != 1) {
                            if (dp[row][col][thisK+ 1] > thisMove + 1) {
                                dp[row][col][thisK + 1] = thisMove + 1;
                                queue.add(new int[]{row, col, thisK + 1, thisMove + 1});
                            }
                        }

                    }
                }
            }


        }
        int val = Arrays.stream(dp[n-1][m-1]).min().getAsInt();
        System.out.println( val == Integer.MAX_VALUE ? -1 : val );

    }
}