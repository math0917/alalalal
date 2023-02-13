
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] maze;
    static int treasureCount;
    static Queue<int[]> queue;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Map<Integer, Integer> treasureMap;
    static boolean[][][][] visited;
    public static void main(String[] args) throws Exception {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                break;
            }
            maze = new int[n][m];
            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                for (int j = 0; j < m; j++) {
                    switch (line.charAt(j)) {
                        case 'N':
                            maze[i][j] = 0;
                            break;
                        case 'E':
                            maze[i][j] = 1;
                            break;
                        case 'S':
                            maze[i][j] = 2;
                            break;
                        case 'W':
                            maze[i][j] = 3;
                            break;
                    }
                }
            }
            treasureCount = Integer.parseInt(br.readLine());
            visited = new boolean[n][m][1 << treasureCount][4];
            treasureMap = new HashMap<>();
            for (int i = 0; i < treasureCount; i++) {
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken()) - 1;
                int col = Integer.parseInt(st.nextToken()) - 1;
//            System.out.println(row * m + col);
                treasureMap.put(row * m + col, i);
            }
            queue = new ArrayDeque<>();

            queue.add(new int[]{0, 0, 0, 0});
            visited[0][0][0][maze[0][0]] = true;
            while (!queue.isEmpty()) {
                int[] thisTurn = queue.poll();
                int thisRow = thisTurn[0];
                int thisCol = thisTurn[1];
                int thisLength = thisTurn[2];
                int thisBit = thisTurn[3];

//            System.out.println(thisRow + " "+ thisCol + " "+  thisLength+ " " + thisBit + " 으로부터 ( 현재 방향은 "+ (maze[thisRow][thisCol] + thisLength) % 4 );
                if (thisRow == n - 1 && thisCol == m - 1) {

                    if (thisBit == ((1 << treasureCount) - 1)) {
                        System.out.println(thisLength);
                        break;
                    }
                }
                if (!visited[thisRow][thisCol][thisBit][(maze[thisRow][thisCol] + thisLength + 1) % 4]) {
                    visited[thisRow][thisCol][thisBit][(maze[thisRow][thisCol] +  thisLength+ 1) % 4] = true;
//                System.out.println(thisRow + " "+ thisCol + " "+  (thisLength+ 1) + " " + thisBit + " 추가");

                    queue.add(new int[]{thisRow, thisCol, thisLength + 1, thisBit});
                }
                int direction = (maze[thisRow][thisCol] + thisLength) % 4;
//            System.out.println(direction);
                int row = thisRow + dx[direction];
                int col = thisCol + dy[direction];
                int bit = thisBit;
                if (0 <= row && row < n && 0 <= col && col < m) {
                    if (treasureMap.containsKey(row * m + col)) {

                        bit |= (1 << treasureMap.get(row * m + col));
                    }
//                System.out.println(bit);
                    if (!visited[row][col][bit][(maze[row][col] + thisLength + 1) % 4]) {
                        visited[row][col][bit][(maze[row][col] + thisLength + 1) % 4] = true;
                        queue.add(new int[]{row, col, thisLength + 1, bit});
//                    System.out.println(row + " "+ col + " "+  (thisLength + 1) + " " + bit + " 추가");

                    }
                }


            }
        }


    }
}
