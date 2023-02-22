import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static int[][] square;
    static int[][] dp;
    static int result = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        square = new int[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                square[i][j] = line.charAt(j) - '0';
            }
        }
        dp = new int[r][c];

        for (int j = 0; j < c; j++) {
            dp[0][j] = square[0][j];
        }
        for (int i = 1; i < r; i++) {
            dp[i][0] = square[i][0];
            for (int j = 1; j < c; j++) {
                dp[i][j] = square[i][j] == 1 ? Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1 : 0;
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }
        System.out.println(result * result);
    }
}