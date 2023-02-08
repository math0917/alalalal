


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int sujin, dongsang;
    static Queue<Node> queue;
    static int result = -1;
    static int length = -1;
    static Set<Integer> visited;
    static List<Integer> thisTurnVisited;
    static int resultCount = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        sujin = Integer.parseInt(st.nextToken());
        dongsang = Integer.parseInt(st.nextToken());
        visited = new HashSet<>();
        thisTurnVisited = new LinkedList<>();
        thisTurnVisited.add(sujin);
        queue = new ArrayDeque<>();
        queue.add(new Node(sujin, sujin, 0));
        while (!queue.isEmpty()) {
            Node thisTurn = queue.poll();
            if (length != thisTurn.weight) {
                visited.addAll(thisTurnVisited);
                length ++;
                thisTurnVisited = new LinkedList<>();
                thisTurnVisited.add(thisTurn.to);
            }
//            System.out.println(thisTurn.to);
            if (thisTurn.to == dongsang) {
                result = thisTurn.weight;
                resultCount += 1;
                continue;
            }if (thisTurn.weight == result) {
                continue;
            }
            
            if (thisTurn.to * 2 < 200001) {
                if (!visited.contains(thisTurn.to * 2)) {
                    queue.add(new Node(thisTurn.to, thisTurn.to * 2, thisTurn.weight + 1));
                    thisTurnVisited.add(thisTurn.to * 2);
                }
            }
            if (thisTurn.to + 1 < 200001) {
                if (!visited.contains(thisTurn.to + 1)) {
                    queue.add(new Node(thisTurn.to, thisTurn.to + 1, thisTurn.weight + 1));
                    thisTurnVisited.add(thisTurn.to + 1);
                }
            }
            if (thisTurn.to - 1 >= 0) {
                if (!visited.contains(thisTurn.to - 1)) {
                    queue.add(new Node(thisTurn.to, thisTurn.to - 1, thisTurn.weight + 1));
                    thisTurnVisited.add(thisTurn.to - 1);
                }
            }
            




        }
        System.out.println(result);
        System.out.println(resultCount);



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

