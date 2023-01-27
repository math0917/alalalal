

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[][] map;
    static int[] parent;
    static Queue<int[]> queue;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] length;
    public static void main(String[] args) throws Exception{
        input();
        bfs();
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int[] thisTurn = queue.poll();
            int thisTurnRow = thisTurn[0];
            int thisTurnCol = thisTurn[1];
            int thisTurnLength = thisTurn[2];
            for (int i = 0; i < 4; i++) {
                int row = thisTurnRow + dx[i];
                int col = thisTurnCol + dy[i];
                if (0 <= row && row < n && 0 <= col && col < n) {
                    if (map[row][col] == 0) {
                        length[row][col] = thisTurnLength + 1;
                        map[row][col] = find(map[thisTurnRow][thisTurnCol]);
                        queue.add(new int[]{row, col, thisTurnLength + 1});
                    } else {
                        if (find(map[row][col]) != find(map[thisTurnRow][thisTurnCol])) {
                            union(map[row][col], map[thisTurnRow][thisTurnCol]);
                            if (length[row][col] == length[thisTurnRow][thisTurnCol]) {
                                if (k == 1) {
                                    System.out.println(length[row][col]);
                                    return;
                                }
                            } else {
                                if (k == 1) {
                                    System.out.println(Math.max(length[row][col], length[thisTurnRow][thisTurnCol]));
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        parent = new int[k+1];
        queue = new ArrayDeque<>();
        length = new int[n][n];
        int groundCount = 0;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            parent[i + 1] = i + 1;
            int thisRow = Integer.parseInt(st.nextToken()) - 1;
            int thisCol = Integer.parseInt(st.nextToken()) - 1;
            map[thisRow][thisCol] = ++groundCount;
            length[thisRow][thisCol] = 0;
            queue.add(new int[]{thisRow, thisCol, 0});
        }
    }

    private static int find(int num) {
        if (parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }

    private static void union(int num1, int num2) {
        int par1 = find(num1);
        int par2 = find(num2);
        if (par1 != par2) {
            parent[Math.max(par1, par2)] = Math.min(par1, par2);
            k -= 1;
        }
    }

}
