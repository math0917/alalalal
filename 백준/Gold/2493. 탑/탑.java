
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] tower;
    static Deque<int[]> deque;
    static int[] result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        tower = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            tower[i] = Integer.parseInt(st.nextToken());
        }
        result = new int[n];
        deque = new ArrayDeque<>();

        int index = n - 1;

        while (index >= 0) {
            if (deque.isEmpty()) {
                deque.addLast(new int[] {tower[index], index --});
            } else {
                while(!deque.isEmpty()&&tower[index] >= deque.peekLast()[0]){
                    int[] ints = deque.pollLast();
                    result[ints[1]] = index+ 1;
                }
                deque.add(new int[]{tower[index ], index });
                index --;

            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
