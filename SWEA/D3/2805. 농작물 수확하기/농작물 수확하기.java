import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int testCase;
    static int n;
    static int[][] farm;
    public static void main(String[] args) throws Exception{
   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());
            farm = new int[n][n];
            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                for (int j = 0; j < n; j++) {
                    farm[i][j] = line.charAt(j)-'0';
                }

            }

            int mid = n / 2;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (Math.abs(i - mid) + Math.abs(j - mid) <= mid) {
                        sum += farm[i][j];
                    }
                }
            }
            System.out.println("#"+t+" "+sum);

        }
    }
}
