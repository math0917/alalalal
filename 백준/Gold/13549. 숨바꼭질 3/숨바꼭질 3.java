

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dist;
    static Queue<int[]> pq;
//    동생 : k 수빈 : n
    static int n, k;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dist = new int[200000];
        for (int i = 0; i < 200000; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        pq = new ArrayDeque<>();
        dist[n] = 0;

        dijkstra();

        System.out.println(dist[k]);
    }

    private static void dijkstra() {
        pq.add(new int[] {n,0});
        if (n == 0) {
            dist[n+1] = 1;
            pq.add(new int[]{n + 1, 1});
        }
        while (!pq.isEmpty()) {
            int[] thisTurn = pq.poll();
//            System.out.println("thisTurn[0] = " + thisTurn[0]);
//            System.out.println("thisTurn[1] = " + thisTurn[1]);
            int dot = thisTurn[0];
            if (dot == k) {
                continue;
            } else if (dot > k) {
                dist[k] = Math.min(dist[dot]+dot - k, dist[k]);
                continue;
            }
            if (dot == 0) {
                continue;
            }
            if (dist[dot - 1] > thisTurn[1] + 1) {
                dist[dot - 1] = thisTurn[1] + 1;
                pq.add(new int[]{dot - 1, thisTurn[1] + 1});
            }
            if (dist[dot + 1] > thisTurn[1] + 1) {
                dist[dot + 1] = thisTurn[1] + 1;
                pq.add(new int[]{dot + 1, thisTurn[1] + 1});
            }
            if (dist[dot * 2] > thisTurn[1]) {
                dist[dot * 2] = thisTurn[1];
                pq.add(new int[]{dot * 2, thisTurn[1]});
            }
        }
    }
}
