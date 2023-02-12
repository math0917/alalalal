

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] classRoom;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int result = 0;
    static int somCount = 0;
    static int connectCount = 0;
    static Queue<Integer> queue;
    static Set<Integer> queueVisited;
    static int[] visited = new int[5];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        classRoom = new char[5][5];

        for (int i = 0; i < 5; i++) {
            String thisLine = br.readLine();
            for (int j = 0; j < 5; j++) {
                classRoom[i][j] = thisLine.charAt(j);
            }
        }
        start();
        System.out.println(result);
    }

    private static void start() {
        for (int i = 0; i < 25; i++) {
            int row = i / 5;
            int col = i % 5;
            visited[row] |= (1 << col);
            visit(1, i + 1, i);
            visited[row] ^= (1 << col);
        }
    }

    private static void visit(int index, int start, int startIndex) {
        if (index == 7) {

            queue = new ArrayDeque<>();
            queueVisited = new HashSet<>();
            queue.add(startIndex);
            queueVisited.add(startIndex);
            somCount = ((classRoom[startIndex / 5][startIndex % 5]) == 'S' ? 1 : 0);
            connectCount = 1;
            while (!queue.isEmpty()) {
                Integer thisTurn = queue.poll();
                int thisRow = thisTurn / 5;
                int thisCol = thisTurn % 5;

                for (int k = 0; k < 4; k++) {
                    int row = thisRow + dx[k];
                    int col = thisCol + dy[k];
                    if (canGo(row, col) && (visited[row] & (1 << col)) != 0 && !queueVisited.contains(row * 5 + col)) {
                        queue.add(row * 5 + col);
                        queueVisited.add(row * 5 + col);
                        somCount += ((classRoom[row][col]) == 'S' ? 1 : 0);
                        connectCount += 1;
                    }
                }

            }
            if (connectCount == 7 && somCount >= 4) {
//                System.out.println(startIndex);
//                System.out.println(Arrays.toString(visited));
                result += 1;
            }
            return;

        }
        for (int i = start; i < 25; i++) {
            int row = i / 5;
            int col = i % 5;
            visited[row] |= (1 << col);
            visit(index + 1, i + 1, startIndex);
            visited[row] ^= (1 << col);
        }
    }

    private static boolean canGo(int row, int col) {
        if (0 <= row && row < 5 && 0 <= col && col < 5) {
            return true;
        }
        return false;
    }

}

