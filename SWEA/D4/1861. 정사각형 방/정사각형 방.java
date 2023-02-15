import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution{
    static int testCase;
    static int n;
    static int[][] maze;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] result;
    static int length;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());
            maze = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    maze[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            result = new int[]{0, Integer.MIN_VALUE};
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    length = 1;
                    dfs(i, j);
                    if (result[1] < length) {
                        result = new int[]{maze[i][j], length};
                    } else {
                        if (result[1] == length) {
                            if (result[0] > maze[i][j]) {
                                result[0] = maze[i][j];
                            }
                        }
                    }
                }
            }
            System.out.println("#"+t+" "+result[0] +" "+ result[1]);


        }

    }

    private static void dfs(int thisRow, int thisCol) {

        for (int k = 0; k < 4; k++) {
            int row = thisRow + dx[k];
            int col = thisCol + dy[k];

            if (0 <= row && row < n && 0 <= col && col < n) {
                if (maze[thisRow][thisCol] + 1 == maze[row][col]) {
                    dfs(row, col);
                    length += 1;
                }
            }

        }

    }
}
