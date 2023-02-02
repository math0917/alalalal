

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Node>[] graph;
    static int[][] fraction;
    static Set<Integer> set;
    public static void main(String[] args) throws Exception{
        input();
        fractionGcd();

        print(fractionDownLcm());
    }

    private static void print(int lcm) {
        for (int i = 0; i < n; i++) {
            System.out.print(lcm/ fraction[i][1] *fraction[i][0] +" ");
        }
    }

    private static int fractionDownLcm() {
        if (n == 2) {
            return fraction[1][1];
        }
        int lcm = fraction[1][1];
        for (int i = 2; i < n; i++) {

            int gcd = gcdCal(lcm, fraction[i][1]);
            lcm = fraction[i][1]/ gcd * lcm;
        }

        return lcm;
    }

    private static void fractionGcd() {
        for (int i = 0; i < n; i++) {
            int gcd = gcdCal(Math.min(fraction[i][0], fraction[i][1]), Math.max(fraction[i][0], fraction[i][1]));

            fraction[i][0] /= gcd;
            fraction[i][1] /= gcd;
        }
    }

    private static int gcdCal(int i, int i1) {
//        System.out.println(i + " " + i1);
        if (i1 == 0) {
            return i;
        }
        return gcdCal(i1, i % i1);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        fraction = new int[n][2];
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int down = Integer.parseInt(st.nextToken());
            int up = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, up, down));
            graph[to].add(new Node(from, down, up));
        }

        fraction[0][0] = 1;
        fraction[0][1] = 1;
        set = new HashSet<>();
        set.add(0);
        dfs(0);
    }

    private static void dfs(int thisNum) {
        for (Node next : graph[thisNum]) {
            if (!set.contains(next.to)) {
                set.add(next.to);
                fraction[next.to][0] = fraction[thisNum][0] * next.up;
                fraction[next.to][1] = fraction[thisNum][1] * next.down;
                dfs(next.to);
            }
        }
    }

}

class Node{
    int to;
    int up;
    int down;

    public Node(int to, int up, int down) {
        this.to = to;
        this.up = up;
        this.down = down;
    }
}
