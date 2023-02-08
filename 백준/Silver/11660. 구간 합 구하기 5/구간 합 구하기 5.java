

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] matrix;
    static int[][] accum;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }

        }
        accum = new int[n][n + 1];
        init();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int row1 = Integer.parseInt(st.nextToken())-1,
                    col1 = Integer.parseInt(st.nextToken())-1,
                    row2 = Integer.parseInt(st.nextToken())-1,
                    col2 = Integer.parseInt(st.nextToken());
            int sum = 0;
            for (int j = row1; j <= row2; j++) {
                sum += accum[j][col2] - accum[j][col1];
            }
            sb.append(sum + "\n");
        }
        System.out.println(sb.toString());

    }

    private static void init() {
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 1; j < n + 1; j++) {
                sum += matrix[i][j - 1];
                accum[i][j] = sum;
            }
        }
    }
}
