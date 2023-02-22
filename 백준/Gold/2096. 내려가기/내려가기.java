import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] line;
    static int[][] maxDp;
    static int[][] minDp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        line = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                line[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        maxDp = new int[n][3];
        minDp = new int[n][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                minDp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int j = 0; j < 3; j++) {
            maxDp[0][j] = line[0][j];
            minDp[0][j] = line[0][j];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i - 1][0] + line[i][j]);
                minDp[i][j] = Math.min(minDp[i][j], minDp[i - 1][0] + line[i][j]);
            }
            for (int j = 1; j < 3; j++) {
                maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i - 1][2] + line[i][j]);
                minDp[i][j] = Math.min(minDp[i][j], minDp[i - 1][2] + line[i][j]);
            }
            for (int j = 0; j < 3; j++) {
                maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i - 1][1] + line[i][j]);
                minDp[i][j] = Math.min(minDp[i][j], minDp[i - 1][1] + line[i][j]);
            }
        }
        System.out.println(Arrays.stream(maxDp[n-1]).max().getAsInt() + " "+ Arrays.stream(minDp[n-1]).min().getAsInt());
    }
}