
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] proc;
    static List<int[]> wind;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        proc = new int[m][2];
        wind = new ArrayList<>();
        wind.add(new int[]{n - 1, 0});
        wind.add(new int[]{n - 1, 1});
        wind.add(new int[]{n - 2, 0});
        wind.add(new int[]{n - 2, 1});
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            start(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Arrays.stream(map[i]).sum();
        }
        System.out.println(sum);


    }

    private static void start(int direction, int far) {
        for (int[] win : wind) {
            win[0] = (win[0] + dx[direction] * far + 50 * n) % n;
            win[1] = (win[1] + dy[direction] * far + 50 * n) % n;
            map[win[0]][win[1]] += 1;
        }
        Set<Integer> visited = new HashSet<>();
        for (int[] win : wind) {
            int count = 0;
            for (int i = 1; i < 8; i += 2) {
                int row = win[0] + dx[i];
                int col = win[1] + dy[i];
                if (0 <= row && row < n && 0 <= col && col < n) {
                    if (map[row][col] > 0) {
                        count += 1;
                    }
                }
            }
            map[win[0]][win[1]] += count;
            visited.add(win[0] * n + win[1]);
        }
        wind.clear();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] >= 2) {
                    if (!visited.contains(i * n + j)) {
                        map[i][j] -= 2;
                        wind.add(new int[]{i, j});
                    }
                }
            }
        }

    }
}
