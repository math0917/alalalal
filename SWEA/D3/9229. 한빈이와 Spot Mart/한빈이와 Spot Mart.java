
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int t;
    static int n, m;
    static int[] bag;
    static int result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= t; testCase++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            bag = new int[n];
            for (int i = 0; i < n; i++) {
                bag[i] = Integer.parseInt(st.nextToken());
            }
            result = Integer.MIN_VALUE;
            start(0,  0, 0);
            System.out.println("#" + testCase + " " + (result == Integer.MIN_VALUE ? -1 : result));
        }

    }

    private static void start(int start,  int count, int weight) {
        if (weight <= m && count == 2) {
            result = Math.max(weight, result);
            return;
        }
        for (int i = start; i < n; i++) {
            if (weight + bag[i] <= m) {
                start(i + 1, count + 1, weight + bag[i]);
            }
        }
    }
}
