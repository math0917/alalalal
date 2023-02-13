
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static Deque<Integer> deque;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        deque = new ArrayDeque<>();
        int count = 1;
        for (int i = 1; i <= n; i++) {
            deque.addLast(i);
        }
        System.out.print("<");
        while (deque.size() != 1) {
            deque.addLast(deque.pollFirst());
            if (count++ == k) {
                System.out.print(deque.pollLast()+ ", ");
                count = 1;
            }
        }
        System.out.print(deque.poll()+">");

    }
}
