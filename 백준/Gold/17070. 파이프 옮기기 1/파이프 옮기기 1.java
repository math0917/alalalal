import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] pipe;
    static int[][][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        pipe = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                pipe[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][n][3];



        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 1; j < n; j++) {

                if (i == 0 ) {
                    if (pipe[i][j] == 1) {
                        flag = false;
                    }
                    if (flag) {
                        dp[0][j][0] = 1;
                    }
                } else {
                    dp[i][j][0] = pipe[i][j] == 1 ? 0 : dp[i][j - 1][0] + dp[i][j-1][1];
                    dp[i][j][1] = pipe[i][j] == 1 || pipe[i - 1][j] == 1 || pipe[i][j - 1] == 1 ? 0 : dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                    dp[i][j][2] = pipe[i][j] == 1 ? 0 : dp[i-1][j][1] + dp[i-1][j][2];
                }
            }
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(Arrays.toString(dp[i][j])+ " ");
//            }
//            System.out.println();
//        }
        System.out.println(Arrays.stream(dp[n-1][n-1]).sum());
    }
}