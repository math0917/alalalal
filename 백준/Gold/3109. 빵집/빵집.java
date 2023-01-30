

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static char[][]  map;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static boolean[][] visited;


    static int result = 0;
    public static void main(String[] args) throws Exception{
        input();

        for (int i = 0; i < r; i++) {
            if (dfs(i, 0)) {
                result += 1;
            }
        }

        System.out.println(result);
    }

    private static boolean dfs(int thisRow, int thisCol) {
        if (thisCol == c - 1) {
            return true;
        }
        for (int i = 0; i < 3; i++) {
            int row = thisRow + dx[i];
            int col = thisCol + dy[i];
            if (0 <= row && row < r && 0 <= col && col < c) {
                if (map[row][col] == '.' && !visited[row][col]) {
                    visited[row][col] = true;
                    if (dfs(row, col)) {
                        map[row][col] = 'x';
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String thisLine = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = thisLine.charAt(j);
            }
        }
    }


}
