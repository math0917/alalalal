
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());
            if (value != 0) {
                pq.add(new Node(value));
            } else {
                if (pq.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(pq.poll().value);
                }
            }
        }


    }
}

class Node implements Comparable<Node> {
    int value;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return Math.abs(value) == Math.abs(o.value) ? Integer.compare(value, o.value) : Integer.compare(Math.abs(value), Math.abs(o.value));
    }
}
