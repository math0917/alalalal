

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

import static java.lang.System.in;

public class Main {
    static int n, m;
    static char[][] map;
    static int[] parent;
    static Robot[] loc;
    static PriorityQueue<int[]>pq;
    static Map<Integer,Integer> robotLoc;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int result = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][n];
        loc = new Robot[m + 1];
        int robotIndex = 0;
        parent = new int[m + 1];
        robotLoc = new HashMap<>();
        for (int i = 0; i < m + 1; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] != '0' && map[i][j] != '1') {
                    loc[robotIndex] = new Robot(robotIndex, i, j);
                    robotLoc.put(i * n + j, robotIndex);
                    robotIndex ++;
                }
            }
        }
        pq = new PriorityQueue<>(Comparator.comparing(a -> a[2]));
        for (int i = 0; i <= m; i++) {
            findLen(i);
        }
        while (!pq.isEmpty()) {
            int[] thisTurn = pq.poll();
            Robot fr = loc[thisTurn[0]];
            Robot to = loc[thisTurn[1]];
            if (find(fr.robotNum) != find(to.robotNum)) {
                result += thisTurn[2];
                union(fr.robotNum, to.robotNum);
            }
        }
        if (Arrays.stream(parent).filter(x -> x != 0).count() == 0) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }


    }

    private static void findLen(int i) {
        Robot robot = loc[i];
        boolean[][] visited = new boolean[n][n];
        visited[robot.row][robot.col] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{robot.row, robot.col, 0});
        while (!queue.isEmpty()) {
            int[] thisTurn = queue.poll();
            int thisRow = thisTurn[0];
            int thisCol = thisTurn[1];
            int thisLen = thisTurn[2];
            for (int k = 0; k < 4; k++) {
                int row = thisRow + dx[k];
                int col = thisCol + dy[k];
                if (0 <= row && row < n && 0 <= col && col < n) {
                    if (!visited[row][col]) {
                        if (map[row][col] == 'K' || map[row][col] == 'S') {
                            pq.add(new int[]{i, robotLoc.get(row * n + col), thisLen + 1});
                        }
                        if (map[row][col] != '1') {
                            visited[row][col] = true;
                            queue.add(new int[]{row, col, thisLen + 1});
                        }

                    }

                }
            }
        }
    }

    private static int find(int r1) {
        if (parent[r1] == r1) {
            return r1;
        }
        return parent[r1] = find(parent[r1]);
    }

    private static void union(int r1, int r2) {
        int par1 = find(r1);
        int par2 = find(r2);
        parent[Math.max(par1, par2)] = Math.min(par1, par2);
    }
}


class Robot implements Comparable<Robot>{
    int robotNum;
    int row;
    int col;

    public Robot(int robotNum, int row, int col) {
        this.robotNum = robotNum;
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Robot o) {
        return Integer.compare(robotNum, o.robotNum);
    }
}