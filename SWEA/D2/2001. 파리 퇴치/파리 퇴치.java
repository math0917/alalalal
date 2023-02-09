
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.OptionalInt;
import java.util.StringTokenizer;

public class Solution {
    static int testCase;
    static int n, m;
    static int[][] map;
    static int result;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            result = Integer.MIN_VALUE;
            for (int i = 0; i < n - m + 1; i++) {
                for (int j = 0; j < n - m + 1; j++) {
                    find(i, j);
                }
            }
            System.out.println("#" + t+ " " +result);
        }

    }

    private static void find(int thisRow, int thisCol) {
        int thisResult = 0;

        for (int row = thisRow; row < thisRow + m; row++) {
            for (int col = thisCol; col < thisCol + m; col++) {
                thisResult += map[row][col];
            }
        }
        result = Math.max(thisResult, result);
    }
}
