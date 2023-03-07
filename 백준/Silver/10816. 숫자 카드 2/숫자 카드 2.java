import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] have;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        have = new int[n];
        for (int i = 0; i < n; i++) {
            have[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        Arrays.sort(have);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int v = Integer.parseInt(st.nextToken());

            int lower = binarySearch(v);
            int upper = binarySearch2(v);
            sb.append( upper - lower ).append(" ");
        }
        System.out.println(sb);
    }

    private static int binarySearch(int key) {
        int start = 0, finish = n, count = 0;
        while (start < finish) {
            int mid = (start + finish) / 2;
            if (have[mid] < key) {
                start = mid + 1;
            } else {
                finish = mid;
            }
        }
        return start;
    }
    private static int binarySearch2(int key) {
        int start = 0, finish = n, count = 0;
        while (start < finish) {
            int mid = (start + finish) / 2;
            if (have[mid] <= key) {
                start = mid + 1;
            } else {
                finish = mid;
            }
        }
        return finish;
    }
}