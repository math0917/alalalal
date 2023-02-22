import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        dp = new int[n][10];
        for (int i = 0; i < 10; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                int sum = 0;
                for (int k = 0; k <= j; k++) {
                    sum += dp[i - 1][k];
                }
                dp[i][j] = sum % 10007;
            }
        }
        System.out.println(Arrays.stream(dp[n-1]).sum()%10007);
    }
}