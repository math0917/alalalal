import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] puzzle;
    static int result = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        puzzle = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                puzzle[i][j] = line.charAt(j) - '0';
            }
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(puzzle[i][j] + " ");
//            }
//            System.out.println();
//        }

        dfs(0, 0, 0);
        System.out.println(result);
    }

    private static void dfs(int i, int bit, int value) {
        if (i == n * m) {
            result = Math.max(value, result);
//            System.out.println("까지 "+ value);
            return;
        }
//        System.out.println(i + " " + bit + " "+ value);
        if ((bit & (1 << i)) == 0) {
            int val = i / m;
            int left = i % m;
            int downBit = bit;
            int rightBit = bit;
            int vv = 0;

            for (int idx = 0; idx + val < n; idx++) {

                dfs(i + 1, downBit | (1 << ((val + idx) * m + left)), value + vv + puzzle[idx+val][left]);
                downBit |= (1 << ((val + idx) * m + left));
                vv = (vv + puzzle[idx + val][left]) * 10;
            }

            vv = 0;
            for (int idx = 0; idx + left < m; idx++) {
                if ((bit & (1 << (val * m + left + idx))) == 0) {
                    dfs(i + 1, rightBit | (1 << (val * m + left + idx)), value + vv + puzzle[val][idx + left]);
                    rightBit |= (1 << (val * m + left + idx));
                    vv = (vv + puzzle[val][idx + left]) * 10;
                } else {
                    break;
                }


            }
        } else {
            dfs(i + 1, bit, value);
        }
    }
}