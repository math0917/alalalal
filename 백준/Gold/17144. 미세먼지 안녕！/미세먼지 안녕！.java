import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r, c, t;
    static int[][] dirt;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static int[] dirtCleaner;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        dirt = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                dirt[i][j] = Integer.parseInt(st.nextToken());
                if (dirt[i][j] == -1) {
                    dirtCleaner = new int[]{i - 1, j};
                }
            }
        }
        for (int t1 = 0; t1 < t; t1++) {
            Queue<int[]> set = new ArrayDeque<>();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (dirt[i][j] > 0) {
                        set.add(new int[]{i, j, dirt[i][j]});
                        dirt[i][j] = 0;
                    }
                }
            }

            while (!set.isEmpty()) {
                int[] thisTurn = set.poll();
                int mid = thisTurn[2];
                Queue<int[]> queue = new ArrayDeque<>();
                for (int k = 0; k < 4; k++) {
                    int row = thisTurn[0] + dx[k];
                    int col = thisTurn[1] + dy[k];
                    if (0 <= row && row < r && 0 <= col && col < c && dirt[row][col] != -1) {
                        queue.add(new int[]{row, col});
                    }
                }
                int value = thisTurn[2] / 5;
//                System.out.println(value);

                while (!queue.isEmpty()) {
                    int[] poll = queue.poll();
                    dirt[poll[0]][poll[1]] += value;
                    mid -= value;
                }
                dirt[thisTurn[0]][thisTurn[1]] += mid;
            }
//            print();
            int thisRow = dirtCleaner[0];
            int thisCol = dirtCleaner[1];
            thisCol += 1;
            int dir = 0;
            Stack<Integer> q = new Stack<>();

            q.add(dirt[thisRow][thisCol]);

            dirt[thisRow][thisCol] = 0;
            while (thisRow != dirtCleaner[0] || thisCol != dirtCleaner[1]) {
//                System.out.println(thisRow + " " + thisCol);
                int row = thisRow + dx[dir];
                int col = thisCol + dy[dir];
                if (0 <= row && row < r && 0 <= col && col < c) {
                    q.add(dirt[row][col]);
                    if (dirt[row][col] != -1) {
                        dirt[row][col] = 0;

                    }

                    thisRow = row;
                    thisCol = col;
                } else {
                    dir = (dir + 1) % 4;
                }
            }
//            print();
            q.pop();
            q.pop();
//            q.pop();
            dir = 1;
//            thisRow -= 1;
            while (!q.isEmpty()) {
                int row = thisRow + dx[dir];
                int col = thisCol + dy[dir];

//                System.out.println(row + " " + col + " " + dir );

                if (0 <= row && row <= dirtCleaner[0] && 0 <= col && col < c) {
//                    System.out.println(row+ " " + col + " " + dir);
                    dirt[row][col] = q.pop();
                    thisRow = row;
                    thisCol = col;
                } else {
                    dir = (dir - 1 + 4) % 4;
                }
            }

            thisRow = dirtCleaner[0] +1;
            thisCol = dirtCleaner[1];
            thisCol += 1;
            dir = 0;
            q = new Stack<>();

            q.add(dirt[thisRow][thisCol]);

            dirt[thisRow][thisCol] = 0;
            while (thisRow != dirtCleaner[0] + 1 || thisCol != dirtCleaner[1]) {
//                System.out.println(thisRow + " " + thisCol);
                int row = thisRow + dx[dir];
                int col = thisCol + dy[dir];
                if (0 <= row && row < r && 0 <= col && col < c) {
                    q.add(dirt[row][col]);
                    if (dirt[row][col] != -1) {
                        dirt[row][col] = 0;
                    }

                    thisRow = row;
                    thisCol = col;
                } else {
                    dir = (dir - 1 + 4) % 4;
                }
            }
//            print();
            q.pop();
            q.pop();
//            q.pop();
            dir = 3;
//            thisRow += 1;
            while (!q.isEmpty()) {
                int row = thisRow + dx[dir];
                int col = thisCol + dy[dir];

//                System.out.println(row + " " + col + " " + dir );

                if (dirtCleaner[0] + 1 <= row && row < r && 0 <= col && col < c) {
//                System.out.println(row + " " + col + " " + dir );

                    dirt[row][col] = q.pop();
                    thisRow = row;
                    thisCol = col;
                } else {
                    dir = (dir + 1) % 4;
                }
            }
//            print();

        }
        int cnt = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (dirt[i][j] > 0) {
                    cnt += dirt[i][j];
                }
            }
        }
        System.out.println(cnt);


    }

    private static void print() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(dirt[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}