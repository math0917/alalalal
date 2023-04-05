import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Main {
    static int n, m;
    static int fr, to;
    static ArrayList<Integer>[] ho;
    static ArrayList<Integer>[] station;

    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ho = new ArrayList[m]; //호선에 따른 역
        station = new ArrayList[n]; //역에 지나가는 호선
        for (int i = 0; i < n; i++) {
            station[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            ho[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            while (true) {
                int v = Integer.parseInt(st.nextToken()) - 1;
                if (v == -2) {
                    break;
                }
                ho[i].add(v);
                station[v].add(i);
            }
        }

        st = new StringTokenizer(br.readLine());
        fr = Integer.parseInt(st.nextToken()) - 1;
        to = Integer.parseInt(st.nextToken()) - 1;
        init();
        System.out.println(-1);
    }

    private static void init() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{fr, -1});
        //ho : 호선의 역 station : 역에 지나는 호선
        boolean[] visited = new boolean[n];
        visited[fr] = true;
        boolean[] hoVisited = new boolean[m];
        while (!queue.isEmpty()) {
            int[] thisTurn = queue.poll();
            for (int hosun : station[thisTurn[0]]) {
                if (!hoVisited[hosun]) {
                    hoVisited[hosun] = true;
                    for (int st : ho[hosun]) {
                        if (st == to) {
                            System.out.println(thisTurn[1] + 1);
                            System.exit(0);
                        }
                        if (!visited[st]) {
                            visited[st] = true;
                            queue.add(new int[]{st, thisTurn[1] + 1});

                        }
                    }
                }
            }
        }
    }
}