import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static Queue<Integer>[] level;
    static int val = 1;
    static int[] result;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        level = new Queue[n+1];
        for (int i = 0; i < n+1; i++) {
            level[i] = new ArrayDeque<>();
        }
        level[0].add(0);
        result = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n; i++) {
            int val = Integer.parseInt(st.nextToken());
            if (level[val - 1].size() * 2 > level[val].size()) {
                level[val].add(i);
            } else {
                System.out.println(-1);
                System.exit(0);
            }
        }
        find(0);
        for (int a : result) {
            sb.append(a).append(' ');
        }
        System.out.println(sb);
    }

    private static void find(int index) {
        if (level[index].size() != 0) {
            find(index + 1);
            result[level[index].poll()] = val++;
            find(index + 1);
        } else {
            return;
        }

    }


}