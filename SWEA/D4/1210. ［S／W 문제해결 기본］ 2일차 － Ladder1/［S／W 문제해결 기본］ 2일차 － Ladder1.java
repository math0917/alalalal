import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] ladder;
    static int aim;

    static int[] dy = {-1, 1};
    static int result;
    public static void main(String[] args) throws Exception{

//        input을 받고 맨아래에 있는 목적지인 (2) 를 찾아 aim에 저장해놓기!
        input();


    }

    private static void dfs(int thisRow) {
//        System.out.println(thisRow + " " + aim);
        if (thisRow == 0) {
            return;
        }
//       좌우 탐색

        for (int i = 0; i < 2; i++) {
            int row = thisRow;
            int col = aim + dy[i];
            if (0 <= col && col < 100) {
//                System.out.println(ladder[row][col] + " " + row + " " + col);
                if (ladder[row][col] == 1) {
                    aim = directDfs(row, col, (i%2 == 0));
                    break;
                }
            }
        }

        dfs(thisRow - 1);



    }

    private static int directDfs(int thisRow, int thisCol, boolean isLeft) {
        if (isLeft) {
            for (int i = 1; ; i++) {
                if (thisCol - i >= 0) {
                    if (ladder[thisRow][thisCol - i] == 0) {
                        return thisCol - i + 1;
                    }
                } else {
                    return 0;
                }
            }
        } else {
            for (int i = 1; ; i++) {
                if (thisCol + i < 100) {
                    if (ladder[thisRow][thisCol + i] == 0) {
                        return thisCol + i - 1;
                    }
                } else {
                    return 99;
                }
            }
        }

    }

    private static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            br.readLine();
            ladder = new int[100][100];
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    ladder[i][j] = Integer.parseInt(st.nextToken());
                    if (ladder[i][j] == 2) {
                        aim = j;
                    }
                }
            }
            dfs(99);
            System.out.println("#"+t + " " +aim);
        }
    }
}
