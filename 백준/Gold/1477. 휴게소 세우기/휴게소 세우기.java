import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m, l;
    static int[] road;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        road = new int[n+ 2];
        st = new StringTokenizer(br.readLine());
        road[0] = 0;
        for (int i = 0; i < n; i++) {
            road[i+1] = Integer.parseInt(st.nextToken());
        }
        road[n+1] = l;

        Arrays.sort(road);
        System.out.println(binarySearch());

    }

    private static int binarySearch() {
        int left = 1, right = l;
        while (left < right) {
            int mid = (left + right) / 2;
//            System.out.println(left + " " + right + " " + findMin(mid));
            if (findMin(mid) <= m) {
                right = mid;
            } else {
                left = mid + 1;

            }
        }

        return left;
    }

    private static int findMin(int mid) {
        int count = 0;

        for (int i = 0; i < n + 1; i++) {
            int val = road[i + 1];
            int k = road[i];
            if ((val - k) % mid == 0) {
                count -=1;
            }

            count += (val - k) / mid;

        }
        return count;
    }

}