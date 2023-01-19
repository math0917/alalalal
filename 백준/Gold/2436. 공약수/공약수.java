

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int gcd;
    static int lcm;
    static int result;
    static int result1;
    static int result2;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        gcd = Integer.parseInt(st.nextToken());
        lcm = Integer.parseInt(st.nextToken());

        result1 = 1;
        result2=  lcm/gcd;
        int lcmDivisor = lcm / gcd;
        for (int i = 2; i <= (int) Math.sqrt(lcmDivisor); i++) {

            if ((lcmDivisor % i) == 0) {
                if (((lcmDivisor) / i) %i != 0){
                    boolean flag = true;
                    for (int j = 2; j <= (int) Math.sqrt(i); j++) {
                        if (i % j == 0) {
                            if (((lcmDivisor/ i) % j) == 0){
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        result1 = i;
                        result2 = lcmDivisor / i;
                    }
                }

            }
        }

        System.out.printf("%d %d", result1 * gcd, result2 *gcd);

    }


}
