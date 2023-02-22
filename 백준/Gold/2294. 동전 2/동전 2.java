import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static long[] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new long[k+1];
        for (int i = 1; i < k + 1; i++) {
            dp[i] = Long.MAX_VALUE;
        }
        for (int i = 0; i < n; i++) {
            int money = Integer.parseInt(br.readLine());
            for (int j = 1; j < k + 1; j++) {
                dp[j] = Math.min(dp[j], j - money >= 0 && dp[j-money] != Long.MAX_VALUE ? dp[j - money] + 1 : Long.MAX_VALUE);
            }
        }
//        System.out.println(Arrays.toString(dp));
        if (dp[k] == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }

}