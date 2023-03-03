import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] shark;
    static int babyRow;
    static int babyCol;
    static int sec = 0;
    static Queue<int[]> queue = new ArrayDeque<>();
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int babySize = 2;
    static int eatCount = 0;
    static boolean[][] visited;
    static int time = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        shark = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                shark[i][j] = Integer.parseInt(st.nextToken());
                if (shark[i][j] == 9) {
                    shark[i][j] = 0;
                    babyRow = i;
                    babyCol = j;
                }
            }
        }
        while (bfs(babyRow, babyCol)) {

        }
        System.out.println(time);

    }

    private static boolean bfs(int br, int bc) {
        queue.clear();
        visited = new boolean[n][n];
        queue.add(new int[]{br, bc, 0});
        visited[br][bc] = true;
        boolean flag = false;
        int standardRow = -1;
        int standardCol = -1;
        int size = queue.size();
        int length = 0;
        while (!flag && !queue.isEmpty()) {
            size = queue.size();
            for (int p = 0; p < size; p++) {
                int[] thisTurn = queue.poll();
                length = thisTurn[2];
//            System.out.println("hi");
                for (int k = 0; k < 4; k++) {
                    int row = thisTurn[0] + dx[k];
                    int col = thisTurn[1] + dy[k];
                    if (0 <= row && row < n && 0 <= col && col < n) {
                        if (!visited[row][col]) {
                            visited[row][col] = true;
                            if (shark[row][col] == 0 || shark[row][col] == babySize) {
                                if (!flag) {
                                    queue.add(new int[]{row, col, thisTurn[2] + 1});
                                }
                            } else if (shark[row][col] < babySize) {
                                if (flag) {
                                    if (row < standardRow) {
                                        standardRow = row;
                                        standardCol = col;
                                        continue;
                                    } else if (row == standardRow) {
                                        if (col < standardCol) {
                                            standardCol = col;
                                            standardRow = row;
                                            continue;
                                        }
                                    }
                                    continue;
                                }
                                standardRow = row;
                                standardCol = col;
                                flag = true;
                            }
                        }
                    }
                }
            }
        }
        if (flag) {
            shark[standardRow][standardCol] = 0;
            babyRow = standardRow;
            babyCol = standardCol;
            eatCount += 1;
            if (eatCount == babySize) {
                babySize += 1;
                eatCount = 0;
            }
            time += length + 1;
        }
        return flag;
    }
}