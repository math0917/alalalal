
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    static int n, m;
    static Node[] graph;
    static long[] dist;
    static int[] parent;
    static int count;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        count = 0;
        graph = new Node[m];
        dist = new long[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Long.MIN_VALUE;
            parent[i] = i;
        }
        dist[0] = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            graph[i] = (new Node(fr, to, weight));
        }

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                Node node = graph[j];

                if (dist[node.from] != Long.MIN_VALUE && dist[node.from] + node.weight > dist[node.to]) {
                    dist[node.to] = dist[node.from] + node.weight;
                    parent[node.to] = node.from;
                }
            }
        }

//        System.out.println(Arrays.toString(dist));
        for (int j = 0; j < m; j++) {
            Node node = graph[j];
//            System.out.println(dist[node.from] + node.weight +" " + dist[node.to]);
            if (dist[node.from] != Long.MIN_VALUE && dist[node.from] + node.weight > dist[node.to] && isPath(node.to)) {

                System.out.println(-1);
                System.exit(0);


            }
        }
        int par = n - 1;
        Stack<Integer> stack = new Stack<>();
        while (par != 0) {
            stack.add(par + 1);
            par = parent[par];
        }
        stack.add(1);
        while (!stack.isEmpty()) {
            sb.append(stack.pop()+ " ");
        }
        System.out.println(sb);
    }

    private static boolean isPath(int to) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        visited[to] = true;
        queue.add(to);
        while (!queue.isEmpty()) {
            Integer thisTurn = queue.poll();
            for (Node next : graph) {
                if (next.from == thisTurn) {
                    if (!visited[next.to]) {
                        visited[next.to] = true;
                        queue.add(next.to);
                    }
                }
            }
        }
        return visited[n - 1];
    }


}

class Node {
    int from;
    int to;
    int weight;

    public Node(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

}