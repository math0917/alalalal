


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] matrix;
    static int[][] length;
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        length = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            int col = 0;
            while (col < m){
                if (col == fill(i, col)) {
                    col++;
                } else {
                    col = fill(i, col);
                }
            }

        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    int width = 1;
                    int minHeight = length[i][j] - j;
                    for (int k = i + 1; k < n; k++) {
                        if (length[k][j] - j != 0) {
                            if (minHeight > length[k][j] - j) {
                                result = Math.max(width * minHeight, result);
                                minHeight = length[k][j] - j;
                            }
                            width += 1;
                        } else {
                            break;
                        }
                    }
                    result = Math.max(width * minHeight, result);
                }
            }
        }
        if (result == Integer.MIN_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(result);
            
        } 

    }

    private static int fill(int i, int col) {
//        System.out.println(i + " "+col);
        if (col == m) {
            return m;
        } else {
            if (matrix[i][col] == 0) {
                return length[i][col] = fill(i, col + 1);
            } else {
                return length[i][col] = col;
            }
        }
    }


}
