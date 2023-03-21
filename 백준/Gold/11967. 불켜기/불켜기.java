
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static boolean[][] room;
    static ArrayList<int[]>[][] graph;
    static int count = 0;
    static Queue<int[]> queue = new ArrayDeque<>();
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int toRow = Integer.parseInt(st.nextToken()) - 1;
            int toCol = Integer.parseInt(st.nextToken()) - 1;
            graph[row][col].add(new int[]{toRow, toCol});
        }
        room = new boolean[n][n];
        room[0][0] = true;
        visited = new boolean[n][n];
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        count = 1;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int row = poll[0];
            int col = poll[1];
//            System.out.println(row + " "+ col);
            for (int[] next : graph[row][col]) {
                if (!room[next[0]][next[1]]) {
                    room[next[0]][next[1]] = true;
                    count += 1;

                    for (int k = 0; k < 4; k++) {
                        int thisRow = next[0] + dx[k];
                        int thisCol = next[1] + dy[k];
                        if (0 <= thisRow && thisRow < n && 0 <= thisCol && thisCol < n) {
                            if (room[thisRow][thisCol] && visited[thisRow][thisCol]) {

                                visited[next[0]][next[1]] = true;
                                queue.add(new int[]{next[0],next[1]});
                                break;
                            }
                        }
                    }
                }
            }
            for (int k = 0; k < 4; k++) {
                int thisRow = row + dx[k];
                int thisCol = col + dy[k];
                if (0 <= thisRow && thisRow < n && 0 <= thisCol && thisCol < n) {
                    if (!visited[thisRow][thisCol] && room[thisRow][thisCol]) {
                        visited[thisRow][thisCol] = true;
                        queue.add(new int[]{thisRow, thisCol});
                    }
                }
            }
        }
        System.out.println(count);

    }
}
