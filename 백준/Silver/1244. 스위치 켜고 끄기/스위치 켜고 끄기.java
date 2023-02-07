

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static boolean[] flag;
    static int pro;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        flag = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (Integer.parseInt(st.nextToken()) == 0) {
                flag[i] = false;
            } else {
                flag[i] = true;
            }
        }

        pro = Integer.parseInt(br.readLine());

        for (int i = 0; i < pro; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (p == 1) {
                proc1(num);
            } else {
                proc2(num);
            }
        }
        int index = 0;
        boolean flag1 = true;
        while (flag1) {
            for (int i = 0; i < 20; i++) {
                if (i + index < n) {
                    System.out.print((flag[i + index] == true ? 1 : 0) + " ");
                } else {
                    flag1 = false;
                    break;

                }

            }
            index += 20;
            System.out.println();

        }}

    private static void proc2(int num) {
        int start = num - 1;

        flag[start] = !flag[start];

        for (int i = 1; start - i >= 0 && start + i < n; i++) {
            int left = start - i;
            int right = start + i;
            if (flag[left] == flag[right]) {
                flag[left] = !flag[left];
                flag[right] = !flag[right];
            } else {
                break;
            }
        }
    }

    private static void proc1(int p) {

        for (int start = p - 1; start < n; start += p) {
            flag[start] = !flag[start];
        }
    }


}
