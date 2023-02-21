import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static char[][] picture;
    static boolean[][] visitedWeakness;
    static boolean[][] visited;
    static Queue<int[]> queue;
    static int result = 0;
    static int resultWeakness = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        picture = new char[n][n];
        visited = new boolean[n][n];
        visitedWeakness = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                picture[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    queue = new ArrayDeque<>();
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    result += 1;
                    while (!queue.isEmpty()) {
                        int[] thisTurn = queue.poll();
                        int thisRow = thisTurn[0];
                        int thisCol = thisTurn[1];
                        for (int k = 0; k < 4; k++) {
                            int row = thisRow + dx[k];
                            int col = thisCol + dy[k];
                            if (0 <= row && row < n && 0 <= col && col < n) {
                                if (picture[thisRow][thisCol] == picture[row][col] && !visited[row][col]) {
                                    visited[row][col] = true;
                                    queue.add(new int[]{row, col});
                                }
                            }
                        }
                    }
                }
                if (!visitedWeakness[i][j]) {
                    queue = new ArrayDeque<>();
                    queue.add(new int[]{i, j});
                    visitedWeakness[i][j] = true;
                    resultWeakness += 1;
                    while (!queue.isEmpty()) {
                        int[] thisTurn = queue.poll();
                        int thisRow = thisTurn[0];
                        int thisCol = thisTurn[1];
                        for (int k = 0; k < 4; k++) {
                            int row = thisRow + dx[k];
                            int col = thisCol + dy[k];
                            if (0 <= row && row < n && 0 <= col && col < n) {
                                switch (picture[thisRow][thisCol]) {
                                    case 'B':
                                        if (picture[row][col] == 'B' && !visitedWeakness[row][col]) {
                                            visitedWeakness[row][col] = true;
                                            queue.add(new int[]{row, col});
                                        }
                                        break;
                                    case 'R': case 'G':
                                        if (picture[row][col] != 'B' && !visitedWeakness[row][col]) {
                                            visitedWeakness[row][col] = true;
                                            queue.add(new int[]{row, col});
                                        }
                                        break;
                                }

                            }
                        }
                    }
                }
            }
        }
        System.out.println(result + " " + resultWeakness);
    }
}