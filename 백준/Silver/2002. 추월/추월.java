
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static List<String> pq;
    static int result  = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        pq = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pq.add(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            String next = br.readLine();

            if (!next.equals(pq.get(0))) {
                result += 1;
            }
            pq.remove(next);

        }
        System.out.println(result);
    }
}

class Node{
    String text;

    public Node(String text) {
        this.text = text;
    }


}