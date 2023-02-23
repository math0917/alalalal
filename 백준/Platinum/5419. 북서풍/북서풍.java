import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    static int testCase;
    static int n;
    static PriorityQueue<Dot> pq;
    static List<Dot> list;
    static long sCount;
    static StringBuilder sb;
    static int[] seg;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCase = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int t = 0; t < testCase; t++) {
            pq = new PriorityQueue<>();
            n = Integer.parseInt(br.readLine());
            seg = new int[n * 4];
            list = new ArrayList<>();
            sCount = 0;
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                list.add(new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            list.sort(Comparator.comparingInt(a -> -a.y));
            int maxValue = Integer.MAX_VALUE;
            int count = -1;
            for (int i = 0; i < n; i++) {
//                System.out.print(list.get(i).y + " "+ minValue + " ");
                if (list.get(i).y < maxValue) {
                    count += 1;
                    maxValue = list.get(i).y;
                }
                list.get(i).y = count;
                pq.add(list.get(i));
            }

            while (!pq.isEmpty()) {
                Dot thisDot = pq.poll();

                find(1, 0, count, thisDot.y);
//                System.out.println(sCount);
                update(1, 0, count, thisDot.y);

//                System.out.println(Arrays.toString(seg));

            }
            sb.append(sCount).append('\n');
        }
        System.out.println(sb);

    }

    private static void update(int index, int start, int finish, int value) {
//        System.out.println(index + " " +start +" " + finish +" "+ value + "update");
        if (finish < value) {
            return;
        }
        if (start > value) {
            return;
        }
        if (value <= finish) {
            seg[index] += 1;
//            return;
        }
        if (start >= finish) {
            return;
        }
        int mid = (start + finish) / 2;
        update(index * 2, start, mid, value);

        update(index * 2 + 1, mid + 1,finish, value);
    }

    private static void find(int index, int start, int finish, int value) {
//        System.out.println(index+" "+start +" " + finish +" "+ value+"find ");

        if (start > value) {
            return;
        }
        if (value >= finish) {
//            System.out.println(seg[index]+"라고");
            sCount += seg[index];
            return;
        }
        if (start >= finish) {
            return;
        }
        int mid = (start + finish) / 2;
        find(index * 2, start, mid, value);

        find(index * 2 + 1, mid + 1,finish, value);
    }
}


class Dot implements Comparable<Dot> {
    int x;
    int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Dot o) {
        return Integer.compare(x, o.x) == 0 ? Integer.compare(y, o.y) : Integer.compare(x, o.x);
    }
}