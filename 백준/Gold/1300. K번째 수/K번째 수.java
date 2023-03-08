import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static long n, k;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Long.parseLong(br.readLine());
        k = Long.parseLong(br.readLine());
//        List<Integer> list = new ArrayList<>();
//        list.add(0);
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                list.add(i * j);
//            }
//        }
//        Collections.sort(list);
//        System.out.println(list.get(k));
        System.out.println(binarySearch());
    }

    private static long binarySearch() {
        long l = 1, r = (long)n * n + 1;

        while (l < r) {
            long mid = (l + r) / 2;
            if (find(mid) < k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l ;
    }

    private static long find(long mid) {
        long count = 0;
        for (int i = 1; i <= n; i++) {
            count += Math.min(mid, (long)n * i) / i;


        }
//        System.out.println(count);
        return count;
    }


}