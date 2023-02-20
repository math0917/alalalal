
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static PriorityQueue<Integer> pq;
    static int[] minus;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(st.nextToken()));
        }
        minus = new int[n - 1];
        int sum = 0;
        int cut = 0;

        int value = pq.poll();
        int max = value;
        int idx = 0;
        int min = 0;
        while (!pq.isEmpty()) {
            Integer thisValue = pq.poll();
            minus[idx++] = value - thisValue;
            value = thisValue;
            if (pq.isEmpty()) {
                min = thisValue;
            }
        }
//        System.out.println(Arrays.toString(minus));
        if (n == 1) {
            System.out.println(value - m);
            System.exit(0);
        } else {
            int i = 1;
            try {
                while (true) {
//                System.out.println(sum);
                    if (i * minus[i - 1] < m) {

                        cut += minus[i - 1];
                        m -= i * minus[i - 1];
                        i++;
                    } else {
                        if (m % i == 0) {
//                        System.out.println(cut);
                            cut += m / i;
                            System.out.println(max - cut);
                            System.exit(0);

                        } else {
//                        System.out.println(cut);
                            cut += (m / i) + 1;
                            System.out.println(max - cut);
                            System.exit(0);

                        }
                    }
                }
            } catch (Exception e) {
                if (m % i == 0) {
//                        System.out.println(cut);
                    cut += m / i;
                    System.out.println(max - cut);
                    System.exit(0);

                } else {
//                        System.out.println(cut);
                    cut += (m / i) + 1;
                    System.out.println(max - cut);
                    System.exit(0);

                }
            }


        }
    }
}
