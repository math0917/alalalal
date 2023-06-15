import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int n;
    static int[][] dist;
    static boolean[] visited;
    static int[][] dp;
    static int result = Integer.MAX_VALUE;
    static Queue<int[]> queue;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        dist = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = Integer.MAX_VALUE;
            }
            dp[0][i] = 0;
        }


        for (int i = 1; i <n ; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (dist[j][k] != 0) {
                        if (dp[i - 1][j] != Integer.MAX_VALUE) {
                            dp[i][k] = Math.min(dp[i - 1][j] + dist[j][k], dp[i][k]);

                        }
                    }
                }
            }

        }
//        System.out.println(Arrays.deepToString(dp));
        int asInt = Arrays.stream(dp[n - 1]).min().getAsInt();
        System.out.println(asInt == Integer.MAX_VALUE?-1:asInt
        );

    }


}
