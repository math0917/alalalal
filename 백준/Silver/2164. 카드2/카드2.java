

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        pro(n);
    }

    private static void pro(int n) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            deque.addLast(i);
        }
        int num = -1;
        while (true) {
            try {
                num = deque.pollFirst();
                num = deque.pollFirst();
                deque.addLast(num);
            } catch (Exception e) {
                System.out.println(num);
                System.exit(0);
            }
        }
    }
}
