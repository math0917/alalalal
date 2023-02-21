import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] coin;
    static int[][] dp;
    static int result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coin = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[n][k + 1];

        Arrays.sort(coin);
        int j = 0;
        while (coin[0]*j < k + 1) {
            dp[0][coin[0]*(j++)] += 1;
            if (coin[0] * j == k) {
                result = 1;
                dp[0][coin[0] *j] +=1;
                break;
            } else {
                result = 0;
            }
        }
        for (int i = 1; i < n; i++) {
            int value = coin[i];
            if (value > k) {
                break;
            }
            for (j = 0; j < value; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            for (j = value; j < k + 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - value];
                result = dp[i][j];
            }
        }


        System.out.println(result);

    }
}