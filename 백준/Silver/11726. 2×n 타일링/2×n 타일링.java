
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        if (n == 1){
            System.out.println(1);
            return;
        }
        if (n == 2){
            System.out.println(2);
            return;
        }
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            dp[i] = (dp[i-2] + dp[i-1] );
            dp[i] %= 10007;
        }
        System.out.println(dp[n]);
    }
}
