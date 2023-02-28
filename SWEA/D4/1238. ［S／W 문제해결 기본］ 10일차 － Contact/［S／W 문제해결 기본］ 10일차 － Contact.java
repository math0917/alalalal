import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    static Map<Integer, ArrayList<Integer>> map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            map = new HashMap<>();
            int maxLength = -1;
            int maxValue = Integer.MIN_VALUE;
            Set<Integer> visited = new HashSet<>();
            int start = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                if (!map.containsKey(from)) {
                    map.put(from, new ArrayList<>());
                }
                map.get(from).add(to);
            }
            Queue<int[]> queue = new ArrayDeque<>();
            visited.add(start);
            queue.add(new int[]{start, 0});
            while (!queue.isEmpty()) {
                int[] thisTurn = queue.poll();
                int fr = thisTurn[0];
                int length = thisTurn[1];
//                System.out.println(fr + " " + length);
                if (length > maxLength) {
                    maxValue = fr;
                    maxLength = length;
                } else if (length == maxLength) {
                    maxValue = Math.max(fr, maxValue);
                }
                if (map.containsKey(fr)) {
                    for (int v : map.get(fr)) {
                        if (!visited.contains(v)) {
                            visited.add(v);
                            queue.add(new int[]{v, length + 1});
                        }

                    }
                }

            }
            System.out.println("#"+t+" "+maxValue);
        }
    }
}