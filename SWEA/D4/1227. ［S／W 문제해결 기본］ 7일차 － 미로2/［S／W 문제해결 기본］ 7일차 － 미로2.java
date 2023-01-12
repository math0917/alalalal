

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static int[][] maze;
    public static int[] start;
    public static int[] finish;
    public static boolean[][] visited;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public static Queue<int[]> queue;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        maze = new int[100][100];
        for (int t = 1; t < 11; t++) {
            boolean flag = false;
            br.readLine();
            start = new int[2];
            finish = new int[2];
            queue = new LinkedList<>();
            visited = new boolean[100][100];
            for (int i = 0; i < 100; i++) {
                char[] chars = br.readLine().toCharArray();
                for (int j = 0; j < 100; j++) {
                    maze[i][j] = chars[j] - '0';
                    
                    if (maze[i][j] == 2) {
                        start[0] = i;
                        start[1] = j;
                    } else if (maze[i][j] == 3) {
                        finish[0] = i;
                        finish[1] = j;
                    }
                }
            }
            
            visited[start[0]][start[1]] = true;
            queue.add(new int[]{start[0], start[1]});
            while (!(queue.isEmpty())) {
                int[] thisTurn = queue.poll();
                int thisRow = thisTurn[0], thisCol = thisTurn[1];
                for (int i = 0; i < 4; i++) {
                    int row = thisRow + dx[i], col = thisCol + dy[i];
                    if (0 <= row && row < 100 && 0 <= col && col < 100) {
                        if (maze[row][col] == 0) {
                            if (!(visited[row][col])) {
                                visited[row][col] = true;
                                queue.add(new int[]{row, col});
                            }
                        } else if (maze[row][col] == 3) {
                            flag = true;
                            queue.clear();
                        }
                    }
                }
            }
            if (flag) {
                System.out.printf("#%d %d\n", t, 1);
            } else {
                System.out.printf("#%d %d\n", t, 0);

            }


        }

    }
}
