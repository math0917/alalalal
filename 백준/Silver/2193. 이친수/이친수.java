import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static long[][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        dp = new long[n + 1][2];
        dp[1][0] = 0;
        dp[1][1] = 1;

        for (int i = 2; i < n + 1; i++) {
            dp[i][1] += dp[i - 1][0];
            dp[i][0] += dp[i - 1][1] + dp[i - 1][0];
        }

        System.out.println(Arrays.stream(dp[n]).sum());

    }
}