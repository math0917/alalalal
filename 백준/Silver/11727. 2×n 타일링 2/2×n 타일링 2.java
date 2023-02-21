import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        dp[1] = 1;
        if (n == 1) {
            System.out.println(1);
            System.exit(0);
        }
        dp[2] = 3;
        if (n == 2) {
            System.out.println(3);
            System.exit(0);
        }
        for (int i = 3; i <= n; i++) {
            dp[i] = ((dp[i - 2] * 2) % 10007 + dp[i - 1]) % 10007;
        }
        System.out.println(dp[n]);


    }
}