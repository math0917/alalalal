import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static PriorityQueue<Integer>[] dist;
    static int n, m, k;
    static ArrayList<Node>[] graph;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dist = new PriorityQueue[n];
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = new PriorityQueue<>(Collections.reverseOrder());

        }
//        dist[0].add(0);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            graph[fr].add(new Node(fr, to, weight));
//            graph[to].add(new Node(to, fr, weight));
        }

        dijkstra();

//        for (int i = 0; i < n; i++) {
//            for (int a : dist[i]) {
//                System.out.print(a + " ");
//            }
//            System.out.println();
//        }
        for (int i = 0; i < n; i++) {
            if (dist[i].size() < k) {
                System.out.println(-1);

            } else {
                
                System.out.println(dist[i].poll());
            }
        }

    }

    private static void dijkstra() {
        pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 0));
        dist[0].add(0);
        while (!pq.isEmpty()) {
            Node thisTurn = pq.poll();

            for (Node next : graph[thisTurn.to]) {
                {
                    if (dist[next.to].size() < k) {
                        pq.add(new Node(next.from, next.to, next.weight + thisTurn.weight));
                        dist[next.to].add(next.weight + thisTurn.weight);
                    } else {
                        if (dist[next.to].peek() > next.weight + thisTurn.weight) {
                            dist[next.to].poll();
                            pq.add(new Node(next.from, next.to, next.weight + thisTurn.weight));
                            dist[next.to].add(next.weight + thisTurn.weight);
                        }




                    }

//                        System.out.println(next.from + " " + next.to + " " + dist[next.to][i]);

                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int from;
    int to;
    int weight;

    public Node(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(weight, o.weight);
    }
}