import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static char[][] bread;
    static boolean[][] visited;
    static int result = 0;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static boolean flag;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        bread = new char[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                bread[i][j] = str.charAt(j);
            }
        }
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            flag = true;
            dfs(i, 0);
        }
        System.out.println(result);
    }

    private static void dfs(int thisRow, int thisCol) {
        if (thisCol == c - 1) {
            result += 1;
            flag = false;
            return;
        }
        for (int k = 0; k < 3 && flag; k++) {
            int row = thisRow + dx[k];
            int col = thisCol + dy[k];
            if (0 <= row && row < r && 0 <= col && col < c) {
                if (bread[row][col] == '.' && !visited[row][col]) {
                    visited[row][col] = true;
                    dfs(row, col);
                }
            }
        }
    }
}