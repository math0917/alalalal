
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int n, k;
    static int[][] puzzle;
    static int gameStatus;
    static int game = 0;
    static int[][] location;
    static Deque<Horse>[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        location = new int[k][2];
        puzzle = new int[n][n];
        map = new Deque[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                puzzle[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new ArrayDeque<>();
            }
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            location[i][0] = row;
            location[i][1] = col;
            Horse horse = new Horse(i, dir);
            map[row][col].addLast(horse);
        }

        game = 0;

        while (game < 1000) {
            gameStatus = 0;
//            System.out.println("언제와?" + game);
            for (int i = 0; i < k; i++) {
                move(i);
            }
            if (gameStatus == 0) {
                System.out.println(-1);
                System.exit(0);
            }
            game ++;
        }
        System.out.println(-1);

    }

    private static void move(int i) {
        int thisRow = location[i][0];
        int thisCol = location[i][1];
        Deque<Horse> horses = map[thisRow][thisCol];
        Deque<Horse> deque = new ArrayDeque<>();
//        System.out.println(game + " "+i+ " " +thisRow + " " + thisCol + " " + horses.size());
        while (true) {
            Horse horse = horses.pollLast();

            deque.add(horse);

            if (horse.num == i) {

                int row = thisRow + dx[horse.dir];
                int col = thisCol + dy[horse.dir];
                if (!(0 <= row && row < n && 0 <= col && col < n) || (puzzle[row][col] == 2)) {
                    horse.dir = (horse.dir / 2) * 2 + (horse.dir + 1) % 2;
                    row = thisRow + dx[horse.dir];
                    col = thisCol + dy[horse.dir];
                    if (!(0 <= row && row < n && 0 <= col && col < n) || (puzzle[row][col] == 2)) {
                        while (!deque.isEmpty()) {
                            horses.add(deque.pollLast());
                        }
                        break;
                    } else {
                        moving(deque, row, col);
                        gameStatus = 1;
                    }
                } else {
                    moving(deque, row, col);
                    gameStatus = 1;
                }
                break;

            }

        }
        confirmGameFinish(i);
    }

    private static void moving(Deque<Horse> deque, int toRow, int toCol) {
        if (puzzle[toRow][toCol] == 1) {
            Deque<Horse> horses = map[toRow][toCol];
            while (!deque.isEmpty()) {
                Horse poll = deque.pollFirst();
                int horseNum = poll.num;
                location[horseNum][0] = toRow;
                location[horseNum][1] = toCol;
                horses.addLast(poll);
            }
        } else {
            Deque<Horse> horses = map[toRow][toCol];
            while (!deque.isEmpty()) {
                Horse poll = deque.pollLast();
                int horseNum = poll.num;
                location[horseNum][0] = toRow;
                location[horseNum][1] = toCol;
                horses.addLast(poll);
            }
        }
    }

    private static void confirmGameFinish(int number) {
//        System.out.println("hi" + game);
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                for (Horse horse : map[i][j]) {
//                    System.out.print(horse + " ");
//                }
//                System.out.print("|");
//            }
//            System.out.println();
//        }
//        for (int i = 0; i < k; i++) {
//            System.out.print(Arrays.toString(location[i]) + " ");
//        }
//        System.out.println();
        int row = location[number][0];
        int col = location[number][1];
        if (map[row][col].size() >= 4) {
            System.out.println(game + 1);
            System.exit(0);
        }
    }
}

class Horse{
    int num;
    int dir;

    public Horse(int num, int dir) {
        this.num = num;
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Horse{" +
                "num=" + num +
                ", dir=" + dir +
                '}';
    }
}