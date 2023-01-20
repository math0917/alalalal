

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int a, d, q;
    static StringBuilder sb = new StringBuilder();
    static long[] arr;
    static Queue<Integer> queue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(br.readLine());
        for (int t = 0; t < q; t++) {
            st = new StringTokenizer(br.readLine());
            int procedure = Integer.parseInt(st.nextToken()),
                    from = Integer.parseInt(st.nextToken()),
                    to = Integer.parseInt(st.nextToken());
            if (procedure == 1) {
                long first = a + (long) (from - 1) * d;
                sb.append((long) (to - from + 1) * first + (long) (to - from) * (to - from + 1) * d / 2);
                sb.append("\n");
            } else {
                queue = new ArrayDeque<>();
                for (int i = from; i <= to; i++) {
                    queue.add(a + (i-1) * d);
                }
                while (!queue.isEmpty()) {
                    if (queue.size() == 1) {
                        sb.append(queue.poll());
                        sb.append("\n");
                    } else {
                        queue.add(gcd(queue.poll(), queue.poll()));
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }

    private static int gcd(int a, int d) {
        int min = Math.min(a, d);
        int max = Math.max(a, d);
        if (max % min == 0) {
            return min;
        } else {
            return gcd(min, max % min);
        }
    }

}
