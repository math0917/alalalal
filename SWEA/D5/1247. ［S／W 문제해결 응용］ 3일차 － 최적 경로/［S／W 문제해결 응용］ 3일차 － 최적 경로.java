
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static int[] home;
    public static int[] company;
    public static int[][] loc;
    public static int[] numbers;
    public static int[] combi;
    public static int n;
    public static int minValue;
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t < testCase + 1; t++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            home = new int[2];
            company = new int[2];
            loc = new int[n][2];
            combi = new int[n];
            numbers = new int[n];
            home[0] = Integer.parseInt(st.nextToken());
            home[1] = Integer.parseInt(st.nextToken());

            company[0] = Integer.parseInt(st.nextToken());
            company[1] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                loc[i][0] = Integer.parseInt(st.nextToken());
                loc[i][1] = Integer.parseInt(st.nextToken());
                numbers[i] = i;
            }
            minValue = Integer.MAX_VALUE;
            combinations(0,0);
            System.out.printf("#%d %d\n", t, minValue);

        }

    }
    static void combinations(int bit, int index) {
        if (index == n) {
            
            int thisTurnResult = 0;
            for (int i = 0; i < n - 1; i++) {
                thisTurnResult += Math.abs(loc[combi[i]][0] - loc[combi[i+1]][0]);
                thisTurnResult += Math.abs(loc[combi[i]][1] - loc[combi[i+1]][1]);
            }
            thisTurnResult += Math.abs(company[0] - loc[combi[0]][0]);
            thisTurnResult += Math.abs(company[1] - loc[combi[0]][1]);
            thisTurnResult += Math.abs(home[0] - loc[combi[n-1]][0]);
            thisTurnResult += Math.abs(home[1] - loc[combi[n-1]][1]);
            minValue = Math.min(thisTurnResult, minValue);
        }
        for (int i = 0; i < n; i++) {
            if (((1<<i) & bit) == 0) {
                combi[index] = i;
                combinations(bit | (1<<i), index + 1);

            }
        }
    }

}
