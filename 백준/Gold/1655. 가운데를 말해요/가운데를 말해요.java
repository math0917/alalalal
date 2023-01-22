

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static PriorityQueue<Integer> minPq;
    static PriorityQueue<Integer> maxPq;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        minPq = new PriorityQueue<>();
        maxPq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                maxPq.add(-Integer.parseInt(br.readLine()));
            } else {
                minPq.add(Integer.parseInt(br.readLine()));
            }
            if (i > 0) {
                int i1 = -maxPq.poll();
                int i2 = minPq.poll();
                if (i1 > i2) {
                    minPq.add(i1);
                    maxPq.add(-i2);
                } else {
                    minPq.add(i2);
                    maxPq.add(-i1);
                }
            }

            sb.append(-(maxPq.peek())).append("\n");
        }
        System.out.println(sb.toString());

    }
}
