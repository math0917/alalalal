import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int testCase;
    static int n;
    static int[][] elect;
    static List<int[]> core;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static int resultLength;
    static int resultElect;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());
            resultLength = Integer.MIN_VALUE;
            resultElect = Integer.MAX_VALUE;
            core = new ArrayList<>();
            elect = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    elect[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (elect[i][j] == 1) {
                        if (i == 0 || i == n - 1 || j == 0 || j == n - 1) {
                            visited[i][j] = true;
                            continue;
                        }
                        core.add(new int[]{i, j});
                    }
                }
            }

            backTracking(0,0, 0);

            System.out.println("#" + t+" " +resultElect);

        }
    }

    private static void backTracking(int index, int cnt, int length) {
//        System.out.println(index + " " + cnt + " " + length);
        if (index == core.size()) {
            if (cnt > resultLength) {
                resultLength = cnt;
                resultElect = length;
            } else if (cnt == resultLength) {
                resultElect = Math.min(length, resultElect);
            }
            return;
        }
        int[] rowCol = core.get(index);
        int thisRow = rowCol[0];
        int thisCol = rowCol[1];
//        System.out.println(thisRow + " " + thisCol + " 에서");
        for (int i = 0; i < 4; i++) {
            Queue<int[]> queue = new ArrayDeque<>();

            int idx = 1;
            boolean flag = true;
            while (0 <= thisRow + dx[i] * idx && thisRow + dx[i] * idx < n && 0 <= thisCol + dy[i] * idx && thisCol + dy[i] * idx < n) {
//                System.out.println((thisRow + dx[i] * idx) + " "+ (thisCol + dy[i] * idx));
                if (elect[thisRow + dx[i] * idx][thisCol + dy[i] * idx] == 0 && !visited[thisRow + dx[i] * idx][thisCol + dy[i] * idx]) {

                    queue.add(new int[]{thisRow + dx[i] * idx, thisCol + dy[i] * idx});
                    visited[thisRow + dx[i] * idx][thisCol + dy[i] * idx] = true;
                    idx++;
                } else {
                    while (!queue.isEmpty()) {
                        int[] thisTurn = queue.poll();
                        visited[thisTurn[0]][thisTurn[1]] = false;
                    }
                    flag = false;
                    break;
                }
            }
            if (flag) {
                backTracking(index + 1, cnt + 1, length + idx - 1);
                while (!queue.isEmpty()) {
                    int[] thisTurn = queue.poll();
                    visited[thisTurn[0]][thisTurn[1]] = false;
                }
            }

        }
        backTracking(index + 1, cnt, length);


    }
}