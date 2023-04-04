import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int n,m, k;
    static Map<Integer,PriorityQueue<Micro>> map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Micro> queue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t < testCase + 1; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            queue = new ArrayDeque<>();
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                Micro micro = new Micro(row, col, count, dir);
                queue.add(micro);
            }
            while (m-- > 0) {
//                System.out.println(m+"번");
                map = new HashMap<>();

                while (!queue.isEmpty()) {
                    Micro thisTurn = queue.poll();

                    int row = thisTurn.row + dx[thisTurn.dir];
                    int col = thisTurn.col + dy[thisTurn.dir];
                    if (finish(row, col)) {
                        thisTurn.count /= 2;
                        thisTurn.dir = 2*(thisTurn.dir / 2) + (thisTurn.dir + 1) % 2;
                    }

                    if (thisTurn.count > 0) {
                        thisTurn.row = row;
                        thisTurn.col = col;
                        if (!map.containsKey(row * n + col)) {
                            map.put(row * n + col, new PriorityQueue<>());
                        }
//                        System.out.println(thisTurn);
                        map.get(row * n + col).add(thisTurn);
                    }
                }
                for (int rowCol : map.keySet()) {

                    PriorityQueue<Micro> micros = map.get(rowCol);
                    if (micros.size() > 1) {
                        Micro poll = micros.poll();
                        while (!micros.isEmpty()) {
                            poll.count += micros.poll().count;
                        }
                        queue.add(poll);
                    } else {
                        Micro poll = micros.poll();
                        queue.add(poll);

                    }
                }

            }
            int result = 0;

            while (!queue.isEmpty()) {
                int v = queue.poll().count;
//                System.out.println("D"+ " "+v);
                result += v;

            }
            System.out.println("#"+t+" "+result);
        }
    }

    private static boolean finish(int row, int col) {
        return row == n - 1 || row == 0 || col == 0 || col == n - 1;
    }
}

class Micro implements Comparable<Micro>{
    int row;
    int col;
    int count;
    int dir;

    public Micro(int row, int col, int count, int dir) {
        this.row = row;
        this.col = col;
        this.count = count;
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Micro{" +
                "row=" + row +
                ", col=" + col +
                ", count=" + count +
                ", dir=" + dir +
                '}';
    }

    @Override
    public int compareTo(Micro o) {
        return Integer.compare(o.count, count);
    }
}