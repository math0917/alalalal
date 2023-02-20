
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] shark;
    static boolean[][] visited;
    static Map<Node, Integer> map;
    static int score = 0;
    static int[] dx = { 1, 0, -1,0};
    static int[] dy = {0, -1, 0,1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        shark = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                shark[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean flag = true;
        while (flag) {
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.printf("%2d ",shark[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println();
            visited = new boolean[n][n];
            map = new TreeMap<>();
            Queue<int[]> queue = new ArrayDeque<>();
            flag = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (shark[i][j] > 0 && !visited[i][j]) {

                        int value = shark[i][j];
                        int length = 1;
                        int rainbow = 0;
                        queue.add(new int[]{i, j});

                        visited[i][j] = true;
                        boolean[][] zeroVisited = new boolean[n][n];
                        while (!queue.isEmpty()) {
                            int[] thisTurn = queue.poll();
                            int thisRow = thisTurn[0];
                            int thisCol = thisTurn[1];
                            for (int k = 0; k < 4; k++) {
                                int row = thisRow + dx[k];
                                int col = thisCol + dy[k];
                                if (0 <= row && row < n && 0 <= col && col < n) {
                                    if (shark[row][col] == 0 && !zeroVisited[row][col]) {
                                        zeroVisited[row][col] = true;
                                        flag = true;
                                        queue.add(new int[]{row, col});
                                        rainbow += 1;
                                        length += 1;
                                    } else if (shark[row][col] == value && !visited[row][col]) {
                                        queue.add(new int[]{row, col});
                                        flag = true;
                                        length += 1;
                                        visited[row][col] = true;
                                    }
                                }
                            }
                        }
                        map.put(new Node(i,j, rainbow), length);
                    }
                }
            }
            if (!flag) {
                break;
            }
            int maxValue = 0;
            Node result = null;
            for (Node values : map.keySet()) {
//                System.out.println(values.row + " "+ values.col + " " + map.get(values));
                if (map.get(values) > maxValue) {
                    maxValue = map.get(values);
                    result = values;
                } else if (map.get(values) == maxValue) {
                    result = values;
                }
            }
//            System.out.println(maxValue);
            score += maxValue * maxValue;
            queue.add(new int[]{result.row, result.col});
            int sharkValue = shark[result.row][result.col];
            shark[result.row][result.col] = -2;
            while (!queue.isEmpty()) {
                int[] thisTurn = queue.poll();
                int thisRow = thisTurn[0];
                int thisCol = thisTurn[1];
                for (int k = 0; k < 4; k++) {
                    int row = thisRow + dx[k];
                    int col = thisCol + dy[k];
                    if (0 <= row && row < n && 0 <= col && col < n) {
                        if (sharkValue == shark[row][col] || shark[row][col] == 0) {
                            queue.add(new int[]{row, col});
                            shark[row][col] = -2;
                        }
                    }
                }
            }
//            System.out.println("삭제");
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.printf("%2d ",shark[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println();
            gravity();
//            System.out.println("중력");
//
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.printf("%2d ",shark[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println();
            rotate90();
//            System.out.println("90도");
//
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.printf("%2d ",shark[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println();
            gravity();
//            System.out.println("중력");
//
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.printf("%2d ",shark[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println();


        }
        System.out.println(score);
    }

    private static void rotate90() {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new ArrayDeque<>();
        Queue<Integer> values = new ArrayDeque<>();
        queue.add(new int[]{0, n - 1,0});
        values.add(shark[0][n - 1]);
        visited[0][n-1] = true;
        while (values.size() < n * n) {
            int[] poll = queue.poll();
            int thisRow = poll[0];
            int thisCol = poll[1];
            int thisDir = poll[2];
//            System.out.println(thisRow + " "+ thisCol + " " + thisDir+ " "+ values.size());
            int row = thisRow + dx[thisDir];
            int col = thisCol + dy[thisDir];
            if (0 <= row && row < n && 0 <= col && col < n) {
                if (visited[row][col]) {
                    queue.add(new int[]{thisRow, thisCol, (thisDir + 1) % 4});
                } else {
                    visited[row][col] = true;
                    queue.add(new int[]{row, col, thisDir});
                    values.add(shark[row][col]);
                }
            } else {
                queue.add(new int[]{thisRow, thisCol, (thisDir + 1) % 4});
            }
        }

        visited = new boolean[n][n];
        visited[0][0] = true;
        queue.clear();
        shark[0][0] = values.poll();
        queue.add(new int[]{0, 0, 3});
        while (!queue.isEmpty() && !values.isEmpty()) {
            int[] poll = queue.poll();
            int thisRow = poll[0];
            int thisCol = poll[1];
            int thisDir = poll[2];
            int row = thisRow + dx[thisDir];
            int col = thisCol + dy[thisDir];
            if (0 <= row && row < n && 0 <= col && col < n) {
                if (visited[row][col]) {
                    queue.add(new int[]{thisRow, thisCol, (thisDir + 1) % 4});
                } else {
                    shark[row][col] = values.poll();
                    visited[row][col] = true;
                    queue.add(new int[]{row, col, thisDir});
                }
            } else {
                queue.add(new int[]{thisRow, thisCol, (thisDir + 1) % 4});
            }
        }
    }

    private static void gravity() {
        for (int j = 0; j < n; j++) {
            int i = 0;
            Stack<Integer> values = new Stack<>();

            while (i < n) {
                while (i < n && shark[i][j] != -1) {
                    if (shark[i][j] >= 0) {

                        values.add(shark[i][j]);
                        shark[i][j] = -2;
                    }
                    i ++;
                }
                int idx = i;
                while (!values.isEmpty()) {
                    shark[--idx][j] = values.pop();
                }
                i++;
            }

        }
    }
}

class Node implements Comparable<Node> {
    int row;
    int rainbow;

    public Node(int row, int col,int rainbow) {
        this.row = row;
        this.col = col;
        this.rainbow = rainbow;
    }

    int col;


    @Override
    public int compareTo(Node o) {
        return rainbow != o.rainbow ? Integer.compare(rainbow, o.rainbow) : row != o.row ? Integer.compare(row, o.row) : Integer.compare(col, o.col);
    }

}
