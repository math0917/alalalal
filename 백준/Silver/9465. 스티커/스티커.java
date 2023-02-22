import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int testCase;
    static int n;
    static int[][] sticker;
    static int[][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCase; t++) {
            n = Integer.parseInt(br.readLine());
            sticker = new int[n][2];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    sticker[j][i] = Integer.parseInt(st.nextToken());
                }
            }
            dp = new int[n + 1][4];

            for (int i = 1; i < n + 1; i++) {
                int realIdx = i - 1;
                dp[i][0] = Math.max(dp[i - 1][2], dp[i - 1][3]) + sticker[realIdx][0];
                dp[i][1] = Arrays.stream(dp[i-1]).max().getAsInt();
                dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]) + sticker[realIdx][1];
                dp[i][3] = Arrays.stream(dp[i - 1]).max().getAsInt();
            }

            System.out.println(Arrays.stream(dp[n]).max().getAsInt());
        }

    }
}