

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static long[][] result;
    static PriorityQueue<Node> pq;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        result = new long[n][n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        m = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            graph[fr].add(new Node(fr, to, weight));
        }
        for (int i = 0; i < n; i++) {
            for (Node node : graph[i]){
                if (result[i][node.to] > node.weight) {
                    pq.add(node);
                    result[i][node.to] = node.weight;
                }


            }
            while (!pq.isEmpty()) {
                Node thisTurn = pq.poll();
                for (Node next : graph[thisTurn.to]) {
                    if (result[i][next.to] > thisTurn.weight + next.weight) {
                        result[i][next.to] = thisTurn.weight + next.weight;
                        pq.add(new Node(next.fr, next.to, (int)result[i][next.to]));
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (result[i][j] == Integer.MAX_VALUE) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(result[i][j] + " ");
                }

            }
            System.out.println();
        }

    }


}
class Node implements Comparable<Node>{
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
        return Integer.compare(weight, o.weight);
    }
}