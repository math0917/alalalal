import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int testCase, n, m, c;


    static int[][] honey;
    static int result;
    static int[] a;
    static int[] b;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            honey = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    honey[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            a = new int[m];
            b = new int[m];
            result = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j + m - 1< n; j++) {
                    for (int k = j + m; k + m - 1 < n; k++) {
                        cal(i, j, i, k);
                    }
                    for (int row = i + 1; row < n; row++) {
                        for (int k = 0; k + m - 1 < n; k++) {
                            cal(i, j, row, k);
                        }
                    }
                }
            }
            System.out.println("#" + t + " "+ result);
        }


    }

    private static void cal(int aR, int aC, int bR, int bC) {
//        System.out.println(Arrays.toString(a));
        for (int j = 0; j < m; j++) {
            a[j] = honey[aR][aC + j];
        }
        for (int j = 0; j < m; j++) {
            b[j] = honey[bR][bC + j];
        }
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));


        long sum = 0;

        for (int i = 1; i < (int) Math.pow(2, m); i++) {
            sum = 0;
            sum += dfsA(i);
            for (int j = 1; j < (int) Math.pow(2, m); j++) {
                sum += dfsB(j);
                result = (int)Math.max(result, sum);
                sum -= dfsB(j);
            }
        }

    }

    private static int dfsB(int bit) {
        int sum = 0;
        int val = 0;
        for (int i = 0; i < m; i++) {
            if ((bit & (1 << i)) != 0) {
                sum += b[i];
                val += b[i] * b[i];
            }
        }
        if (sum <= c) {
            return val;
        } return Integer.MIN_VALUE;
    }

    private static int dfsA(int bit) {
        int sum = 0;
        int val = 0;
        for (int i = 0; i < m; i++) {
            if ((bit & (1 << i)) != 0) {
                sum += a[i];
                val += a[i] * a[i];
            }
        }
        if (sum <= c) {
            return val;
        } return Integer.MIN_VALUE;
    }
}