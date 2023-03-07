import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, c;
    static int[] cable;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        cable = new int[n];
        for (int i = 0; i < n; i++) {
            cable[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(cable);

        System.out.println(binarySearch(c));

    }

    private static int binarySearch(int c) {
        int l = 1, h = cable[n-1] - cable[0] + 1;
        while (l < h) {
            int mid = (l + h) / 2;
//            System.out.println(mid + " " + installCount(mid));
            // start를 높이면 카운트가 줄고
//            finish를 줄이면 카운트가 늘음
            if (installCount(mid) >= c) {
                l = mid + 1;
            } else {
//                카운트가 크거나 같으면 계속 줄여나가봐야함
                h = mid;
            }
        }
        return l - 1;
    }

    private static int installCount(int length) {
        int v = cable[0];
        int idx = 1;
        int count = 1;
        while (idx < n) {
            if (cable[idx] - v >= length) {
                v = cable[idx];
                count += 1;
            }
            idx +=1;
        }
        return count;

    }
}