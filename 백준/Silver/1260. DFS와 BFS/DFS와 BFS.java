import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    static PriorityQueue<Integer>[] dfsGraph;
    static PriorityQueue<Integer>[] bfsGraph;

    static int n, m, start;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        dfsGraph = new PriorityQueue[n + 1];
        bfsGraph = new PriorityQueue[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dfsGraph[i] = new PriorityQueue<>();
            bfsGraph[i] = new PriorityQueue<>();

        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            dfsGraph[s].add(f);
            dfsGraph[f].add(s);
            bfsGraph[s].add(f);
            bfsGraph[f].add(s);
        }

        visited = new boolean[n + 1];
        visited[start] = true;
        dfs(start);
        visited = new boolean[n + 1];
        System.out.println();
        visited[start] = true;
        bfs(start);

    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Integer v1 = queue.poll();
            System.out.print(v1 + " ");
            PriorityQueue<Integer> pq = bfsGraph[v1];
            while (!pq.isEmpty()) {
                Integer v2 = pq.poll();
                if (!visited[v2]) {
                    visited[v2] = true;
                    queue.add(v2);
                }
            }
        }
    }

    private static void dfs(int vertex) {
        System.out.print(vertex+ " ");
        PriorityQueue<Integer> pq = dfsGraph[vertex];
        while (!pq.isEmpty()) {
            Integer v1 = pq.poll();
            if (!visited[v1]) {
                visited[v1] = true;
                dfs(v1);
            }
        }
    }
}