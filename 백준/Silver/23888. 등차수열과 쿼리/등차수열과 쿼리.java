
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static long a, d, q;
    static StringBuilder sb = new StringBuilder();
    static long[] arr;
    static Deque<Integer> queue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        d = Long.parseLong(st.nextToken());
        q = Long.parseLong(br.readLine());
        for (int t = 0; t < q; t++) {
            st = new StringTokenizer(br.readLine());
            int procedure = Integer.parseInt(st.nextToken()),
                    from = Integer.parseInt(st.nextToken()),
                    to = Integer.parseInt(st.nextToken());
            if (procedure == 1) {
//                9999991000000

                sb.append((long)(to - from + 1) * a + ((long)(to + from - 2) * (to - from +1) / 2 * d));
//                sb.append((new BigInteger(String.valueOf((to - from + 1)* first)).add
//                        (new BigInteger(String.valueOf((to - from) * (to - from + 1))).multiply(new BigInteger(String.valueOf(d))).divide(new BigInteger("2")))));
                sb.append("\n");
            } else {
                if (from != to) {
                    sb.append(gcd(a, d));
                    sb.append("\n");
                } else {
                    sb.append(a + (from- 1) *d);
                    sb.append("\n");
                }

            }
        }
        System.out.println(sb.toString());
    }

    private static BigInteger multiply(long a1, long a2) {
        return (new BigInteger(String.valueOf(a1)).multiply(new BigInteger(String.valueOf(a2))));
    }

    private static long gcd(long a, long d) {
        long min = Math.min(a, d);
        long max = Math.max(a, d);
        if (min == 0) {
            return max;
        }
        if (max % min == 0) {
            return min;
        } else {
            return gcd(min, max % min);
        }
    }

}
