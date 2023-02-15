
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r;
    static int[][] matrix;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < r; i++) {
            proc(Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void proc(int pro) {
        switch (pro) {
            case 1 :
                reverseUpDown();
                break;
            case 2:
                reverseLeftRight();
                break;
            case 3:
                rotate90();
                break;
            case 4:
                rotateLeft90();
                break;
            case 5:
                moveRight();
                break;
            case 6:
                moveLeft();
                break;
        }
    }


    private static void reverseUpDown() {
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n-1-i][j] = temp;
            }
        }
    }

    private static void reverseLeftRight() {
        for (int j = 0; j < m/2; j++) {
            for (int i = 0; i < n; i++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][m - 1 - j];
                matrix[i][m - 1 - j] = temp;
            }
        }
    }

    private static void rotate90() {

        int[][] newMatrix = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMatrix[j][n - 1 - i] = matrix[i][j];
            }
        }
        matrix = newMatrix;
        int temp = n;
        n = m;
        m = temp;
    }

    private static void rotateLeft90() {
        int[][] newMatrix = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = m - 1; j >= 0 ; j--) {

                newMatrix[m - 1 - j][i] = matrix[i][j];
            }
        }
        matrix = newMatrix;
        int temp = n;
        n = m;
        m = temp;
    }

    private static void moveRight() {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                queue.addLast(matrix[i][j]);
            }
        }
        for (int i = 0; i < n / 2; i++){
            for (int j = m / 2; j < m; j++) {
                queue.addLast(matrix[i][j]);
            }
        }

        for (int i = n / 2; i < n; i++) {
            for (int j = m / 2; j < m; j++) {
                queue.addLast(matrix[i][j]);

            }
        }
        for (int i = n / 2; i < n; i++){
            for (int j = 0; j < m / 2; j++) {
                queue.addLast(matrix[i][j]);

            }
        }

        for (int i = 0; i < n / 2; i++) {
            for (int j = m / 2; j < m; j++) {

                matrix[i][j] = queue.pollFirst();
            }
        }
        for (int i = n / 2; i < n; i++) {
            for (int j = m / 2; j < m; j++) {
                matrix[i][j] = queue.pollFirst();

            }
        }
        for (int i = n / 2; i < n; i++){
            for (int j = 0; j < m / 2; j++) {
                matrix[i][j] = queue.pollFirst();

            }
        }
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                matrix[i][j] = queue.pollFirst();
            }

        }



    }

    private static void moveLeft() {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                queue.addLast(matrix[i][j]);
            }
        }
        for (int i = 0; i < n / 2; i++){
            for (int j = m / 2; j < m; j++) {
                queue.addLast(matrix[i][j]);
            }
        }

        for (int i = n / 2; i < n; i++) {
            for (int j = m / 2; j < m; j++) {
                queue.addLast(matrix[i][j]);

            }
        }
        for (int i = n / 2; i < n; i++){
            for (int j = 0; j < m / 2; j++) {
                queue.addLast(matrix[i][j]);

            }
        }
        for (int i = n / 2; i < n; i++){
            for (int j = 0; j < m / 2; j++) {
                matrix[i][j] = queue.pollFirst();

            }
        }
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                matrix[i][j] = queue.pollFirst();
            }

        }

        for (int i = 0; i < n / 2; i++) {
            for (int j = m / 2; j < m; j++) {

                matrix[i][j] = queue.pollFirst();
            }
        }

        for (int i = n / 2; i < n; i++) {
            for (int j = m / 2; j < m; j++) {
                matrix[i][j] = queue.pollFirst();

            }
        }

    }
}
