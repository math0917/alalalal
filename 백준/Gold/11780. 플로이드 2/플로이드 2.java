

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static long[][] result;
    static Deque<Integer>[][] history;
    static PriorityQueue<Node> pq;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        result = new long[n][n];
        graph = new ArrayList[n];
        history = new ArrayDeque[n][n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    result[i][j] = Integer.MAX_VALUE;
                }

                history[i][j] = new ArrayDeque<>();
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
                    history[i][node.to].clear();
                    history[i][node.to].addLast(i);
                    history[i][node.to].addLast(node.to);
                    result[i][node.to] = node.weight;
                }


            }
            while (!pq.isEmpty()) {
                Node thisTurn = pq.poll();
                for (Node next : graph[thisTurn.to]) {
                    if (result[i][next.to] > thisTurn.weight + next.weight) {
                        history[i][next.to].clear();
                        for (int j = 0; j < history[i][next.fr].size(); j++) {
                            Integer integer = history[i][next.fr].pollFirst();
                            history[i][next.to].addLast(integer);
                            history[i][next.fr].addLast(integer);
                        }
                        history[i][next.to].add(next.to);
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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (result[i][j] == Integer.MAX_VALUE | result[i][j] == 0) {
                    System.out.println(0);
                } else {
                    System.out.print(history[i][j].size()+ " ");
                    while (!history[i][j].isEmpty()) {
                        System.out.print(history[i][j].pollFirst() + 1+" ");
                    }
                    System.out.println();
                }

            }
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