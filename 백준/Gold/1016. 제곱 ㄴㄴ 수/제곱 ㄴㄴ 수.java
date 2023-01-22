



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long min, max;
    static boolean[] prime;
    static Set<Long> set;
    static int count;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());

        prime = new boolean[(int) Math.sqrt(max) + 1];
        set = new HashSet<>();
        count = (int) (max - min +1);
        for (int i = 2; i < (int) Math.sqrt(max) + 1;i++ ) {
            if (prime[i] == false) {
                for (int mul = 2; ; mul++) {
                    if (i * mul < prime.length) {
                        prime[i * mul] = true;
                    } else {
                        break;
                    }
                }
                long square = (long) i * i;
                if (min % square == 0) {
                    set.add(min);
                }
                long l = min / square + 1;
                while (square * l <= max) {
                    set.add(square * l);
                    l += 1;
                }


            }
        }
        count -= set.size();

        System.out.println(count);

    }

    static long findValue(int idx) {
        return (long) idx + min;
    }
}
