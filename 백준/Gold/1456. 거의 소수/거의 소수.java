

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long a, b;
    static boolean[] isPrime;
    static List<Integer> prime;
    static long result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        double logA = Math.log(a);
        double logB = Math.log(b);
        isPrime = new boolean[10000000];
        prime = new ArrayList<>();

        init();

        for (int i = 0;i < prime.size() ; i++) {
            int value = prime.get(i);
            if ((long) value * value > b) {
                break;
            }
            double logValue = Math.log(value);

            for (int j = 2; ; j++) {
                if (logA <= j * logValue && j * logValue <= logB) {
//                    System.out.println(value + " " + j);
                    result += 1;
                } else if (logA > j * logValue) {

                } else {
                    break;
                }
            }
        }
        System.out.println(result);
    }

    private static void init() {
        for (int i = 2; i < isPrime.length; i++) {
            if (!isPrime[i]) {
                prime.add(i);
                for (int j = 2; i * j < isPrime.length; j++) {
                    isPrime[i * j] = true;
                }
            }
        }
    }
}
