import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static char[] result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line1 = br.readLine();
        String line2 = br.readLine();
        dp = new int[line1.length() + 1][line2.length() + 1];
        int maxValue = 0;

        for (int i = 1; i <= line1.length(); i++) {
            for (int j = 1; j <= line2.length(); j++) {
                if (line1.charAt(i - 1) == line2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        int row = line1.length();
        int col = line2.length();
        maxValue = dp[row][col];
        result = new char[maxValue];
        while (row >= 1 && col >= 1) {
            if (dp[row][col] == dp[row - 1][col]) {
                row --;
            } else if (dp[row][col - 1] == dp[row][col]) {
                col--;
            } else {
                result[--maxValue] = line1.charAt(row - 1);
                row--;
                col--;
            }
        }
        System.out.println(result.length);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
        }

    }
}
