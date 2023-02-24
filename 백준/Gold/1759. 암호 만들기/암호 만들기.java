import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int l, c;
    static char[] alpha;
    static char[] result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        alpha = new char[c];
        for (int i = 0; i < c; i++) {
            alpha[i] = st.nextToken().charAt(0);

        }
        result = new char[l];
        Arrays.sort(alpha);

        combi(0,0);



    }

    private static void combi(int index, int start) {
        if (index == l) {
            int moCount = 0;
            int jaCount = 0;
            for (int i = 0; i < l; i++) {
                if (result[i] == 'a' || result[i] == 'i' || result[i] == 'e' || result[i] == 'o' || result[i] == 'u') {
                    moCount += 1;
                } else {
                    jaCount += 1;
                }
            }
            if (moCount >= 1 && jaCount >= 2) {
                for (int i = 0; i < l; i++) {
                    System.out.print(result[i]);
                }
                System.out.println();
            }
            return;
        }
        for (int i = start; i < c; i++) {

            result[index] = alpha[i];
            combi(index + 1, i + 1);

        }
    }
}