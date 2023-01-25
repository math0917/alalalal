



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static boolean[][] visited;
    static int[][] cheese;
    static Queue<int[]> queue;
    static Queue<int[]> cheeseQueue;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n][m];
        cheese = new int[n][m];
        cheeseQueue = new ArrayDeque<>();
        queue = new ArrayDeque<>();
        result = new int[100];
        for (int i = 0; i< n ;i ++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++){
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        queue.add(new int[] {0,0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] thisTurn = queue.poll();

            for (int i = 0; i < 4; i++) {
                int row = thisTurn[0] + dx[i];
                int col = thisTurn[1] + dy[i];
                if (0 <= row && row < n && 0 <= col && col < m) {
                    if (!visited[row][col]) {
                        if (cheese[row][col] == 1) {
                            cheeseQueue.add(new int[]{row, col, 1});
                            visited[row][col] = true;
                        } else{
                            queue.add(new int[]{row, col});
                            visited[row][col] = true;
                        }
                    }
                }
            }
        }

        while (!cheeseQueue.isEmpty()) {
            int[] thisTurn = cheeseQueue.poll();
//            System.out.println(Arrays.toString(thisTurn));

            result[thisTurn[2]] += 1;

            for (int i = 0; i < 4; i++) {
                int row = thisTurn[0] + dx[i];
                int col = thisTurn[1] + dy[i];

                if (0 <= row && row < n && 0 <= col && col < m) {
                    if (!visited[row][col]) {
                        visited[row][col] = true;
                        if (cheese[row][col] == 1) {
                            cheeseQueue.add(new int[]{row, col, thisTurn[2] + 1});
                        } else {
                            queue.add(new int[] {row,col});
                            while (!queue.isEmpty()) {
                                int[] thisTurn1 = queue.poll();

                                for (int j = 0; j < 4; j++) {
                                    int row1 = thisTurn1[0] + dx[j];
                                    int col1 = thisTurn1[1] + dy[j];
                                    if (0 <= row && row < n && 0 <= col && col < m) {
                                        if (!visited[row1][col1]) {
                                            if (cheese[row1][col1] == 1) {
                                                cheeseQueue.add(new int[]{row1, col1, thisTurn[2] + 1});
                                                visited[row1][col1] = true;
                                            } else{
                                                queue.add(new int[]{row1, col1});
                                                visited[row1][col1] = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] != 0) {
                System.out.println(i);
                System.out.println(result[i]);
                break;
            }
        }
    }
}
