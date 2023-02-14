
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            n = Integer.parseInt(br.readLine());
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                char value = st.nextToken().charAt(0);
                if (i < n / 2) {
                    if (!(value == '+' || value == '-' || value == '*' || value == '/')) {
                        flag = false;
                    }
                } else {
                    if (value == '+' || value == '-' || value == '*' || value == '/'){
                        flag = false;
                    }
                }

            }
            if (flag) {
                System.out.println("#" + t + " " + 1);
            } else {
                System.out.println("#" + t + " " + 0);

            }

        }

    }
}
