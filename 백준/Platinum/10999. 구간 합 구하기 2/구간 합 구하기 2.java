import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static long[] value;
    static long[] seg;
    static long[] lazy;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        value = new long[n];
        for (int i = 0; i < n; i++) {
            value[i] = Long.parseLong(br.readLine());
        }
        seg = new long[4 * n];
        lazy = new long[4 * n];
        init(1, 0, n - 1);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int proc = Integer.parseInt(st.nextToken());
            switch (proc) {
                case 1:
//                    System.out.println("hi");
                    update(1, 0, n - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Long.parseLong(st.nextToken()));
//                    for (int j = 0; j < 4 * n; j++) {
//                        System.out.print(seg[i]+ " ");
//                    }
//                    System.out.println();
                    break;
                case 2:
//                    System.out.println("hi");

//                    System.out.println(Integer.parseInt(st.nextToken()));
//                    System.out.println(Integer.parseInt(st.nextToken()));
                    sb.append(sum(1, 0, n - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1)).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static long sum(int index, int start, int finish, int from, int to ) {
        updateLazy(index, start, finish);
        if (from > finish || start > to) {
            return 0;
        }
        if (from <= start && finish <= to) {
            return seg[index];
        }
        int mid = (start + finish) / 2;
        return sum(index * 2, start, mid, from, to) + sum(index * 2 + 1, mid + 1,finish, from, to);
    }

    private static void update(int index, int start, int finish, int from, int to, long diff) {
//        System.out.println(start + " " + finish);
        updateLazy(index, start, finish);
        if (finish < from || start > to) {
            return;
        }
        if (from <= start && finish <= to) {
            seg[index] += (finish - start + 1) * diff;
            if (start != finish) {
                lazy[index * 2] += diff;
                lazy[index * 2 + 1] += diff;

            }
            return;
        }
        int mid = (start + finish) / 2;
        update(index * 2, start, mid, from, to, diff);
        update(index * 2 + 1, mid + 1, finish, from, to, diff);
        seg[index] = seg[index * 2] + seg[index * 2 + 1];
    }

    private static void updateLazy(int index, int start, int finish) {
        if (lazy[index] != 0) {
            seg[index] += ( finish - start + 1) *  lazy[index];
            if (start != finish) {
                lazy[index * 2] += lazy[index];
                lazy[index * 2 + 1] += lazy[index];
            }
            lazy[index] = 0;
        }
    }

    private static void init(int index, int start, int finish) {
        if (start == finish) {
            seg[index] = value[start];
            return;
        }
        int mid = (start + finish) / 2;
        init(index * 2, start, mid);
        init(index * 2 + 1, mid + 1, finish);
        seg[index] = seg[index * 2] + seg[index * 2 + 1];
    }
}