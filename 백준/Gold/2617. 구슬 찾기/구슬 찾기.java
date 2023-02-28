import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static boolean[][][] jewel;
    static int result = 0;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        jewel = new boolean[n][n][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            jewel[p][c][0] = true;
            jewel[c][p][1] = true;
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(jewel[i][j][0] + " " + jewel[i][j][1] + " ㄷ");
//            }
//            System.out.println();
//        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {

                    jewel[j][k][0] = (jewel[j][k][0] || (jewel[j][i][0] && jewel[i][k][0]));
                    jewel[j][k][1] = (jewel[j][k][1] || (jewel[j][i][1] && jewel[i][k][1]));

                }

            }

        }
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(jewel[i][j][0] + " " + jewel[i][j][1] + " ㄷ");
//            }
//            System.out.println();
//        }
        for (int i = 0; i < n; i++) {
            int childCount = 0;
            int parentCount = 0;
            for (int j = 0; j < n; j++) {

                childCount += jewel[i][j][0] ? 1 : 0;
                parentCount += jewel[i][j][1] ? 1 : 0;


            }
//            System.out.println(i + " " + parentCount + " " + childCount);
            if (childCount > n / 2 || parentCount > n / 2) {
                result += 1;
            }
        }
        System.out.println(result);





    }



}