
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static ArrayList<Node> [] graph;
    static int[] dist;
    static boolean[] visited;
    static int aim1, aim2;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new int[n+1];
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i < n+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken()) ,
                    to = Integer.parseInt(st.nextToken()) ,
                    length = Integer.parseInt(st.nextToken());
            graph[fr].add(new Node(to,length));
//            graph[to].add(new Node(fr,length));
        }

        st = new StringTokenizer(br.readLine());
        aim1 = Integer.parseInt(st.nextToken());
        aim2 = Integer.parseInt(st.nextToken());

        for (int i = 1; i < n+ 1;i ++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[aim1] = 0;
//        visited[aim1] = true;
        pq.add(new Node(aim1, 0));
        dijkstra();

//        System.out.println("dist.toString() = " + dist.toString());
        System.out.println(dist[aim2]);


    }

    private static void dijkstra() {
        while (!pq.isEmpty()) {
            Node node = pq.poll();
//            System.out.println("node.adjacent + node.weight = " + node.adjacent +" "+ node.weight);
//            for (int i = 1; i < n + 1; i++) {
//                System.out.println(dist[i]);
//            }
            int destination = node.adjacent;
            if (visited[destination]) {
                continue;
            } else {
                visited[destination] = true;
            }
            for (Node next : graph[destination]) {
                if (dist[next.adjacent] >= dist[destination] + next.weight) {

                    dist[next.adjacent] = dist[destination] + next.weight;
                    pq.add(new Node(next.adjacent, dist[next.adjacent]));
                }
            }
        }
    }

}

class Node implements Comparable<Node> {
    int adjacent;
    int weight;

    public Node(int adjacent, int weight) {
        this.adjacent = adjacent;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(weight, o.weight);
    }
}