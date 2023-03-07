import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static boolean[][] paper;
    static int[][] accum;
    static Stack<int[]> stack;
    static int result = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        paper = new boolean[100][100];
        accum = new int[100][100];
        for (int t = 0; t < n; t++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    paper[row + i][col + j] = true;
                }
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (paper[i][j]) {
                    accum[i][j] = accum[i][j-1] + 1;
                }
            }
        }
        stack = new Stack<>();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (accum[i][j] > 0) {
                    find(i, j);
                }
            }
        }
        System.out.println(result);

    }

    private static void find(int thisRow, int thisCol) {
        stack.clear();
        stack.add(new int[] {accum[thisRow][thisCol], 1});
        for (int i = thisRow + 1; i < 100; i++) {
            if (stack.peek()[0] <= accum[i][thisCol]) {
                stack.peek()[1] += 1;
            } else {
                int[] peek = stack.peek();
                result = Math.max(peek[0] * peek[1], result);
                peek[0] = accum[i][thisCol];
                peek[1] += 1;
            }
        }
        result = Math.max(stack.peek()[0] * stack.peek()[1], result);
    }
}