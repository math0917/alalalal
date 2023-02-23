import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, d;
    static int[][] castle;
    static int result;
    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        result = 0;
        castle = new int[n + 1][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init(0,0);
        System.out.println(result);

    }

    private static void init(int bit, int count) {
        if (count == 3) {
            int idx = 0;
            int[][] copy = new int[n + 1][m];
            for (int j = 0; j < n; j++) {
                copy[j] = castle[j].clone();
            }
            for (int i = 0; i < m; i++) {
                if ((bit & (1 << i)) != 0) {
                    copy[n][i] = 2;
                }
            }
            int val = 0;
            while (true) {

                boolean flag = false;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (copy[i][j] == 1) {
                            flag = true;
                        }
                    }
                }
                if (!flag) {
                    break;
                }

                Set<Integer> set = new HashSet<>();
                for (int j = 0; j < m; j++) {
                    if (copy[n][j] == 2) {
//                        System.out.print(j + " ");
                        Queue<int[]> queue = new ArrayDeque<>();
                        boolean[][] visited = new boolean[n][m];
                        queue.add(new int[]{n, j, 0});
                        while (!queue.isEmpty()) {
                            int[] thisTurn = queue.poll();
                            int thisRow = thisTurn[0];
                            int thisCol = thisTurn[1];
                            int thisLength = thisTurn[2];
                            if (thisLength == d) {
                                break;
                            }
                            for (int k = 0; k < 3; k++) {
                                int row = thisRow + dx[k];
                                int col = thisCol + dy[k];
//                                System.out.println(row + " " + col);
                                if (0 <= row && row < n && 0 <= col && col < m) {
                                    if (!visited[row][col]) {
                                        visited[row][col] = true;
                                        if (copy[row][col] == 1) {
//                                            System.out.println(row * m + col);
                                            set.add(m * row + col);
                                            queue.clear();
                                            break;
                                        }
                                        queue.add(new int[]{row, col, thisLength + 1});
                                    }
                                }
                            }
                        }

                    }
                }
                val += set.size();

                for (int a : set) {

                    int row = a / m;
                    int col = a % m;
                    copy[row][col] = 0;
                }

                for (int r = n-1; r >= 0; r--) {
                    for (int c = 0; c < m; c++) {
                        if (copy[r][c] == 1) {
                            copy[r][c] = 0;

                            if (r + 1 < n) {

                                copy[r+1][c] = 1;
                            }

                        }

                    }

                }

            }
            result = Math.max(result, val);
            return;


//            Set<Integer> delete = new HashSet<>();
//            boolean[][] visited = new boolean[n][m];
//            Queue<int[]> queue = new ArrayDeque<>();
//            queue.add(new int[]{n, i, 0});
//            while (!queue.isEmpty()) {
//                int[] thisTurn = queue.poll();
//                int thisRow = thisTurn[0];
//                int thisCol = thisTurn[1];
//                for (int k = 0; k < 3; k++) {
//                    int row = thisRow + dx[k];
//                    int col = thisCol + dy[k];
//                }
//            }




        }
        for (int i = 0; i < m; i++) {
            if ((bit & (1 << i)) == 0) {
                init(bit | (1 << i), count + 1);
            }
        }
    }
}