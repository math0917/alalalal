

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];

        int maxDp = (int) Math.sqrt(n);

        for (int i = 1; i <= maxDp; i++) {

            dp[i * i] = 1;
        }

        System.out.println(findValue(n));


    }

    private static int findValue(int n) {
        if (dp[n] > 0) {
            return dp[n];
        }
        int maxDp = (int) Math.sqrt(n) ;
        int minValue = Integer.MAX_VALUE;
        for (int j = maxDp; j >= 1; j--) {
            minValue = Math.min(findValue(n - j * j) + 1, minValue);
        }
        return dp[n] = minValue;
    }
}
