
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static long m;
    static long[] level;
    static long[] patty;
    static long count = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());

        level = new long[n];
        patty = new long[n];
        level[0] = 5;
        patty[0] = 3;
        for (int i = 1; i < level.length; i++) {
            level[i] = level[i - 1] * 2 + 3;
            patty[i] = patty[i - 1] * 2 + 1;
        }

        find(n-1,m);
        System.out.println(count);
    }

    private static void find(int lev, long m) {
//        System.out.println(lev + " " + m);
        if (lev == 0) {
            if (m == 2) {
                count += 1;
            } else if (m == 3) {
                count += 2;
            } else if (m == 4) {
                count += 3;
            } else if (m == 5) {
                count += 3;
            }
            return;
        }
        if (level[lev] / 2 + 1< m) {
            count += patty[lev - 1] + 1;
            find(lev - 1, m == level[lev]? level[lev] - (level[lev] / 2 +2) : m - level[lev]/2 - 1);
        } else if (level[lev] / 2 + 1== m) {
            count += patty[lev - 1] + 1;
        } else {
            find(lev - 1, m-1);
        }
    }

}
