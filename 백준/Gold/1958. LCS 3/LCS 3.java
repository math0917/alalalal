
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String line1, line2, line3;
    static int[][][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line1 = br.readLine();
        line2 = br.readLine();
        line3 = br.readLine();

        dp = new int[line1.length() + 1][line2.length() + 1][line3.length() + 1];

        for (int i = 1; i <= line1.length(); i++) {
            for (int j = 1; j <= line2.length(); j++) {
                for (int k = 1; k <= line3.length(); k++) {
                    if (line1.charAt(i - 1) == line2.charAt(j - 1) && line2.charAt(j - 1) == line3.charAt(k - 1)) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(Math.max(dp[i - 1][j][k], dp[i][j - 1][k]), dp[i][j][k - 1]);
                    }
                }
            }
        }
        System.out.println(dp[line1.length()][line2.length()][line3.length()]);

    }
}
