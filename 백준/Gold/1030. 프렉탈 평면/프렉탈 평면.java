
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int s, n, k, r1, r2, c1, c2;

    static int[][] result;
    public static void main(String[] args) throws Exception{
        input();
        fillFractal();
        print();
    }

    private static void fillFractal() {
        int border = (int) Math.pow(n, s);
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                if (fill(border, i, j)) {
                    result[i - r1][j - c1] = 1;
                }
            }
        }
    }

    private static boolean fill(int border, int row, int col) {
        if (border == 1) {
            return false;
        }
        int chunk = border / n;
        int from = chunk * ((n-k) / 2);
        int to = border - from;
        if (from <= row && row < to && from <= col && col < to) {
            return true;
        }
        return fill(border / n, row % (border / n), col % (border / n));

    }

    private static void print() {
        for (int i = 0; i < r2 - r1 + 1; i++) {
            for (int j = 0; j < c2 - c1 + 1; j++) {
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
    }




    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        if (s == 0) {
            System.out.println(0);
            System.exit(0);
        }
        result = new int[r2 - r1 + 1][c2 - c1 + 1];

    }
}
