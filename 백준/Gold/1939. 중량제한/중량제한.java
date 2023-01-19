

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] parent;
    static PriorityQueue<Node> pq;
    static ArrayList<Node>[] graph;
    static int count;
    static int aim1, aim2;
    static int result = 0;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
            graph[i] = new ArrayList<>();
        }
        count = 0;
        pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.add(new Node(fr, to, weight));
        }
        st = new StringTokenizer(br.readLine());
        aim1 = Integer.parseInt(st.nextToken());
        aim2 = Integer.parseInt(st.nextToken());
        while (count < n-1 && (!pq.isEmpty())) {
            Node node = pq.poll();
            if (find(node.fr) != find(node.to)) {
                union(node.fr, node.to);
                graph[node.fr].add(new Node(node.fr, node.to, node.weight));
                graph[node.to].add(new Node(node.to, node.fr, node.weight));
            }
        }
        visited = new boolean[n + 1];
        visited[aim1] = true;
        dfs(aim1,Integer.MAX_VALUE);
        System.out.println(result);
    }

    private static void dfs(int idx, int value) {
        if (idx == aim2) {
            result = Math.max(value, result);
            return;
        }
        for (Node next : graph[idx]) {
            if (!visited[next.to]) {
                visited[next.to] = true;
                dfs(next.to, Math.min(value, next.weight));
                visited[next.to] = false;
            }
        }
    }

    static int find(int num) {
        if (parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }

    static void union(int num1, int num2) {
        int par1 = find(num1);
        int par2 = find(num2);

        if (par1 != par2) {
            count += 1;
            parent[Math.max(par1, par2)] = Math.min(par1, par2);
        }
    }
    static class Node implements Comparable<Node>{
        int fr;
        int to;
        int weight;

        public Node(int fr, int to, int weight) {
            this.fr = fr;
            this.to = to;
            this.weight = weight;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(o.weight, weight);
        }
    }
}
