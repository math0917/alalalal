import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb;
    static int testCase;
    static int[] file;
    static int[][] dp;
    static int[] sum;
    static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCase = Integer.parseInt(br.readLine());

        for (int p = 1; p <= testCase; p++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            file = new int[n];
            sum = new int[n];
            dp = new int[n][n];
            int value = 0;
            for (int i = 0; i < n; i++) {
                file[i] = Integer.parseInt(st.nextToken());
                value += file[i];
                sum[i] = value;
                if (i >= 1) {
                    dp[i - 1][i] = sum[i] - (i - 2 >= 0 ? sum[i - 2] : 0);
                }
            }

            for (int k = 2; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    if (i + k < n) {
                        int minValue = Integer.MAX_VALUE;
                        for (int j = i; j < i + k; j++) {
                            minValue = Math.min(minValue, dp[i][j] + dp[j + 1][i + k]);
                        }
                        dp[i][i + k] = minValue + sum[i + k] - (i - 1 >= 0 ? sum[i - 1] : 0);
                    }
                }
            }
            System.out.println(dp[0][n-1]);


        }

    }
}