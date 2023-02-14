
import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r;
    static int[][] matrix;
    static int[][] result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        matrix = new int[n][m];
        result = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < Math.min(n, m) / 2; i++) {
            int rowCount = n - 2 * i;
            int colCount = m - 2 * i;
            int rotation = (rowCount - 1)* 2 + (colCount- 1) * 2;
//            System.out.println(rotation);
            int realRotate = r % rotation;
            rotate(i, realRotate, rotation);
//            System.out.println("끝");
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void rotate(int index,int rotateCount, int howMany) {
        int start = index;
        int rowFinish = n - 1 - index;
        int colFinish = m - 1 - index;

        int[] next = new int[] {start,start};

        for (int i = 0; i < rotateCount; i++) {
            next = nextIndex(next[0], next[1], start, rowFinish, colFinish);
        }

        int row = start;
        int col = start;

        for (int i = 0; i < howMany; i++) {

            result[next[0]][next[1]] = matrix[row][col];
            next = nextIndex(next[0], next[1], start, rowFinish, colFinish);
//            System.out.println(next[0] +" " + next[1] + " " + row +" "+ col + " 11");
            int[] ints = nextIndex(row, col, start, rowFinish, colFinish);
//            System.out.println(next[0] +" " + next[1] + " " + row +" "+ col + " 21");
            row = ints[0];
            col = ints[1];
//            System.out.println(next[0] +" " + next[1] + " " + row +" "+ col + " 31");
        }

    }

    private static int[] nextIndex(int row, int col, int start, int rowFinish, int colFinish) {
//        System.out.println(row +" " + col + " " + start +" "+ rowFinish + " " + colFinish);

        if (row == start) {
            if (col == start) {
                return new int[]{row + 1, col};
            } else {
                return new int[]{row, col - 1};
            }
        } else if (rowFinish == row) {
            if (col == colFinish) {
                return new int[]{row - 1, col};
            } else {
                return new int[]{row, col + 1};
            }
        } else if (col == start) {
            return new int[]{row + 1, col};
        } else {
            return new int[]{row - 1, col};
        }
    }
}
