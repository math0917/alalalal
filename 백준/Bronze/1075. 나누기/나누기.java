
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long n, k;
    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Long.parseLong(br.readLine());
        k = Long.parseLong(br.readLine());

        long val = n % 100;
        long num = n /100;
        for (long i = 0; i < 100; i++) {
            if ((num * 100 + i) % k == 0) {
                if (i < 10) {
                    System.out.println("0" + i);
                    break;
                } else {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}