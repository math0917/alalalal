import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static char[][] alpha;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int result = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        alpha = new char[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                alpha[i][j] = line.charAt(j);
            }
        }
        int bit = (1 << (alpha[0][0] - 'A'));
        dfs(0, 0, bit, 1);
        System.out.println(result);
    }

    private static void dfs(int thisRow, int thisCol, int bit, int length) {
        boolean flag = false;
//        System.out.println(thisRow + " " + thisCol + " " + bit + " " + length);
        for (int i = 0; i < 4; i++) {
            int row = thisRow + dx[i];
            int col = thisCol + dy[i];
            if (0 <= row && row < r && 0 <= col && col < c) {
                if ((bit & (1 << (alpha[row][col] - 'A'))) == 0) {
                    dfs(row, col, bit | (1 << (alpha[row][col] - 'A')), length + 1);
                    flag = true;
                }
            }
        }
        if (!flag) {
            result = Math.max(result, length);
        }
    }
}