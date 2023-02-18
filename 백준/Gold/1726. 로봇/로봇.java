
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] robot;
    static int startRow, startCol, startDir;
    static int finishRow, finishCol, finishDir;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][][] visited;
    static Queue<Dot> queue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        robot = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                robot[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        st = new StringTokenizer(br.readLine());
        startRow = Integer.parseInt(st.nextToken()) - 1;
        startCol = Integer.parseInt(st.nextToken()) - 1;
        startDir = Integer.parseInt(st.nextToken()) - 1;

        if (startDir == 1) {
            startDir = 2;
        } else if (startDir == 2) {
            startDir = 1;
        }
        st = new StringTokenizer(br.readLine());
        finishRow = Integer.parseInt(st.nextToken()) - 1;
        finishCol = Integer.parseInt(st.nextToken()) - 1;
        finishDir = Integer.parseInt(st.nextToken()) - 1;
        if (finishDir == 1) {
            finishDir = 2;
        } else if (finishDir == 2) {
            finishDir = 1;
        }
        visited = new boolean[n][m][4];
        visited[startRow][startCol][startDir] = true;
        queue = new ArrayDeque<>();
        queue.add(new Dot(startRow, startCol, startDir, 0));

        while (!queue.isEmpty()) {
            Dot thisDot = queue.poll();
//            System.out.println(thisDot.row + " "+thisDot.col + " "+thisDot.dir + " " + thisDot.length + " 에서");
            if (thisDot.row == finishRow && thisDot.col == finishCol && thisDot.dir == finishDir) {
                System.out.println(thisDot.length);
                break;
            }
            for (int i = 1; i <= 3; i++) {
                int row = thisDot.row + dx[thisDot.dir] * i;
                int col = thisDot.col + dy[thisDot.dir] * i;
                if (0 <= row && row < n && 0 <= col && col < m) {
                    if (robot[row][col] == 0) {
                        if (!visited[row][col][thisDot.dir]) {
                            visited[row][col][thisDot.dir] = true;
                            queue.add(new Dot(row, col, thisDot.dir, thisDot.length + 1));
                        }
                    } else {
                        break;
                    }
//                    System.out.println(row + " " + col + " " + thisDot.dir + " " + thisDot.length + 1);

                } else {
                    break;
                }
            }
            int changeDir = (thisDot.dir + 1) % 4;
            if (!visited[thisDot.row][thisDot.col][changeDir]) {
                visited[thisDot.row][thisDot.col][changeDir] = true;
//                System.out.println(thisDot.row + " "+thisDot.col + " "+changeDir + " " + thisDot.length + 1 );

                queue.add(new Dot(thisDot.row, thisDot.col, changeDir, thisDot.length + 1));

            }

            changeDir = (thisDot.dir - 1 + 4) % 4;
            if (!visited[thisDot.row][thisDot.col][changeDir]) {
                visited[thisDot.row][thisDot.col][changeDir] = true;
//                System.out.println(thisDot.row + " "+thisDot.col + " "+changeDir + " " + thisDot.length + 1 );

                queue.add(new Dot(thisDot.row, thisDot.col, changeDir, thisDot.length + 1));
            }
        }


    }
}

class Dot{
    int row;
    int col;

    public Dot(int row, int col, int dir, int length) {
        this.row = row;
        this.col = col;
        this.dir = dir;
        this.length = length;
    }

    int dir;
    int length;
}
