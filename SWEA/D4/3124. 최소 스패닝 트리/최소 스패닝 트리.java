import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int testCase;
    static int v, e;
    static ArrayList<Edge>[] graph;
    static int[] parent;
    static PriorityQueue<Edge> pq;
    static long result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            graph = new ArrayList[v + 1];
            parent = new int[v + 1];

            for (int i = 1; i < v + 1; i++) {
                graph[i] = new ArrayList<>();
                parent[i] = i;
            }

            pq = new PriorityQueue<>();
            result = 0;
            for (int i = 1; i <= e; i++) {
                st = new StringTokenizer(br.readLine());
                int fr = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph[fr].add(new Edge(fr, to, weight));
                graph[to].add(new Edge(to, fr, weight));
                pq.add(new Edge(fr, to, weight));
            }

            start();
            System.out.println("#"+t + " "+result);
        }

    }

    private static void start() {
        while (!pq.isEmpty()) {
            Edge thisTurn = pq.poll();
            if (find(thisTurn.to) != find(thisTurn.from)) {
                union(thisTurn.to, thisTurn.from);
                result += thisTurn.weight;

            }

        }
    }

    private static int find(int num1) {
        if (parent[num1] == num1) {
            return num1;
        }
        return parent[num1] = find(parent[num1]);

    }

    private static void union(int num1, int num2) {
        int par1 = find(num1);
        int par2 = find(num2);
        parent[Math.max(par1, par2)] = Math.min(par1, par2);
    }
}

class Edge implements Comparable<Edge> {

    int from;
    int to;
    long weight;

    public Edge(int from, int to, long weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(weight, o.weight);
    }
}