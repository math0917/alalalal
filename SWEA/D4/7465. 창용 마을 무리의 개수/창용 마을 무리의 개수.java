import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int testCase;
    static int n, m;
    static int[] parent;
    static int moori;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
            moori = n;
            for (int k = 0; k < m; k++) {
                st = new StringTokenizer(br.readLine());
                union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            System.out.println("#"+ t + " "+ moori);
        }
    }
    private static int find(int num) {
        if (parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }
    private static void union(int from, int to) {
        int par1 = find(from);
        int par2 = find(to);
        if (par1 != par2) {
            moori--;
            parent[Math.max(par2, par1)] = Math.min(par2, par1);
        }
    }
}