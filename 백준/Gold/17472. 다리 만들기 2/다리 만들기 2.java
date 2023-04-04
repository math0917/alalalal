import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class Main {
    static int n, m;
    static int[][] map;
    static int count = 1;
    static int[] parent;
    static int landCount = 1;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] graph;
    static int result = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = landCount;
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] thisTurn = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int row = thisTurn[0] + dx[k];
                            int col = thisTurn[1] + dy[k];
                            if (0 <= row && row < n && 0 <= col && col < m) {
                                if (map[row][col] == 0) {
                                    map[row][col] = landCount;
                                    queue.add(new int[]{row, col});
                                }
                            }
                        }
                    }
                    landCount ++;
                }
            }

        }

        graph = new int[landCount][landCount];
        for (int i = 0; i < landCount; i++) {
            Arrays.fill(graph[i], 11);
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    for (int k = 0; k < 4; k++) {
                        int idx = 1;
                        int thisRow = i;
                        int thisCol = j;
                        while (true) {
                            int row = thisRow + idx * dx[k];
                            int col = thisCol + idx * dy[k];
                            if (0 <= row && row < n && 0 <= col && col < m) {
                                if (map[row][col] == map[i][j]) {
                                    thisRow = row;
                                    thisCol = col;
                                    idx = 1;
                                    continue;
                                } else if (map[row][col] != map[i][j] && map[row][col] > 0 ) {
//                                    System.out.println(map[row][col] + " "+ map[i][j] + " " +idx + " "+i + " " + j + " "+ row + " " + col);
                                    if (idx >= 3) {
                                        graph[map[row][col]][map[i][j]] = Math.min(graph[map[row][col]][map[i][j]], idx - 1);
                                        graph[map[i][j]][map[row][col]] = Math.min(graph[map[i][j]][map[row][col]], idx - 1);
                                    }
                                    break;
                                }
                                idx ++;
                            } else {
                               break;
                            }
                        }
                    }
                }
            }

        }

//        for (int i = 1; i < landCount; i++) {
//            for (int j = 1; j < landCount; j++) {
//                System.out.print(graph[i][j] + " ");
//            }
//            System.out.println();
//        }

        boolean[] visited = new boolean[landCount];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
        pq.add(new int[]{1, 0});
        while (!pq.isEmpty()) {
            int[] thisTurn = pq.poll();
            if (!visited[thisTurn[0]]) {
//                System.out.println(thisTurn[0] +" "+ thisTurn[1]);
                result += thisTurn[1];
                visited[thisTurn[0]] = true;
                for (int j = 1; j < landCount; j++) {
                    if (!visited[j] && graph[thisTurn[0]][j] != 11) {
                        pq.add(new int[]{j, graph[thisTurn[0]][j]});
                    }
                }
            }
        }
        for (int i = 1; i < landCount; i++) {
            if (!visited[i]) {
                System.out.println(-1);
                System.exit(0);
            }
        }
        System.out.println(result);
    }
}