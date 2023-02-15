
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

//        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            System.out.println(cal(Integer.parseInt(br.readLine()) - 1));
            
        }


    }

    private static long cal(long value) {
        if (value == 0) {
            return 1;
        }
        if (value == 2) {
            return 2;
        } else if (value == 1) {
            return 1;
        } else {
            long val = cal(value / 2);
            return value % 2 == 1 ? val * val % 1000000007 * 4 % 1000000007 : val * val % 1000000007 * 2 %1000000007 ;
        }
    }
}
