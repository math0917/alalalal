

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m ;
    static List<Node>[] graph;
    static int[] dist;
    static boolean[] visited;
    static int aimFr, aimTo;
    static PriorityQueue<Node> pq;
    static int[] fromWhere;
    static Stack<Integer> result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        dist = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        fromWhere = new int[n + 1];
        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            graph[fr].add(new Node(fr, to, length));
        }

        st = new StringTokenizer(br.readLine());
        aimFr = Integer.parseInt(st.nextToken());
        aimTo = Integer.parseInt(st.nextToken());

        dist[aimFr] = 0;
        fromWhere[aimFr] = aimFr;
        dijkstra();
        System.out.println(dist[aimTo]);
        result = new Stack<>();

        while (true) {
            result.add(aimTo);
            if (fromWhere[aimTo] != aimTo) {
                aimTo = fromWhere[aimTo];
            } else {
                break;
            }
        }
        System.out.println(result.size());
        while (!result.isEmpty()) {
            System.out.print(result.pop()+" ");
        }
    }

    private static void dijkstra() {
        pq = new PriorityQueue<>();
        for (Node node : graph[aimFr]) {
            pq.add(node);
        }

        while (!pq.isEmpty()) {
            Node thisTurn = pq.poll();

            if (thisTurn.length < dist[thisTurn.finish]) {
                dist[thisTurn.finish] = thisTurn.length;
                fromWhere[thisTurn.finish] = thisTurn.start;
                for (Node next : graph[thisTurn.finish]) {
                    pq.add(new Node(next.start, next.finish, dist[thisTurn.finish] + next.length));
                }
            }


        }
    }
}

class Node implements Comparable<Node>{
    int start;
    int finish;
    int length;

    public Node(int start, int finish, int length) {
        this.start = start;
        this.finish = finish;
        this.length = length;
    }


    @Override
    public int compareTo(Node o) {
        return Integer.compare(length, o.length);
    }
}
