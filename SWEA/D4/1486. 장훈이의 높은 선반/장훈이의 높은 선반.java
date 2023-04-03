import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Solution {
    static int testCase;
    static int n, k;
    static int[] val;
    static int result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            val = new int[n];
            result = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                val[i] = Integer.parseInt(st.nextToken());
            }


            dfs(0, 0);
            System.out.println("#"+t+" " +result);
        }
    }

    private static void dfs(int i, int v) {
        if (v >= k) {
            result = Math.min(v - k, result);
            return;
        }
        if (i >= n) {
            return;
        }
        dfs(i + 1, v + val[i]);
        dfs(i + 1, v);
    }
}