

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] recipe;
    static int sour;
    static int bitten;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        recipe = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                recipe[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sour = 1;
        bitten = 0;

        dfs (0);
        System.out.println(result);
    }

    private static void dfs(int start) {
        for (int i = start; i < n; i++) {
            sour *= recipe[i][0];
            bitten += recipe[i][1];
            result = Math.min(Math.abs(bitten - sour), result);
            dfs( i + 1);
            sour /= recipe[i][0];
            bitten -= recipe[i][1];

        }
    }
}
