import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][][][] visited;
    static int startRow, startCol;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Map<Integer,Integer> loc;
    static Queue<int[]> queue;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        loc = new HashMap<>();
        int c = 0;

        visited = new boolean[n][m][1 << 2][4];
        queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                    map[i][j] = '.';
                    for (int k = 0; k < 4; k++) {
                        visited[i][j][0][k] = true;
                        queue.add(new int[]{i, j, k, 0, 0});
                    }
                } else if (map[i][j] == 'C') {
                    loc.put(i * m + j, c++);
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] thisTurn = queue.poll();
            int thisRow = thisTurn[0] + dx[thisTurn[2]];
            int thisCol = thisTurn[1] + dy[thisTurn[2]];
            int thisDir = thisTurn[2];
            int thisLen = thisTurn[3];
            int visitBit = thisTurn[4];

            if (0 <= thisRow && thisRow < n && 0 <= thisCol && thisCol < m) {
                if (map[thisRow][thisCol] == '.') {
                    for (int k = 1; k < 4; k++) {
                        if (!visited[thisRow][thisCol][visitBit][(thisDir + k) % 4]) {
                            visited[thisRow][thisCol][visitBit][(thisDir + k) % 4] = true;
                            queue.add(new int[]{thisRow, thisCol, (thisDir + k) % 4, thisLen + 1, visitBit});
                        }
                    }
                } else if (map[thisRow][thisCol] == 'C' && (visitBit & (1 << loc.get(thisRow *m + thisCol)) )== 0) {
                    for (int k = 1; k < 4; k++) {
                        if (!visited[thisRow][thisCol][visitBit | 1 << loc.get(thisRow *m + thisCol)][(thisDir + k) % 4]) {
                            visited[thisRow][thisCol][visitBit | 1 << loc.get(thisRow *m + thisCol)][(thisDir + k) % 4] = true;
                            if ((visitBit | 1 << loc.get(thisRow * m + thisCol)) == 3) {
                                result = Math.min(result, thisLen + 1);
                                continue;
                            }
                            queue.add(new int[]{thisRow, thisCol, (thisDir + k) % 4, thisLen + 1, visitBit | 1 << loc.get(thisRow *m + thisCol)});
                        }
                    }
                }
            }

        }
        System.out.println(result != Integer.MAX_VALUE? result : -1);

    }
}