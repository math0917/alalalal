
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;
    static int[] cost;

    static int[] parent;
    static ArrayList<int[]> pq;
    static int result = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cost = new int[n];
        parent = new int[n];
        pq = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(br.readLine());

            parent[i] = i;
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int co = Integer.parseInt(st.nextToken());

            pq.add(new int[]{from, to, co + cost[to] + co + cost[from]});
        }
        pq.sort(Comparator.comparing(a -> a[2]));

        for (int idx = 0; idx < pq.size() && n > 1; idx++) {
            int[] ints = pq.get(idx);
            int from = ints[0];
            int to = ints[1];
            int weight = ints[2];
            if (find(from) != find(to)) {
                union(from, to);
                result += + weight;
            }

        }
        System.out.println(result + Arrays.stream(cost).min().getAsInt());



    }

    private static int find(int num) {
        if (parent[num] != num) {
            return parent[num] = find(parent[num]);
        } else {
            return num;
        }
    }

    private static void union(int num1, int num2) {
        int par1 = find(num1);
        int par2 = find(num2);
        n--;
        parent[Math.max(par1, par2)] = Math.min(par1, par2);
    }
}
