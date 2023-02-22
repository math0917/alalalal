
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int testCase;
    static int n;
    static PriorityQueue<Integer> oddPq;
    static PriorityQueue<Integer> evenPq;
    static int[] tree;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            tree = new int[n];
            oddPq = new PriorityQueue<>(Collections.reverseOrder());
            evenPq = new PriorityQueue<>(Collections.reverseOrder());
            for (int i = 0; i < n; i++) {
                tree[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(tree);

            for (int i = 0; i < n - 1; i++) {
                int diff = tree[n - 1] - tree[i];
                if (diff == 0) {
                    break;
                } else if (diff % 2 == 1) {
                    oddPq.add(diff);
                } else {
                    evenPq.add(diff);
                }

            }
            int days = 1;
            while (!oddPq.isEmpty() || !evenPq.isEmpty()) {
//                System.out.println(oddPq.peek() + " "+ evenPq.peek());
                switch (days % 2) {
                    case 0:
                        if (evenPq.isEmpty()) {
                            if (oddPq.peek() > 1) {
                                oddPq.add(oddPq.poll() - 2);
                            }
                        } else {
                            Integer val = evenPq.poll();
                            if (val - 2 != 0) {
                                evenPq.add(val - 2);
                            }
                        }
                        days ++;
                        break;
                    case 1:
                        if (oddPq.isEmpty()) {
                            if (evenPq.size() != 1) {
                                Integer val = evenPq.poll();
                                oddPq.add(val - 1);
                            }
                        } else {
                            Integer val = oddPq.poll();
                            if (val - 1 != 0) {
                                evenPq.add(val - 1);
                            }
                        }
                        days ++;
                }
            }
            System.out.println("#"+ t+" " + (days -1));
        }

    }
}
