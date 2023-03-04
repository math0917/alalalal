import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] tree;
    static boolean flag = true;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int twoCount = 0;
        int oneCount = 0;
        for (int j = 0;j < n; j++) {
            int i = Integer.parseInt(st.nextToken());
            if (i > 0) {
                while (i >= 2) {
                    twoCount += 1;
                    i -= 2;
                }
                if (i == 1) {
                    oneCount += 1;
                }
            }
        }

        while (true) {
            if (oneCount > twoCount) {
                System.out.println("NO");
                System.exit(0);
            } else if (oneCount == twoCount) {
                System.out.println("YES");
                System.exit(0);
            } else {
                twoCount -= oneCount;
                oneCount = 0;
                twoCount -= 2;
                oneCount += 1;
            }
        }






    }
}