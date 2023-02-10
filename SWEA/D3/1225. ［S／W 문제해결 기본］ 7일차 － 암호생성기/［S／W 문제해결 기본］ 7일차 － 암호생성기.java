

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
    static int testCase= 10;
    static Deque<Integer> deque;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;



        for (int t = 1; t <= testCase; t++) {
            br.readLine();
            deque = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 8; j++) {
                deque.add(Integer.parseInt(st.nextToken()));
            }
            int num = 1;
            for (; ; ) {
                Integer int1 = deque.pollFirst();
                int1 -= num++;
                if (num == 6) {
                    num = 1;
                }
                if (int1 <= 0) {
                    deque.addLast(0);
                    break;
                }
                deque.addLast(int1);
            }
            System.out.print("#"+t+" ");
            while (!deque.isEmpty()) {
                System.out.print(deque.poll() + " ");

            }
            System.out.println();
        }
    }
}
