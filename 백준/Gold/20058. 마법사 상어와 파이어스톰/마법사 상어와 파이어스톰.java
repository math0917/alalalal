
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, q;
    static int[][] ice;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int resultArea = 0;
    static int iceLength = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        ice = new int[(int) Math.pow(2, n)][(int) Math.pow(2, n)];

        for (int i = 0; i < ice[0].length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < ice[0].length; j++) {
                ice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[ice[0].length][ice[0].length];
        st = new StringTokenizer(br.readLine());
        for (int t = 0; t < q; t++) {
            proc(Integer.parseInt(st.nextToken()));
//            for (int row = 0; row < ice[0].length; row++) {
//                for (int col = 0; col < ice[0].length; col++) {
//                    System.out.print(ice[row][col] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
            Queue<int[]> queue = new ArrayDeque<>();
            for (int i = 0; i < ice[0].length; i++) {
                for (int j = 0; j < ice[0].length; j++) {
                    if (ice[i][j] > 0) {
                        int count = 0;
                        for (int k = 0; k < 4; k++) {
                            int row = i + dx[k];
                            int col = j + dy[k];
                            if (0 <= row && row < ice[0].length && 0 <= col && col < ice[0].length) {
                                if (ice[row][col] > 0) {
                                    count += 1;
                                }
                            }
                        }
                        if (count < 3) {
                            queue.add(new int[]{i, j});
                        }
                    }
                }
            }
            while (!queue.isEmpty()) {
                int[] thisTurn = queue.poll();
                ice[thisTurn[0]][thisTurn[1]] -= 1;
            }

        }
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < ice[0].length; i++) {
            for (int j = 0; j < ice[0].length; j++) {
                if (ice[i][j] != 0 && !visited[i][j]) {
                    iceLength += ice[i][j];
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    int areaCount = 1;
                    while (!queue.isEmpty()) {
                        int[] thisTurn = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int row = thisTurn[0] + dx[k];
                            int col = thisTurn[1] + dy[k];
                            if (0 <= row && row < ice[0].length && 0 <= col && col < ice[0].length) {
                                if (ice[row][col] > 0 && !visited[row][col]) {
                                    visited[row][col] = true;
                                    iceLength += ice[row][col];
                                    areaCount += 1;
                                    queue.add(new int[]{row, col});
                                }
                            }
                        }
                    }
                    resultArea = Math.max(areaCount, resultArea);
                }
            }
        }

        System.out.println(iceLength);
        System.out.println(resultArea);
    }

    private static void proc(int procNum) {
        if (procNum == 0) {
            return;
        }
        int gap = (1 << procNum);

        for (int i = 0; i < ice[0].length; i += gap) {
            for (int j = 0; j < ice[0].length; j += gap) {
                rotate(i, j, gap);
            }
        }
    }

    private static void rotate(int thisRow, int thisCol, int rotateGap) {
        Queue<int[]> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[rotateGap][rotateGap];
        queue.add(new int[]{thisRow, thisCol, 0});
        Queue<Integer> value = new ArrayDeque<>();
        value.add(ice[thisRow][thisCol]);
        visited[0][0] = true;
        while (!queue.isEmpty()) {


            int[] thisTurn = queue.poll();
//            System.out.println(thisTurn[0] + " " + thisTurn[1] + " "+ thisTurn[2]);
            int thRow = thisTurn[0];
            int thCol = thisTurn[1];
            int thisDir = thisTurn[2];
            int row = thRow + dx[thisDir];
            int col = thCol + dy[thisDir];
            int booleanRowIdx = row - thisRow;
            int booleanColIdx = col - thisCol;

            if (0 <= booleanRowIdx && booleanRowIdx < rotateGap && 0 <= booleanColIdx && booleanColIdx < rotateGap) {
                if (visited[booleanRowIdx][booleanColIdx]) {
                    queue.add(new int[]{thRow, thCol, (thisDir + 1) % 4});
                } else {
                    visited[booleanRowIdx][booleanColIdx] = true;
                    value.add(ice[row][col]);
                    queue.add(new int[]{row, col, thisDir});
                }
            } else{
                queue.add(new int[]{thRow, thCol, (thisDir + 1) % 4});
            }
            if (value.size() == (int) Math.pow(rotateGap,2)) {
//                System.out.println("hi");
                queue.clear();
                queue.add(new int[]{thisRow, thisCol + rotateGap - 1, 1});
                ice[thisRow][thisCol+ rotateGap-1] = value.poll();
                visited = new boolean[rotateGap][rotateGap];
                visited[0][rotateGap-1] = true;
                while (!queue.isEmpty() && value.size() > 0) {
                    thisTurn = queue.poll();
                    thRow = thisTurn[0];
                    thCol = thisTurn[1];
                    thisDir = thisTurn[2];
                    row = thRow + dx[thisDir];
                    col = thCol + dy[thisDir];
                    booleanRowIdx = row - thisRow;
                    booleanColIdx = col - thisCol;
                    if (0 <= booleanRowIdx && booleanRowIdx < rotateGap && 0 <= booleanColIdx && booleanColIdx < rotateGap) {
                        if (visited[booleanRowIdx][booleanColIdx]) {
                            queue.add(new int[]{thRow, thCol, (thisDir + 1) % 4});
                        } else {
                            visited[booleanRowIdx][booleanColIdx] = true;

                            ice[row][col] = value.poll();
                            queue.add(new int[]{row, col, thisDir});
                        }
                    } else {
                        queue.add(new int[]{thRow, thCol, (thisDir + 1) % 4});
                    }
                }
                queue.clear();
            }
        }

    }
}
