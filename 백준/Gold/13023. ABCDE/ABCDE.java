import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static Set<Integer>[] friend;
    static boolean[] visited;
    static boolean flag;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        friend = new HashSet[n];
        for (int i = 0; i < n; i++) {
            friend[i] = new HashSet<>();
        }
        visited = new boolean[n];
        flag = true;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            friend[fr].add(to);
            friend[to].add(fr);
        }

        for (int i = 0; i < n && flag; i++) {
            visited[i] = true;
            dfs(i, 1);
            visited[i] = false;
        }
        if (flag) {
            System.out.println(0);
        } else {
            System.out.println(1);

        }


    }

    private static void dfs(int from, int count) {
        if (count == 5) {
            flag = false;
            return;
        }
        for (int a : friend[from]) {
            if (!visited[a]) {
                visited[a] = true;
                dfs(a, count + 1);
                visited[a] = false;
            }
        }
    }
}