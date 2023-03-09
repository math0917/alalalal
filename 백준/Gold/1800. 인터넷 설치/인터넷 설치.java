import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, p, k;
    static int[] dist;
    static PriorityQueue<Cable> pq;
    static ArrayList<Cable>[] cable;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cable = new ArrayList[n];
        dist = new int[n];
        for (int i = 0; i < n; i++) {
            cable[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }
        dist[0] = 0;
        
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            cable[fr].add(new Cable(fr, to, weight, 0));
            cable[to].add(new Cable(to, fr, weight, 0));
        }

        binarySearch();
        System.out.println(result);
        
        
    }

    private static void binarySearch() {
        int l = 0, r = 1_000_001;
        while (l < r) {
            int mid = (l + r) / 2;
//            삭제되는게 더 적게도 연결된다면... 크기를 더 줄여봐야함
//            System.out.println(l + " " + r + " " + dijkstra(mid) + " " + k);
            if (dijkstra(mid) <= k) {
                r = mid;
//                System.out.println(mid);
                result = Math.min(result, mid);
            } else {
//                삭제되는게 많게 연결된다면 얘는 크기를 더 키워봐야함
                l = mid + 1;
            }
        }


    }

    private static int dijkstra(int value) {
        pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        pq.add(new Cable(0, 0, 0, 0));
        while (!pq.isEmpty()) {
            Cable thisTurn = pq.poll();

            for (Cable next : cable[thisTurn.to]) {
                if (next.weight <= value) {
                    if (dist[next.to] > thisTurn.deleteCount) {
                        dist[next.to] = thisTurn.deleteCount;
                        pq.add(new Cable(next.from, next.to, 0, dist[next.to]));
                    }
                } else {
                    if (dist[next.to] > thisTurn.deleteCount + 1) {
                        dist[next.to] = thisTurn.deleteCount + 1;
                        pq.add(new Cable(next.from, next.to, 0, dist[next.to]));
                    }
                }
            }
        }
        if (dist[n - 1] == Integer.MAX_VALUE) {
            System.out.println(-1);
            System.exit(0);
        }
        return dist[n - 1];
    }
}

class Cable implements Comparable<Cable>{

    int from;
    int to;
    int weight;
    int deleteCount;

    public Cable(int from, int to, int weight, int deleteCount) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.deleteCount = deleteCount;
    }

    @Override
    public int compareTo(Cable o) {

        return Integer.compare(deleteCount, o.deleteCount);

    }

}