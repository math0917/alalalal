
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m, k;
    static int[][] matrix;
    static int[][] proc;
    static int[] result;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int maxSum = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        proc = new int[k][3];
        for (int t = 0; t < k; t++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int length = Integer.parseInt(st.nextToken());
            proc[t][0] = row;
            proc[t][1] = col;
            proc[t][2] = length;

        }
        result = new int[k];
        dfs(0, 0);
        System.out.println(maxSum);
    }

    private static void dfs(int index, int bit) {
        if (index == k) {
            int[][] copyMatrix = new int[n][m];
            for (int i = 0; i < n; i++) {
                copyMatrix[i] = matrix[i].clone();

            }

            for (int idx : result) {
                rotate(proc[idx]);
            }

            for (int i = 0; i < n; i++) {
                maxSum = Math.min(maxSum,Arrays.stream(matrix[i]).sum());
            }
            matrix = copyMatrix;
            return;
        }
        for (int i = 0; i < k; i++) {
            if ((bit & (1 << i)) == 0) {
                result[index] = i;
                dfs(index + 1, bit | (1 << i));
            }
        }
    }

    private static void rotate(int[] ints) {
        int thisRow = ints[0];
        int thisCol = ints[1];
        int length = ints[2];
        for (int i = 1; i <= length; i++) {

            int direction = 0;
            Queue<Integer> queue = new ArrayDeque<>();

            int startRow = thisRow - i;
            int startCol = thisCol - i;
            for (int p = 0; p < 4; p++) {
                for (int k = 0; k < 2 * i; k++) {
                    queue.add(matrix[startRow][startCol]);
                    startRow += dx[direction];
                    startCol += dy[direction];
                }
                direction += 1;
            }



            startRow = thisRow - i;
            startCol = thisCol - i + 1;
            direction = 0;

            for (int k = 1; k < 2 * i; k++) {
                matrix[startRow][startCol] = queue.poll();
                startRow += dx[direction];
                startCol += dy[direction];
            }
            direction += 1;
            for (int p = 0; p < 3; p++) {
                for (int k = 0; k < 2 * i; k++) {
                    matrix[startRow][startCol] = queue.poll();
                    startRow += dx[direction];
                    startCol += dy[direction];
                }
                direction += 1;
            }
//            System.out.println(startCol);
            matrix[startRow][startCol] = queue.poll();


//            for (int r = 0; r < n; r++) {
//                for (int c = 0; c < m; c++) {
//                    System.out.print(matrix[r][c] + " ");
//
//                }
//                System.out.println();
//            }
//            System.out.println();
        }

    }
}
