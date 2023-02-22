import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int testCase;
    static int n;
    static int startX,startY, finishX, finishY;
    static int[][] dot;
    static int min;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            min = Integer.MAX_VALUE;
            n = Integer.parseInt(br.readLine());
            dot = new int[n][2];
            st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            finishX = Integer.parseInt(st.nextToken());
            finishY = Integer.parseInt(st.nextToken());
            for (int i = 0; i < n; i++) {
                dot[i][0] = Integer.parseInt(st.nextToken());
                dot[i][1] = Integer.parseInt(st.nextToken());
            }
            dfs(0, 0, -1);
            System.out.println("#"+ t + " " +min);
        }

    }

    private static void dfs(int bit, int length, int lastVisit) {


        if (bit == 0) {
            for (int j = 0; j < n; j++) {
                dfs(1 << j, Math.abs(dot[j][0] - startX) + Math.abs(dot[j][1] - startY), j);
            }
            return;
        }
        if (bit == ((int) Math.pow(2, n)) - 1) {
            min = Math.min(length + Math.abs(dot[lastVisit][0] - finishX) + Math.abs(dot[lastVisit][1] - finishY), min);
        }
        for (int i = 0; i < n; i++) {
            if ((bit & (1 << i)) ==0) {
                dfs(bit | (1 << i), length + Math.abs(dot[lastVisit][0] - dot[i][0]) + Math.abs(dot[lastVisit][1] - dot[i][1]), i);
            }
        }



    }
}