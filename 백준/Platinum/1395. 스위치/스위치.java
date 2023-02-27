import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] seg;
    static boolean[] lazy;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        seg = new int[4 * n];
        lazy = new boolean[4 * n];


        for (int p = 0; p < m; p++) {
            st = new StringTokenizer(br.readLine());
            int proc = Integer.parseInt(st.nextToken());
            switch (proc) {
                case 0:
                    int start = Integer.parseInt(st.nextToken()) - 1;
                    int finish = Integer.parseInt(st.nextToken()) - 1;
                    update(1, 0, n - 1, start, finish);
                    break;
                case 1:
                    start = Integer.parseInt(st.nextToken()) - 1;
                    finish = Integer.parseInt(st.nextToken()) - 1;
                    sb.append(find(1, 0, n - 1, start, finish)).append('\n');
            }
//            for (int i = 0; i < 4 * n; i++) {
//                System.out.print(seg[i]+ " ");
//            }
//            System.out.println();

        }
        System.out.println(sb);

    }

    private static int find(int index, int start, int finish, int from, int to) {
        updateLazy(index, start, finish);
        if (from > finish || to < start) {
            return 0;
        }
        if (from <= start && finish <= to) {
            return seg[index];
        }
        int mid = (start + finish) / 2;
        return (find(index * 2, start, mid, from, to) + find(index * 2 + 1, mid + 1, finish, from, to));
    }

    private static void update(int index, int start, int finish, int from, int to) {
        updateLazy(index, start, finish);
        if (from > finish || to < start) {
            return;
        }
        if (from <= start && finish <= to) {
            int sum = finish - start + 1;
            seg[index] = sum - seg[index];
            if (start != finish) {
                lazy[index * 2] = !lazy[index * 2];
                lazy[index * 2 + 1] = !lazy[index * 2 + 1];
            }
            return;
        }
        int mid = (start + finish) / 2;
        update(index * 2, start, mid, from, to);
        update(index * 2 + 1, mid + 1, finish, from, to);
        seg[index] = seg[index * 2] + seg[index * 2 + 1];
    }

    private static void updateLazy(int index, int start, int finish) {
        if (lazy[index]) {
            int sum = finish - start + 1;
            seg[index] = sum - seg[index];
            if (start != finish) {
                lazy[index * 2] = !lazy[index * 2];
                lazy[index * 2 + 1] = !lazy[index * 2 + 1];
            }
            lazy[index] = false;
        }
    }
}