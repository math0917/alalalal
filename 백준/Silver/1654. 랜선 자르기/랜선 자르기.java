import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static long[] line;
    static long maxLength = Long.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        line = new long[n];
        for (int i = 0; i < n; i++) {
            line[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(line);

        binarySearch();
        System.out.println(maxLength);
    }

    private static void binarySearch() {
        long start = 0, end = line[n - 1] + 1;

        while (start <= end) {
//            System.out.println(start + " " + end);
            long value = 0;
            long mid = (Math.max(1,((long)start + end) / 2));
            for (int i = 0; i < n; i++) {
                value += line[i] / mid;
            }
            if (value == k) {
                maxLength = Math.max(maxLength, mid);
                start = mid + 1;
            } else if (value > k) {
                maxLength = Math.max(maxLength, mid);

                start =  mid + 1;
            } else {
                end = mid - 1;
            }
        }
    }

}