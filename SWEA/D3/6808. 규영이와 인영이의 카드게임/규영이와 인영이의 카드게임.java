
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static int[] value;
    static int[] my;
    static int win;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            value = new int[9];
            int visited = 0;
            my = new int[9];
            win = 0;
            for (int i = 0; i < 9; i++) {
                value[i] = Integer.parseInt(st.nextToken());
                visited |= (1 << value[i]);
            }
            dfs(0, visited);
            System.out.println("#"+t+ " "+win+ " " + (362880 - win));
        }
    }

    private static void dfs(int index, int visited) {
        if (index == 9) {
//            System.out.println(Arrays.toString(value));
            int winValue = 0;
            int winOppoValue = 0;
            for (int i = 0; i < 9; i++) {
                if (value[i] > my[i]) {
                    winValue += value[i] + my[i];
                } else {
                    winOppoValue += value[i] + my[i];
                }
            }
            if (winValue > winOppoValue) {
                win += 1;
            }
            return;
        }
        for (int i = 1; i <= 18; i++) {
            if (((1<<i) & visited) == 0) {
                my[index] = i;
                dfs(index + 1, visited | (1 << i));
            }
        }
    }
}
