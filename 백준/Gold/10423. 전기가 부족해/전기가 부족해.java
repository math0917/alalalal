

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int[] parent;
    static PriorityQueue<Node> pq;
    static int result;
    static int count;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }
        pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        count = 0;
        for (int i = 0; i < k; i++) {
            int oil = Integer.parseInt(st.nextToken());
            union(0, oil);
        }

        result = 0;

        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken()),
                    to = Integer.parseInt(st.nextToken()),
                    weight = Integer.parseInt(st.nextToken());
            pq.add(new Node(fr, to, weight));
        }

        while (count < n) {
            Node thisTurn = pq.poll();
            if (find(thisTurn.fr) != find(thisTurn.to)) {
//                System.out.println(thisTurn.fr + "" + thisTurn.to);
                union(thisTurn.fr, thisTurn.to);
                result += thisTurn.weight;
            }
        }
        System.out.println(result);
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
            return Integer.compare(weight, o.weight);
        }
    }

    static int find(int num1) {
        if (parent[num1] == num1) {
            return num1;
        }
        return parent[num1] = find(parent[num1]);
    }

    static void union(int num1, int num2) {
        int par1 = find(num1);
        int par2 = find(num2);

        if (par1 != par2) {
            parent[Math.max(par1, par2)] = parent[Math.min(par1, par2)];
            count += 1;
        }
    }
}
