import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] cost;
    static int[][] dp;
    static Stack<int[]> queue;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[n][(int) Math.pow(2, n)];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        queue = new Stack<>();

        queue.add(new int[]{0, 1, 0});

        while (!queue.isEmpty()) {
            int[] thisTurn = queue.pop();
            int thisPlace = thisTurn[0];
            int thisBit = thisTurn[1];
            int thisLen = thisTurn[2];

            for (int i = 0; i < n; i++) {
                if ((thisBit & (1 << i)) == 0) {
                    if (cost[thisPlace][i] > 0) {
                        if (dp[i][thisBit | (1 << i)] > thisLen + cost[thisPlace][i]) {
                            dp[i][thisBit | (1 << i)] = thisLen + cost[thisPlace][i];
                            queue.add(new int[]{i, thisBit | (1 << i), thisLen + cost[thisPlace][i]});
                        }
                    }

                }
            }

        }

        for (int i = 0; i < n; i++) {
            if (cost[i][0] > 0) {
                if (dp[i][(int) Math.pow(2, n) - 1] != Integer.MAX_VALUE) {
                    result = Math.min(dp[i][(int)Math.pow(2,n) - 1] + cost[i][0], result);

                }
            }

        }
        System.out.println(result);


    }
}