

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static int n, procCount;

    static ArrayList<Integer> password;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int t = 1; t <= 10; t++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            password = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                password.add(Integer.parseInt(st.nextToken()));
            }

            procCount = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < procCount; i++) {
                st.nextToken();
                int index = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                ArrayList<Integer> addList = new ArrayList<>();
                for (int j = 0; j < count; j++) {
                    addList.add(Integer.parseInt(st.nextToken()));
                }
                password.addAll(index, addList);

            }
            System.out.print("#"+t+" ");
            for (int i = 0; i < 10; i++) {
                System.out.print(password.get(i) + " ");
            }
            System.out.println();
        }

    }
}
