
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static Point[] point;
    static int[] parent;
    static int count;
    static double result;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        count = 0;
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;

        }
        point = new Point[n + 1];
        for (int t = 1; t < n+1; t++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            point[t] = new Point(x, y);
        }
        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            union(fr, to);

        }
        result = 0;
        pq = new PriorityQueue<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                pq.add(new Node(i, j, point[i].dist(point[j])));
            }
        }

        while (count < n - 1) {
            Node thisTurn = pq.poll();
            if (find(thisTurn.fr) != find(thisTurn.to)) {
                union(thisTurn.fr, thisTurn.to);
                result += thisTurn.weight;

            }
        }



        System.out.printf("%.2f", result);

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
            count += 1;
            parent[Math.max(par1, par2)] = Math.min(par1, par2);
        }
    }
    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double dist(Point point) {
            return Math.sqrt((x - point.x) * (x - point.x) + (y - point.y) * (y - point.y));
        }
    }

    static class Node implements Comparable<Node>{
        int fr;
        int to;
        double weight;

        public Node(int fr, int to, double weight) {
            this.fr = fr;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(weight, o.weight);
        }
    }

}
