

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static Map<Character, String> map;
    static Queue<long[]> queue;
    static int num1, num2;
    static StringBuilder sb = new StringBuilder();
    static Set<Integer> visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());
            map = new HashMap<>();
            map.put('1', "D");
            map.put('2', "S");
            map.put('3', "L");
            map.put('4', "R");
            visited = new HashSet<>();
            queue = new ArrayDeque<>();
            queue.add(new long[]{num1, 0});
            visited.add(num1);
            while (!queue.isEmpty()) {
                long[] thisTurn = queue.poll();
                long thisTurnNum = thisTurn[0], everSince = thisTurn[1];
                if (thisTurnNum == num2) {
                    String s = String.valueOf(everSince);

                    for (int j = 0; j < s.length(); j++) {
                        sb.append(map.get(s.charAt(j)));
                    }
                    sb.append("\n");
                    break;
                }
                int dP = dProcedure((int)thisTurnNum);
                if (!visited.contains(dP)) {
                    queue.add(new long[] {dP, everSince * 10 + 1});
                    visited.add(dP);
                }


                int sP = sProcedure((int)thisTurnNum);
                if (!visited.contains(sP)) {
                    queue.add(new long[] {sP, everSince * 10 + 2});
                    visited.add(sP);
                }

                int lP = lProcedure((int)thisTurnNum);
                if (!visited.contains(lP)) {
                    queue.add(new long[] {lP, everSince * 10 + 3});
                    visited.add(lP);
                }

                int rP = rProcedure((int)thisTurnNum);
                if (!visited.contains(rP)) {
                    queue.add(new long[] {rP, everSince * 10 + 4});
                    visited.add(rP);
                }


            }
        }


        System.out.println(sb.toString());
    }

    private static int dProcedure(int num) {
        return (num * 2) % 10000;
    }

    private static int sProcedure(int num) {
        return num > 0 ? num - 1 : 9999;
    }

    private static int lProcedure(int num) {
        return (num % 1000) * 10 + num / 1000;
    }

    private static int rProcedure(int num) {
        return (num % 10) * 1000 + num / 10;
    }
}
