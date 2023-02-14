

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int subin, dongsang;
    static Set<Integer> visited;
    static Deque<int[]> queue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        subin = Integer.parseInt(st.nextToken());
        dongsang = Integer.parseInt(st.nextToken());

        visited = new HashSet<>();
        queue = new ArrayDeque<>();

        queue.add(new int[]{subin, 0});

        while (!queue.isEmpty()) {
            int[] thisTurn = queue.pollFirst();
            if (dongsang == thisTurn[0]) {
                System.out.println(thisTurn[1]);
                break;
            }
            if (!visited.contains(thisTurn[0] * 2) && thisTurn[0] * 2 < 200000) {
                visited.add(thisTurn[0] * 2);
                queue.addLast(new int[]{thisTurn[0] * 2, thisTurn[1] + 1});
            }
            if (!visited.contains(thisTurn[0] + 1) && thisTurn[0] + 1 < 100000) {
                visited.add(thisTurn[0] + 1);
                queue.addLast(new int[]{thisTurn[0] + 1, thisTurn[1] + 1});
            }
            if (!visited.contains(thisTurn[0] - 1) && thisTurn[0] - 1 >= 0) {
                visited.add(thisTurn[0] - 1);
                queue.addLast(new int[]{thisTurn[0] - 1, thisTurn[1] + 1});
            }
        }



    }
}
