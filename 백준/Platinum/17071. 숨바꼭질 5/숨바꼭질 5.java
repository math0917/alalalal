

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int subin, dongsang;
    static int time;
    static Queue<int[]> queue;
    static Set<Integer> visitedEven;
    static Set<Integer> visitedOdd;
    static Map<Integer, int[]> map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        subin = Integer.parseInt(st.nextToken());
        dongsang = Integer.parseInt(st.nextToken());



        queue = new ArrayDeque<>();
        queue.add(new int[]{subin, 0});
        visitedEven = new HashSet<>();
        visitedOdd = new HashSet<>();
        map = new HashMap<>();
        visitedEven.add(subin);


        map.put(subin, new int[]{0, 0});
        int walk = 1;

        time = 0;
        while (!queue.isEmpty() && dongsang < 500000) {
            int[] thisTurn = queue.poll();
//            System.out.println(Arrays.toString(thisTurn));
//            System.out.println(time);
            if (thisTurn[1] != time) {
                time = thisTurn[1];
                dongsang += walk++;
            }
            if (time % 2 == 0) {
                if (visitedEven.contains(dongsang)) {
                    System.out.println(time);
                    return;
                }
            } else{
                if (visitedOdd.contains(dongsang)) {
                    System.out.println(time);
                    return;
                }
            }


            visit(thisTurn[0] * 2, thisTurn);
            visit(thisTurn[0] - 1, thisTurn);
            visit(thisTurn[0] + 1, thisTurn);
        }
        System.out.println(-1);

    }

    private static void visit(int value, int[] thisTurn) {
        if (value <= 500000 && value >= 0) {
            if (((thisTurn[1] + 1) % 2) == 1) {

                if (!visitedOdd.contains(value)) {
                    visitedOdd.add(value);
                    queue.add(new int[]{value, thisTurn[1] + 1});
                }
            }
            else if (((thisTurn[1] + 1) % 2) == 0) {
                if (!visitedEven.contains(value)) {
                    visitedEven.add(value);
                    queue.add(new int[]{value, thisTurn[1] + 1});
                }

            }
        }
    }

}

