import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] water;
    static int result = 0;
    static Queue<int[]> queue = new ArrayDeque<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine()
        );
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        water = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                water[i][j] = line.charAt(j) - '0';
            }
        }
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            if (i == 0 || i == n - 1) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;
                        dfs(i, j);
                    }
                }
            } else {
                if (!visited[i][0]) {
                    visited[i][0] = true;
                    dfs(i, 0);
                }
                if (!visited[i][m - 1]) {
                    visited[i][m - 1] = true;
                    dfs(i, m - 1);
                }

            }
        }
        for (int num = 9; num >= 1; num--) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (water[i][j] == num && !visited[i][j]) {
//                        System.out.println(num);
                        queue.add(new int[]{i, j});
                        Set<Integer> thisSet = new HashSet<>();
                        thisSet.add(m * i + j);
                        int minValue = Integer.MAX_VALUE;
                        while (!queue.isEmpty()) {
                            int[] poll = queue.poll();
//                            System.out.println(poll[0] + " " + poll[1]);
                            for (int k = 0; k < 4; k++) {
                                int row = poll[0] + dx[k];
                                int col = poll[1] + dy[k];
                                if (visited[row][col]) {
                                    minValue = Math.min(minValue, water[row][col]);
                                } else {
                                    if (!thisSet.contains(m * row + col)) {

                                        queue.add(new int[]{row, col});
                                        thisSet.add(m * row + col);
                                    }

                                }
                            }
                        }
                        if (water[i][j] > minValue) {
                            for (int a : thisSet) {
                                int row = a / m;
                                int col = a % m;
                                if (water[row][col] == num) {
                                    visited[row][col] = true;
                                }
                            }
                        } else {
                            for (int a : thisSet) {
                                int row = a / m;
                                int col = a % m;
                                if (water[row][col] == num) {
                                    visited[row][col] = true;
                                }
                                result += minValue - water[row][col];

                                water[row][col] += minValue - water[row][col];

                            }
                        }

//                        for (int row = 0; row < n; row++) {
//                            for (int col = 0; col < m; col++) {
//                                System.out.print(water[row][col] + " ");
//                            }
//                            System.out.println();
//                        }
//                        System.out.println();
                    }

                }

            }
        }


        System.out.println(result);
    }

    private static void dfs(int thisRow, int thisCol) {
        for (int k = 0; k < 4; k++) {
            int row = thisRow + dx[k];
            int col = thisCol + dy[k];
            if (0 <= row && row < n && 0 <= col && col < m) {
                if (!visited[row][col] && water[thisRow][thisCol] == water[row][col]) {
                    visited[row][col] = true;
                    dfs(row, col);
                }
            }
        }
    }


}