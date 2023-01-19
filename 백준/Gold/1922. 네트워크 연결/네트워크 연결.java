

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] parent;
    static PriorityQueue<Node> pq;
    static int result = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parent = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
        }

        pq = new PriorityQueue<>();
        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken()),
                    to = Integer.parseInt(st.nextToken()),
                    weight = Integer.parseInt(st.nextToken());
            pq.add(new Node(fr, to, weight));
        }
        int count = 0;

        while (count < n-1) {
            Node thisTurn = pq.poll();
            if (find(thisTurn.fr) != find(thisTurn.to)) {
                union(thisTurn.fr, thisTurn.to);
                count+=1;
                result += thisTurn.weight;
            }
        }

        System.out.println(result);
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
            parent[Math.max(par1, par2)] = Math.min(par1, par2);
        }
    }
    static class Node implements Comparable<Node> {
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
}


