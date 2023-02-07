

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] result;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        result = new int[m];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {

            dfs(i, 0);
        }

    }

    private static void dfs(int num, int length) {
        visited[num] = true;
        result[length] = num;
        if (length + 1 == m) {
            print();

        } else {
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(i, length + 1);
                }
            }
        }
        visited[num] = false;
    }

    private static void print() {
        for (int i = 0; i < m; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }
}
