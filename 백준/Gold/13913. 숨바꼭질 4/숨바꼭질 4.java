

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int sujin, dongsang;
    static int[] parent;
    static Queue<Node> queue;
    static int result;
    static Set<Integer> visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        parent = new int[200001];

        st = new StringTokenizer(br.readLine());
        sujin = Integer.parseInt(st.nextToken());
        dongsang = Integer.parseInt(st.nextToken());

        parent[sujin] = sujin;
        visited = new HashSet<>();
        visited.add(sujin);
        queue = new ArrayDeque<>();
        queue.add(new Node(sujin, sujin, 0));
        while (!queue.isEmpty()) {
            Node thisTurn = queue.poll();
//            System.out.println(thisTurn.to);
            if (thisTurn.to == dongsang) {
                result = thisTurn.weight;
                break;
            }
            if (thisTurn.to * 2 < 200001) {
                if (!visited.contains(thisTurn.to * 2)) {
                    queue.add(new Node(thisTurn.to, thisTurn.to * 2, thisTurn.weight + 1));
                    visited.add(thisTurn.to * 2);
                    parent[thisTurn.to * 2] = thisTurn.to;
                }
            }
            if (thisTurn.to + 1 < 200001) {
                if (!visited.contains(thisTurn.to + 1)) {
                    queue.add(new Node(thisTurn.to, thisTurn.to + 1, thisTurn.weight + 1));
                    visited.add(thisTurn.to + 1);
                    parent[thisTurn.to + 1] = thisTurn.to;
                }
            }
            if (thisTurn.to - 1 >= 0) {
                if (!visited.contains(thisTurn.to - 1)) {
                    queue.add(new Node(thisTurn.to, thisTurn.to - 1, thisTurn.weight + 1));
                    visited.add(thisTurn.to - 1);
                    parent[thisTurn.to - 1] = thisTurn.to;
                }
            }


        }
        System.out.println(result);
        Stack<Integer>q = new Stack<>();
        while (true) {
            q.add(dongsang);
            if (parent[dongsang] == dongsang) {
                break;
            }
            dongsang = parent[dongsang];

        }

        while (!q.isEmpty()) {
            System.out.print(q.pop() + " ");
        }

    }


}

class Node implements Comparable<Node>{
    int from;
    int to;
    int weight;

    public Node(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        return Integer.compare(weight, o.weight);
    }
}

