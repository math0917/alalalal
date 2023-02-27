import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int testCase;
    static int n, m;
    static StringBuilder sb;
    static int[] parent;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            sb = new StringBuilder();
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            parent = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                parent[i] = i;
            }
            for (int k = 0; k < m; k++) {
                st = new StringTokenizer(br.readLine());
                int proc = Integer.parseInt(st.nextToken());
                switch (proc) {
                    case 0:
                        int from = Integer.parseInt(st.nextToken());
                        int to = Integer.parseInt(st.nextToken());
                        union(from, to);
                        break;
                    case 1:
                        int num1 = Integer.parseInt(st.nextToken());
                        int num2 = Integer.parseInt(st.nextToken());
                        if (find(num1) == find(num2)) {
                            sb.append(1);
                        } else {
                            sb.append(0);
                        }
                }
            }
            System.out.println("#"+t+ " "+sb.toString());
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
            parent[Math.max(par2, par1)] = Math.min(par2, par1);
        }
    }
}