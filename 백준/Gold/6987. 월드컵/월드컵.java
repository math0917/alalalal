import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][][] result;
    static int[][] proc;
    static List<int[]> fight;
    static boolean flag;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        result = new int[4][6][3];
        proc = new int[6][3];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    result[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
        fight = new ArrayList<>();

        init(0, 0, false);

        for (int i = 0; i < 4; i++) {
            flag = true;
            fightResult(0, i);
            if (flag) {
                System.out.print(0 + " ");
            } else {
                System.out.print(1 + " ");
            }
        }
    }

    private static void fightResult(int index, int game) {

        if (!valid(game)) {
            return;
        }
        if (index == 15) {
            if (isSame(game)) {
                flag = false;
            }
            return;
        }

        int f1 = fight.get(index)[0];
        int f2 = fight.get(index)[1];
        for (int i = 0; i < 3; i++) {
            proc[f1][i] += 1;
            proc[f2][2- i] += 1;
            fightResult(index + 1, game);
            proc[f1][i] -= 1;
            proc[f2][2 - i] -= 1;
        }




    }

    private static boolean valid(int game) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (proc[i][j] > result[game][i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isSame(int game) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (proc[i][j] != result[game][i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    private static void init(int value, int start, boolean flag) {
        if (flag) {
            for (int i = start; i < 6; i++) {
                fight.add(new int[]{value, i});
            }
            return;
        }
        for (int i = start; i < 6; i++) {
            init(i, i + 1, true);
        }
    }
}