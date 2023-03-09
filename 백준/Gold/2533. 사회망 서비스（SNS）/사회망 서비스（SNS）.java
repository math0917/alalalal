import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] dp;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        dp = new int[n][2];
        
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()) - 1;
            int n2 = Integer.parseInt(st.nextToken()) - 1;
            graph[n1].add(n2);
            graph[n2].add(n1);
        }
        visited = new boolean[n];
        visited[0] = true;

        dfs(0);
        System.out.println(Math.min(dp[0][0], dp[0][1]));
    }

    private static void dfs(int root) {
        int minZero = 0;
        int minOne = 0;
        for (int a : graph[root]) {
            if (!visited[a]) {
                visited[a] = true;
                dfs(a);
            }
            minZero += Math.min(dp[a][0], dp[a][1]);
            minOne += dp[a][0];
        }
        dp[root][0] = minZero + 1;
        dp[root][1] = minOne;


    }
}