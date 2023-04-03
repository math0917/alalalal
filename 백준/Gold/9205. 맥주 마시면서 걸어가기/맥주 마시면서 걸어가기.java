import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Main {
    static int testCase;
    static int n;
    static int festivalRow, festivalCol;
    static Queue<int[]> visited;
    static Queue<int[]> notVisited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            visited = new ArrayDeque<>();
            notVisited = new ArrayDeque<>();
            visited.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                notVisited.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
            }
            st = new StringTokenizer(br.readLine());
            festivalRow = Integer.parseInt(st.nextToken());
            festivalCol = Integer.parseInt(st.nextToken());
            notVisited.add(new int[]{festivalRow, festivalCol});
            boolean flag = false;
            while (!visited.isEmpty()) {
                int[] thisTurn = visited.poll();
                int x = thisTurn[0];
                int y = thisTurn[1];

                if (x == festivalRow && y == festivalCol) {
                    System.out.println("happy");
                    flag = true;
                    break;
                }
                int size = notVisited.size();
                for (int i = 0; i < size; i++) {
                    int[] check1 = notVisited.poll();
                    if (Math.abs(check1[0] - x) + Math.abs(check1[1] - y) <= 1000) {
                        visited.add(check1);
                    } else {
                        notVisited.add(check1);
                    }

                }
            }
            if (!flag) {
                System.out.println("sad");
            }
        }

    }
}