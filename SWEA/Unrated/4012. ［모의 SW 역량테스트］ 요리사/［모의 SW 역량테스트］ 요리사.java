

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int testCase;
    static int n;
    static int[][] map;
    static boolean[] visited;

    static int minValue;
    static int[] cook;
    static int[] aCook;
    static int[] bCook;
    static int[] aResult;
    static int[] bResult;
    static int lc ;
    static int rc;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minValue = Integer.MAX_VALUE;
            visited = new boolean[n];
            cook = new int[n];
            aCook = new int[n/2];
            bCook = new int[n/2];
            for (int i = 0; i < n; i++) {
                cook[i] = i;
            }

            permutation(0,0);
            System.out.println("#"+t+" "+minValue);
        }

    }

    private static void permutation(int aIndex, int start) {
        if (aIndex == n / 2) {
//            System.out.println(Arrays.toString(aCook));
            bCook = new int[n / 2];
            int index = 0;
            for (int i = 0; i < n; i++) {
                boolean flag = true;
                for (int j = 0; j < n / 2; j++) {
                    if (aCook[j] == i) {
                        flag= false;
                        break;
                    }
                }
                if (flag) {
                    bCook[index++] = i;
                }
            }
            bResult = new int[2];
            aResult = new int[2];
            lc = 0;
            rc = 0;
            right_part(0, 0);
            left_part(0, 0);
//            System.out.println(lc + " " + rc);
            minValue = Math.min(Math.abs(lc-rc),minValue);

            return;
        }
        for (int i = start; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                aCook[aIndex] = i;
                permutation(aIndex + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    private static void right_part(int index, int start) {
        for (int i = 0; i < aCook.length; i++) {
            int left = aCook[i];
            for (int j = i + 1; j < aCook.length; j++) {
                int right = aCook[j];
                lc += map[left][right] + map[right][left];
            }
        }
    }

    private static void left_part(int index, int start) {
        for (int i = 0; i < bCook.length; i++) {
            int left = bCook[i];
            for (int j = i + 1; j < bCook.length; j++) {
                int right = bCook[j];
                rc += map[left][right] + map[right][left];
            }
        }
    }


}
