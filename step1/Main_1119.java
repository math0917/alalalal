
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1119{
    static int n;
    static boolean[][] connect;
    static boolean[] visited;
    static int leftOver;
    static int ground;
    static int count;
    static int groundCount;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        connect = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                connect[i][j] = line.charAt(j) != 'N';
            }
        }
        visited = new boolean[n];
        leftOver = 0;
        ground = 0;


        if (n == 1) {
            System.out.println(0);
            System.exit(0);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                ground += 1;
                count = 0;
                groundCount = 1;
                dfs(i);
                leftOver += count / 2 - (groundCount - 1);
                if (groundCount == 1) {
                    System.out.println(-1);
                    System.exit(0);
                }
            }
        }

        if (leftOver >= ground - 1) {
            System.out.println(ground - 1);
        } else {
            System.out.println(-1);
        }

    }

    private static void dfs(int num) {
        for (int i = 0; i < n; i++) {
            if (connect[num][i]) {
                if (!visited[i]) {
                    visited[i] = true;
                    groundCount += 1;
                    dfs(i);
                }
                count += 1;
            }
        }
    }
}
