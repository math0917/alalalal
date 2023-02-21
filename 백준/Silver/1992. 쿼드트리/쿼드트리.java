import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] dot;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        dot = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                dot[i][j] = line.charAt(j) - '0';
            }
        }

        find(0, 0, n);

    }

    private static void find(int thisRow, int thisCol, int n) {
        int value = dot[thisRow][thisCol];
        boolean flag = true;
        for (int i = thisRow; i < thisRow + n; i++) {
            for (int j = thisCol; j < thisCol + n; j++) {
                if (value != dot[i][j]) {
                    flag= false;
                }
            }
        }
        if (flag) {
            System.out.print(value);
        } else {
            System.out.print("(");
            find(thisRow, thisCol, n/2);
            find(thisRow, thisCol + n/2, n/2);
            find(thisRow + n/2, thisCol, n/2);
            find(thisRow + n/2, thisCol + n/2, n/2);
            System.out.print(")");
        }

    }
}