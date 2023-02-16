
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int testCase;
    static int n,l;
    static int[][] food;
    static int result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            food = new int[n][2];
            result = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 2; j++) {
                    food[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int value = (1 << n);
            for (int i = 0; i < value; i++) {
                int cal = 0;
                int flavor = 0;
                for (int j = 0; j < n; j++) {

                    if (((1 << j) & i) != 0) {
                        flavor += food[j][0];
                        cal += food[j][1];
                    }
                }
                if (cal <= l) {
                    result = Math.max(result, flavor);
                }
            }
            System.out.println("#"+ t+" "+ result);
        }
    }
}
