import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int up, front, right;
    static int[] diceLocation;
    static int diceDir;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] map;
    static final int OPPOSITE = 128;
    static int score = 0;
    static boolean[][] visited;
    static int area;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine()
            );
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        up = 2;
        front = 32;
        right = 8;
        diceLocation = new int[2];
        diceLocation[0] = 0;
        diceLocation[1] = 0;
        diceDir = 0;
        while (k-- > 0) {
            move();
        }
        System.out.println(score);
    }

    private static void move() {
        int row = diceLocation[0] + dx[diceDir];
        int col = diceLocation[1] + dy[diceDir];
        int downNumber = 0;

        if (!(0 <= row && row < n && 0 <= col && col < m)) {
            diceDir = (diceDir + 2) % 4;
            row = diceLocation[0] + dx[diceDir];
            col = diceLocation[1] + dy[diceDir];
        }
        shiftDice(diceDir);
        diceLocation[0] = row;
        diceLocation[1] = col;
        int bit = getDownDice();
        for (int i = 1; i <= 6; i++) {
            if ((bit & (1 << i)) != 0) {
                downNumber = i;
                break;
            }
        }
        if (downNumber < map[diceLocation[0]][diceLocation[1]]) {
            diceDir = (diceDir - 1 + 4) % 4;
        } else if (downNumber > map[diceLocation[0]][diceLocation[1]]) {
            diceDir = (diceDir + 1) % 4;
        }
        visited = new boolean[n][m];
        area = 1;
        int upValue = 0;
        int frontValue = 0;
        int rightValue = 0;
        
        visited[diceLocation[0]][diceLocation[1]] = true;
        dfs(diceLocation[0], diceLocation[1]);
        score += map[diceLocation[0]][diceLocation[1]] * area;

    }

    private static void dfs(int thisRow, int thisCol) {

        for (int k = 0; k < 4; k++) {
            int row = thisRow + dx[k];
            int col = thisCol + dy[k];
            if (0 <= row && row < n && 0 <= col && col < m) {
                if (map[row][col] == map[thisRow][thisCol] && !visited[row][col]) {
                    area += 1;
                    visited[row][col] = true;
                    dfs(row, col);
                }
            }
        }
    }

    private static int getDownDice() {
        return OPPOSITE / up;
    }

    private static void shiftDice(int dir) {
        int rear = OPPOSITE / front;
        int left = OPPOSITE / right;
        int down = OPPOSITE / up;

        if (dir == 0) {
            right = up;
            up = left;
        } else if (dir == 3) {
            up = front;
            front = down;
        } else if (dir == 2) {
            up = right;
            right = down;
        } else if (dir == 1) {
            front = up;
            up = rear;
        }
    }
}