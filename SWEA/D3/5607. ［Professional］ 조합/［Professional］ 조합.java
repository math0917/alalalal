import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Solution {
    static int testCase;
    static final int MOD = 1234567891;
    static int n, r;
    static long[] factorial = new long[4000001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());
        factorial[1] = 1;
        for (int i = 2; i < 4000001; i++) {
            factorial[i] = (factorial[i-1] * i )%MOD;
        }
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            long N = factorial[n];
            long R = factorial[r];
            R = (R * factorial[n - r]) % MOD;
            System.out.println("#"+t + " "+ N * pow(R, MOD - 2) % MOD);
        }
    }

    private static long pow(long r, int i) {
        if (i == 1) {
            return r;
        }
        long a = pow(r, i / 2);
        if (i % 2 == 1) {
            return a * a % MOD * r % MOD;
        }
        return a * a % MOD;
    }
}