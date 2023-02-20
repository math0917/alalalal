
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, r, c;
    static int result = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        find(n);
        System.out.println(result);
    }

    private static void find(int n) {
        if (n == 1) {
            if (r == 0 && c == 1) {
                result += 1;
            } else if (r == 1 && c == 0) {
                result += 2;
            } else if (r == 1 && c == 1) {
                result += 3;
            }
            return;
        }
        int gap = (int) Math.pow(2, n - 1);
        if (0 <= r && r < gap) {
            if (0 <= c && c < gap) {
                find(n - 1);
            } else {
                result += gap * gap;
                c %= gap;
                find(n - 1);
            }
        } else {
            if (0 <= c && c < gap) {
                result += 2 * gap * gap;
                r %= gap;
                find(n - 1);
            } else {
                result += 3 * gap * gap;
                r %= gap;
                c %= gap;
                find(n - 1);
            }
        }

    }
}
