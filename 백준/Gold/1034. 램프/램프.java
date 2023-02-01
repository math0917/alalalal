

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m,k;

    static int[][] lamp;
    static boolean[] isOn;
    static int[][] min;
    static int max = 0;

    public static void main(String[] args) throws Exception{
        input();
        rowValue();
        for (int j = 0; j < n; j++) {
            int changeCount = min[1][j];
            int minCount = min[0][j];
            if (k - changeCount >= 0) {
                if ((k - changeCount) % 2 == 0) {
                    max = Math.max(max, minCount);
                }
            }
        }
        System.out.println(max);
    }

    private static void rowValue() {
        for (int i = 0; i < n; i++) {
            isOn = new boolean[m];
            int booleanCount = 0;
            for (int j = 0; j < m; j++) {
                if (lamp[i][j] % 2 == 0) {
                    isOn[j] = true;
                    booleanCount += 1;
                }
            }
            min[0][i] = colCollector();
            min[1][i] = booleanCount;
        }
    }

    private static int colCollector() {
        int count = 0;
        for (int j = 0; j < m; j++) {
            if (isOn[j]) {
                for (int i = 0; i < n; i++) {
                    lamp[i][j] += 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (lamp[i][j] % 2 == 0) {
                    flag = false;
                }
                if (isOn[j]) {
                    lamp[i][j] += 1;
                }
            }
            if (flag) {
                count += 1;
            }
        }

        return count;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lamp = new int[n][m];
        for (int i = 0; i < n; i++) {
            String thisLine = br.readLine();
            for (int j = 0; j < m; j++) {
                lamp[i][j] = (thisLine.charAt(j) - '0');
            }
        }
        k = Integer.parseInt(br.readLine());
        min = new int[2][n];
    }
}
