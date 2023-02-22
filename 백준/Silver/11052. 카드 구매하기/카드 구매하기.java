import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] card;
    static int[] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        card = new int[n];
        for (int i = 0; i < n; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }



        dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                int index = j + 1;
                dp[i] = Math.max(dp[i], dp[i - index] + card[j]);
            }
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[n]);

    }
}