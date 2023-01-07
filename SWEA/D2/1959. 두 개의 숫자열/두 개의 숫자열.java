import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    public static int[] numList1;
    public static int[] numList2;
    public static void main(String[] args) throws Exception{
//        System.setIn(new FileInputStream("src/p1/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        for (int i = 1; i < testCase + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            numList1 = new int[n];
            numList2 = new int[m];
            st = new StringTokenizer(br.readLine());
 
            for (int j = 0; j < n; j++) {
                numList1[j] = Integer.parseInt(st.nextToken());
            }
 
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                numList2[j] = Integer.parseInt(st.nextToken());
            }
 
            int result;
            if (n < m) {
                result = calculateMax(n, m, numList1, numList2);
            } else {
                result = calculateMax(m, n, numList2, numList1);
            }
            System.out.printf("#%d %d\n", i, result);
        }
    }
 
    private static int calculateMax(int smallLength, int bigLength, int[] numList1, int[] numList2) {
        int diff = bigLength - smallLength;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i <= diff; i++) {
            int startIdx = i;
 
            int midPart = calculateMid(smallLength, numList1, numList2, startIdx);
 
            maxValue = Integer.max(midPart, maxValue);
        }
        return maxValue;
    }
 
 
 
    private static int calculateMid(int smallLength, int[] numList1, int[] numList2, int bigIndex) {
        int sum = 0;
        int smallIndex = 0;
        for (int i = 0; i < smallLength; i++) {
            sum += numList1[smallIndex++] * numList2[bigIndex++];
        }
        return sum;
    }
 
 
}