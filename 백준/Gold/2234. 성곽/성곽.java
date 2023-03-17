import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] arr;
    static int[][][] parent;
    static int[][] count;
    static boolean[][] visited;
    static int area;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        parent = new int[n][m][2];
        count = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                parent[i][j][0] = i;
                parent[i][j][1] = j;
                count[i][j] = 1;
            }
        }
        area = n * m;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int[] thisTurn = queue.poll();
                        int thisRow = thisTurn[0];
                        int thisCol = thisTurn[1];
                        for (int k = 0; k < 4; k++) {
                            if ((arr[thisRow][thisCol] & (1 << k)) == 0) {
                                int row = thisRow + dx[k];
                                int col = thisCol + dy[k];
                                if (!visited[row][col]) {
                                    visited[row][col] = true;
                                    queue.add(new int[]{row, col});
                                    union(thisRow, thisCol, row, col);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(area);
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue,Arrays.stream(count[i]).max().getAsInt());
        }
        System.out.println(maxValue);
        maxValue = Integer.MIN_VALUE;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int[] poll = queue.poll();
                        int thisRow = poll[0];
                        int thisCol = poll[1];
                        for (int k = 0; k < 4; k++) {
                            int row = thisRow + dx[k];
                            int col = thisCol + dy[k];
                            if ((arr[thisRow][thisCol] & (1 << k)) == 0) {
                                if (!visited[row][col]) {
                                    queue.add(new int[]{row, col});
                                    visited[row][col] = true;
                                }
                            } else {
                                if (0 <= row && row < n && 0 <= col && col < m) {
                                    int[] ints = find(row, col);
                                    int[] ints1 = find(thisRow, thisCol);
                                    if (!(ints[0] == ints1[0] && ints[1] == ints1[1])) {
                                        maxValue = Math.max(count[ints[0]][ints[1]] + count[ints1[0]][ints1[1]], maxValue);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(maxValue);

    }



    private static void union(int thisRow, int thisCol, int row, int col) {
        int[] parent1 = find(thisRow, thisCol);
        int[] parent2 = find(row, col);
        int[] max = findMax(parent1, parent2);
        int[] min = findMin(parent1, parent2);
        area -= 1;
        parent[max[0]][max[1]][0] = min[0];
        parent[max[0]][max[1]][1] = min[1];
        count[min[0]][min[1]] += count[max[0]][max[1]];

    }

    private static int[] findMin(int[] parent1, int[] parent2) {
        if (parent1[0] < parent2[0]) {
            return parent1;
        } else if (parent1[0] > parent2[0]) {
            return parent2;
        } else {
            return parent1[1] > parent2[1] ? parent2 : parent1;
        }
    }

    private static int[] findMax(int[] parent1, int[] parent2) {
        if (parent1[0] < parent2[0]) {
            return parent2;
        } else if (parent1[0] > parent2[0]) {
            return parent1;
        } else {
            return parent1[1] > parent2[1] ? parent1 : parent2;
        }
    }

    private static int[] find(int thisRow, int thisCol) {
        if (parent[thisRow][thisCol][0] == thisRow && parent[thisRow][thisCol][1] == thisCol) {
            return new int[]{thisRow, thisCol};
        }
        int[] ints = find(parent[thisRow][thisCol][0], parent[thisRow][thisCol][1]);
        parent[thisRow][thisCol][0] = ints[0];
        parent[thisRow][thisCol][1] = ints[1];
        return ints;
    }
}