
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int n, q;
    static long[] values;
    static long[] seg;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        values = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            values[i] = Long.parseLong(st.nextToken());
        }
        seg = new long[n * 4];

        init(1, 0, n - 1);
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int temp = Math.min(fr, to);
            int temp1 = Math.max(fr, to);
            fr = temp;
            to = temp1;
            sb.append(partSum(1, 0, n - 1, fr, to)).append('\n');
//            for (int t = 0; t < seg.length; t++) {
//                System.out.println(seg[t]);
//            }
//            System.out.println();
            int changeIdx = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            long changeValue = c - values[changeIdx];
            values[changeIdx] = c;

            update(1, 0, n - 1, changeIdx, changeValue);
//            for (int t = 0; t < seg.length; t++) {
//                System.out.println(seg[t]);
//            }
//            System.out.println();
        }
        bw.write(sb.toString());
        bw.flush();

    }

    private static void update(int index, int start, int finish, int changeIdx, long changeValue) {
//        System.out.println(index);
        if (start <= changeIdx && changeIdx <= finish) {
            seg[index] += changeValue;
        }
        if (changeIdx < start || finish <changeIdx) {
            return;
        }
        int mid = (start + finish) / 2;
        if (start != finish) {
            update(index * 2, start, mid, changeIdx, changeValue);
            update(index * 2 + 1, mid + 1, finish, changeIdx, changeValue);
        }

    }

    private static long partSum(int index, int start, int finish, int fr, int to) {
        if (to < start || finish < fr) {
            return 0;
        }
        if (fr <= start && finish <= to) {
            return seg[index];
        }

        int mid = (start + finish) / 2;
        return partSum(index * 2, start, mid, fr, to) + partSum(index * 2 + 1, mid + 1, finish, fr, to);
    }

    private static void init(int index, int start, int finish) {
        if (start == finish) {
            seg[index] = values[start];
        } else {
            int mid = (start + finish)/2;
            init(index * 2, start, mid);
            init(index * 2 + 1, mid + 1, finish);
            seg[index] = seg[index * 2] + seg[index * 2 + 1];
        }
    }
}
