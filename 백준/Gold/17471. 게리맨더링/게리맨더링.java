import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] population;
    static ArrayList<Integer>[] graph;
    static int result = Integer.MAX_VALUE;
    static boolean[] visitedA;
    static boolean[] visitedB;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        population = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                int from = Integer.parseInt(st.nextToken()) - 1;
                graph[i].add(from);
            }
        }
        for (int i = 1; i < (int) Math.pow(2, n - 1); i++) {
            int countA = 0;
            int countB = 0;
            visitedA = new boolean[n];
            visitedB = new boolean[n];

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    countA += 1;
                } else {
                    countB += 1;
                }
            }

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0){
                    visitedA[j] = true;
                    dfsA(j,i);
                    break;
                }

            }

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 0){
                    visitedB[j] = true;

                    dfsB(j,i);
                    break;
                }
            }
            int a = 0;
            int b = 0;
            for (int idx = 0; idx < n; idx++) {
                if (visitedA[idx]) {
                    a += 1;
                }
                if (visitedB[idx]) {
                    b += 1;
                }
            }
            int teamA = 0;
            int teamB = 0;
            if (a == countA && b == countB) {
                for (int idx = 0; idx < n; idx++) {
                    if ((i & (1 << idx)) != 0) {
                        teamA += population[idx];

                    } else {
                        teamB += population[idx];
                    }
                }
                result = Math.min(result, Math.abs(teamA - teamB));
            }




        }
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);

    }

    private static void dfsB(int j, int i) {
        for (int next : graph[j]) {
            if ((i & (1 << next)) == 0 && !visitedB[next]) {
                visitedB[next] = true;
                dfsB(next, i);
            }
        }
    }
    private static void dfsA(int j, int i) {
        for (int next : graph[j]) {
            if ((i & (1 << next)) != 0 && !visitedA[next]) {
                visitedA[next] = true;
                dfsA(next, i);
            }
        }
    }
}