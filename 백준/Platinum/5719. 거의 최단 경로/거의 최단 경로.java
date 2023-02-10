
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Edge>[] graph;
    static int frAim;
    static int toAim;
    static PriorityQueue<Edge> pq;
    static int[] dist;
    static List<Integer>[] parent;
    static Set<Integer>[] cantVisit;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (; ; ) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                break;
            }
            st = new StringTokenizer(br.readLine());
            frAim = Integer.parseInt(st.nextToken());
            toAim = Integer.parseInt(st.nextToken());
            graph = new ArrayList[n];
            parent = new ArrayList[n];
            cantVisit = new HashSet[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
                parent[i] = new ArrayList<>();
                cantVisit[i] = new HashSet<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int fr = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int length = Integer.parseInt(st.nextToken());
                graph[fr].add(new Edge(fr, to, length));
            }



            dist = new int[n];
            dijkstra();
            dfs(toAim);
//            System.out.println(Arrays.toString(cantVisit));
            dijkstraLast();
            if (dist[toAim] == Integer.MAX_VALUE) {
                sb.append(-1+"\n");
            } else {
                sb.append(dist[toAim]+"\n");
            }
        }
        System.out.println(sb);
    }

    private static void dijkstraLast() {
        pq = new PriorityQueue<>();
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[frAim] = 0;
        for (Edge edge : graph[frAim]) {
            if (!cantVisit[edge.from].contains(edge.to)) {
                pq.add(edge);
                dist[edge.to] = edge.weight;
            }
        }
        while (!pq.isEmpty()) {
            Edge thisEdge = pq.poll();
            for (Edge next : graph[thisEdge.to]) {
                if (!cantVisit[next.from].contains(next.to)) {
                    if (dist[next.to] > thisEdge.weight + next.weight) {
                        dist[next.to] = thisEdge.weight + next.weight;
                        pq.add(new Edge(next.from, next.to, dist[next.to]));
                    }
                }

            }
        }
    }

    private static void dfs(int dot) {
        for (int ver : parent[dot]) {
            if (!cantVisit[ver].contains(dot)) {
                cantVisit[ver].add(dot);
                dfs(ver);
            }

        }
    }

    private static void dijkstra() {
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[frAim] = 0;
        pq = new PriorityQueue<>();
        for (Edge edge : graph[frAim]) {
            pq.add(edge);
            dist[edge.to] = edge.weight;
            parent[edge.to].add(edge.from);
        }

        while (!pq.isEmpty()) {
            Edge thisEdge = pq.poll();
            for (Edge next : graph[thisEdge.to]) {
                if (dist[next.to] > thisEdge.weight + next.weight) {
                    dist[next.to] = thisEdge.weight + next.weight;
                    pq.add(new Edge(next.from, next.to, dist[next.to]));

                    parent[next.to] = new ArrayList<>();
                    parent[next.to].add(next.from);
                } else if (dist[next.to] == thisEdge.weight + next.weight) {
                    parent[next.to].add(next.from);
                }
            }
        }
    }

}

class Edge implements Comparable<Edge> {
    int from;
    int to;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    int weight;
    @Override
    public int compareTo(Edge o) {
        return Integer.compare(weight, o.weight);
    }

}