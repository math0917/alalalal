import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;
//    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }



        binarySearch();
//        System.out.println(result);

    }

    private static void binarySearch() {
        int l = 0, r = Arrays.stream(arr).max().getAsInt() -  Arrays.stream(arr).min().getAsInt() +1;
        while (l < r) {
            int mid = (l + r) / 2;
//            System.out.println(l + " " + r + " " + find(mid));
            if (find(mid) > m) {
                l = mid + 1;

            } else {
                r = mid;
            }
        }
        System.out.println(r);

    }

    private static int find(int mid) {
        int count = 1;
        List<Integer> ever = new ArrayList<>();
        ever.add(arr[0]);
        for (int i = 1; i < n; i++) {
            for (int a : ever) {
                if (mid < Math.abs(arr[i] - a)) {
                    ever.clear();
                    count ++;
                    break;
                }
            }
            ever.add(arr[i]);
            

        }
        return count;
    }

}