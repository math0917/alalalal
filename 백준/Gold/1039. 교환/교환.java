

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String n;
    static int k;
    static int pro = -1;
    static Queue<Node> queue;
    static int max = Integer.MIN_VALUE;
    static Set<String> set;
    static int proc = -1;
    static Node poll;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = st.nextToken();
        k = Integer.parseInt(st.nextToken());


        if ((n.length() == 1 && k > 0)|| (n.length() == 2 && n.charAt(1) == '0' && k > 0)) {
            System.out.println(-1);
            System.exit(0);
        }
        queue = new ArrayDeque<>();
        queue.add(new Node(n, 0));

        while (!queue.isEmpty()) {
            poll = queue.poll();
            if (proc != poll.length) {
                proc = poll.length;
                set = new HashSet<>();
            }
            
            if (poll.length == k) {
                max = Math.max(max, Integer.parseInt(poll.num));
                continue;
            }
            for (int i = 0; i < poll.num.length() - 1; i++) {
                for (int j = i + 1; j < poll.num.length(); j++) {
                    if (i == 0) {
                        if (poll.num.charAt(j) == '0') {
                            continue;
                        }
                    }
                    String str = changeIndex(poll.num, i, j);
                    if (!set.contains(str)) {
                        queue.add(new Node(str, poll.length + 1));
                        set.add(str);
                    }
                }
            }
        }
        System.out.println(max);

    }



    private static String changeIndex(String num, int i, int i1) {
        return new StringBuilder().append(num.substring(0, i)).append(num.charAt(i1)).append(num.substring(i + 1, i1)).append(num.charAt(i)).append(num.substring(i1 + 1, n.length())).toString();
    }
}

class Node{
    String num;
    int length;

    public Node(String num, int length) {
        this.num = num;
        this.length = length;
    }
}