import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    static int[] proc = new int[10];
    static int moveUnder5;
    static int moveUp5;
    static int[] horseLocation;
    static Score[][] scoreBoard;
    static int result = Integer.MIN_VALUE;
    static int thisResult;
    static Set<Integer> gg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            proc[i] = Integer.parseInt(st.nextToken());
        }
        moveUp5 = 0;
        gg = new HashSet<>();
        init();
        dfs(0, 0);
        System.out.println(result);
    }

    private static void init() {
        scoreBoard = new Score[33][2];

        scoreBoard[0][0] = new Score(2,1);
        scoreBoard[1][0] = new Score(4,2);
        scoreBoard[2][0] = new Score(6,3);
        scoreBoard[3][0] = new Score(8,4);
        scoreBoard[4][0] = new Score(10,5);
        scoreBoard[5][0] = new Score(12,6);
        scoreBoard[6][0] = new Score(14,7);
        scoreBoard[7][0] = new Score(16,8);
        scoreBoard[8][0] = new Score(18,9);
        scoreBoard[9][0] = new Score(20,10);
        scoreBoard[10][0] = new Score(22,11);
        scoreBoard[11][0] = new Score(24,12);
        scoreBoard[12][0] = new Score(26,13);
        scoreBoard[13][0] = new Score(28,14);
        scoreBoard[14][0] = new Score(30,15);
        scoreBoard[15][0] = new Score(32,16);
        scoreBoard[16][0] = new Score(34,17);
        scoreBoard[17][0] = new Score(36,18);
        scoreBoard[18][0] = new Score(38, 19);
        scoreBoard[19][0] = new Score(40, 20);
        scoreBoard[20][0] = new Score(0, 21);
        scoreBoard[5][1] = new Score(13, 22);
        scoreBoard[22][0] = new Score(16, 23);
        scoreBoard[23][0] = new Score(19, 24);
        scoreBoard[24][0]= new Score(25, 25);
        scoreBoard[25][0] = new Score(30, 26);
        scoreBoard[26][0] = new Score(35, 27);
        scoreBoard[27][0] = scoreBoard[19][0];
        scoreBoard[10][1] = new Score(22, 28);
        scoreBoard[28][0] = new Score(24, 29);
        scoreBoard[29][0] = scoreBoard[24][0];
        scoreBoard[15][1] = new Score(28, 30);
        scoreBoard[30][0] = new Score(27, 31);
        scoreBoard[31][0] = new Score(26, 32);

        scoreBoard[32][0] = scoreBoard[24][0];




    }


    private static void dfs(int index, int max) {
        if (index == 10) {
            move();
//            System.out.println();
            return;

        } else if (index == 0) {
            moveUnder5 = 1;
            dfs(index + 1, 0);
        } else if (index < 5) {
            int bitIdx = (index / 5) + index % 5;

            for (int i = 0; i <= Math.min(3, max + 1); i++) {
                moveUnder5 |= (1<<(bitIdx * 4 + i));
                dfs(index + 1, Math.max(max, i));
                moveUnder5 ^= (1 << (bitIdx * 4 + i));
            }
        } else {
            int bitIdx = index % 5;

            for (int i = 0; i <= Math.min(3, max + 1); i++) {
                moveUp5 |= (1 <<(bitIdx * 4 + i));
                dfs(index + 1, Math.max(max, i));
                moveUp5 ^= (1 << (bitIdx * 4 + i));

            }
        }


    }

    private static void move() {
        horseLocation = new int[4];
        thisResult = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if ((moveUnder5 & (1 << (4 * i + j))) != 0) {
                    moving(j, horseLocation[j], proc[i]);
//                    System.out.print(j +" "+ horseLocation[j] + " " + thisResult + " |" );
                    if (!check()) {
                        return;
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if ((moveUp5 & (1 << (4 * i + j))) != 0) {
                    moving(j, horseLocation[j], proc[i + 5]);
//                    System.out.print(j +" "+ horseLocation[j] + " " + thisResult + " |");

                    if (!check()) {
                        return;
                    }
                }
            }
        }
//        System.out.println(thisResult);

        result = Math.max(result, thisResult);

    }

    private static boolean check() {
        gg.clear();
        for (int i = 0; i < 4; i++) {
            if (!gg.contains(horseLocation[i]) || horseLocation[i] == 21 || horseLocation[i] == 0) {
                gg.add(horseLocation[i]);
            } else {
                return false;
            }
        }
        return true;
    }

    private static void moving(int number, int loc, int count) {
        int sc = -1;
        if (scoreBoard[loc][1] != null) {
            sc = scoreBoard[loc][1].score;
            loc = scoreBoard[loc][1].num;

            count--;
            while (count-- > 0 && loc !=  21) {
                sc = scoreBoard[loc][0].score;
                loc = scoreBoard[loc][0].num;
            }
        } else {
            while (count-- > 0 && loc !=  21) {
                sc = scoreBoard[loc][0].score;
                loc = scoreBoard[loc][0].num;
            }
        }

        thisResult += sc;
        horseLocation[number] = loc;
    }
}
class Score{
    int score;
    int num;

    public Score(int score, int num) {
        this.score = score;
        this.num = num;

    }
}