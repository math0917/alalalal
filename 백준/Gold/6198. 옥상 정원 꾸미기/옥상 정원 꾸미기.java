
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] height;
    static Deque<int[]> deque;
    static long[] result;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        height = new int[n];
        result = new long[n];
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(br.readLine());
        }
        deque = new ArrayDeque<>();
        int index = 0;
        while (index < n) {

            if (deque.isEmpty()) {
                deque.add(new int[]{height[index], index});
                index ++;
            } else {
                if (deque.peekLast()[0] > height[index]) {
                    deque.add(new int[]{height[index], index});
                    index++;
                } else {
                    int count = 0;
                    while (!deque.isEmpty()&&deque.peekLast()[0] <= height[index]) {
                        result[deque.pollLast()[1]] += count++;
                    }
                    for (int i = 0; i < deque.size(); i++) {
                        int[] ints = deque.pollFirst();
                        result[ints[1]] += count;
                        deque.addLast(ints);
                    }
                    deque.add(new int[]{height[index], index});
                    index++;
                }
            }

        }
        System.out.println(Arrays.stream(result).sum() + ((long)(deque.size()- 1) * deque.size())/2);




    }
}
