



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static PriorityQueue<Node> pq;
    static PriorityQueue<Node> reversePq;
    public static void main(String[] args) throws Exception{
  
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int t = 1; t <= 10; t++) {
            n = Integer.parseInt(br.readLine());
            pq = new PriorityQueue<>();
            reversePq = new PriorityQueue<>(Collections.reverseOrder());
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < 100; k++) {
                Node node = new Node(Integer.parseInt(st.nextToken()));
                pq.add(node);
                reversePq.add(node);
            }
            while (n-- > 0) {
                Node pqPoll = pq.poll();
                Node reversePoll = reversePq.poll();

                reversePoll.x -= 1;
                pqPoll.x += 1;

                pq.add(pqPoll);
                reversePq.add(reversePoll);
            }
            System.out.println("#"+ t+ " " + (reversePq.poll().x - pq.poll().x));



        }
    }
}


class Node implements Comparable<Node> {

    int x;

    public Node(int x) {
        this.x = x;
    }


    @Override
    public int compareTo(Node o) {
        return Integer.compare(x, o.x);
    }
}
