import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        dp = new int[n+1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = i + 1;
            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            try {
//                day 2에 time으로 cost로 상담을 할 수 있으면
//                day 2 + time - 1 까지 상담을
//                day 1까지의 상담cost + cost로 가능하다.
                dp[idx - 1+ time] = Math.max(dp[idx -1 + time], dp[idx - 1] + cost);
//


            } catch (Exception e) {


            }
            dp[idx] = Math.max(dp[idx], dp[idx - 1]);
//            System.out.println(i +" "+ time + " " + cost);
//            System.out.println(Arrays.toString(dp));
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.stream(dp).max().getAsInt());

    }
}