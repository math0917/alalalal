import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static Stack<int[]> stack;
    static ArrayList<Line> lines;
    static Stack<int[]> candidate;
    static int big = 0;
    static int small = 0;
    static int[][] dot;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        stack = new Stack<>();
        lines = new ArrayList<>();
        candidate = new Stack<>();
        dot = new int[n][2];
        int idx = -1;
        int minValueX = Integer.MAX_VALUE;
        int minValueY = -1;
        for (int t = 0; t < n; t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            dot[t][0] = x;
            dot[t][1] = y;
            if (x < minValueX) {
                minValueX = x;
                minValueY = y;
                idx = t;
            } else if (x == minValueX) {
                if (minValueY > y) {
                    minValueY = y;
                    idx = t;
                }

            }



        }

        for (int i = 0; i < n; i++) {
            int x = dot[(i + idx) % n][0];
            int y = dot[(i + idx) % n][1];
//            System.out.println(x + " " +y);
            if (stack.isEmpty()) {
                stack.add(new int[]{x, y});
            } else {
                if (y * stack.peek()[1] < 0) {
                    if (candidate.size() == 1) {
                        int[] pop = candidate.pop();
                        lines.add(new Line(Math.min(pop[0], x), Math.max(pop[0],x)));
                        stack.clear();
                    } else {
                        candidate.add(new int[]{x, y});
                    }

                } else {
                    stack.add(new int[]{x, y});
                }
            }
        }
        if (lines.size() == 0) {
            System.out.println(0 + " " + 0);
            System.exit(0);

        }
        Collections.sort(lines);

        stack.clear();
//        for (int i = 0; i < lines.size(); i++) {
//            System.out.println(lines.get(i).from + " " + lines.get(i).to);
//        }
        for (int i = 0; i < lines.size(); i++) {
//            System.out.println(i);
            Line line = lines.get(i);
            int from = line.from;
            int to = line.to;
            if (stack.isEmpty()) {
                stack.add(new int[]{from, to});
                big += 1;
            } else {
                int[] peek = stack.peek();
                if (peek[1] < from) {
                    stack.pop();
                    small += 1;
                    while (!stack.isEmpty()) {
                        if (from > stack.peek()[1]) {
                            stack.pop();
                        } else {
                            break;
                        }
                    }
                }
                if (stack.isEmpty()) {
                    i--;
                    continue;
                }
                stack.add(new int[]{from, to});

            }
        }
        System.out.println(big + " " + (small+1));

    }
}

class Line implements Comparable<Line> {
    int from;
    int to;

    public Line(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public int compareTo(Line o) {
        return Integer.compare(from, o.from);
    }

}