
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int q, n;
    static PriorityQueue<Node> pq;
    static int[] parent;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        q = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        parent = new int[q + 1];
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Node(i+1,Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int index = 0;
        int finish = -1;
        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            if (finish < poll.start) {
                index = poll.index;
                finish = poll.finish;
            } else {
                union(index, poll.index);
                finish = Math.max(finish, poll.finish);
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            if (find(Integer.parseInt(st.nextToken())) == find(Integer.parseInt(st.nextToken()))) {
                sb.append(1).append('\n');
            } else {
                sb.append(0).append('\n');
            }
        }
        System.out.println(sb);
        
    }

    private static int find(int num) {
        return parent[num] == num ? num : find(parent[num]);
    }

    private static void union(int num1, int num2) {
        int par1 = find(num1);
        int par2 = find(num2);

        parent[par2] = par1;
    }
}

class Node implements Comparable<Node> {
    int index;
    int start;
    int finish;

    public Node(int index, int start, int finish) {
        this.index = index;
        this.start = start;
        this.finish = finish;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(start, o.start);
    }
}