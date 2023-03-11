import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] work;
    static PriorityQueue<int[]> pq;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>(Comparator.comparingInt(a -> -a[1]));
        work = new int[1000];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())});
        }
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            for (int j = poll[0]; j >= 0; j--) {
                if (work[j] == 0) {
                    work[j] = poll[1];
                    break;
                }
            }
        }
//        System.out.println(Arrays.toString(work));
        System.out.println(Arrays.stream(work).sum());
    }
}