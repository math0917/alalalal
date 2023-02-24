import java.util.*;

class Node {
    int x, y, cnt;

    public Node(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

class Solution {
    static char[][] miro;
    static int x_S, y_S, x_L, y_L, x_E, y_E;// 출발지점, 레버, 탈출지점 좌표 저장
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, -1, 0, 1 };
    static int row, col;
    static int answer;

    public static int solution(String[] maps) {

        // String[] maps = { "SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE" };
        row = maps.length;
        col = maps[0].length();

        miro = new char[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                char c = maps[i].charAt(j);
                miro[i][j] = c;

                if (c == 'S') {
                    x_S = i;
                    y_S = j;
                } else if (c == 'L') {
                    x_L = i;
                    y_L = j;
                } else if (c == 'E') {
                    x_E = i;
                    y_E = j;
                }

            }

        }
        int a = bfs(x_S, y_S, x_L, y_L);
        System.out.println(a);
        int b = bfs(x_L, y_L, x_E, y_E);
        System.out.println(b);

        if (a == -1 || b == -1) {
           return -1;
            // System.out.println(-1);
        }

        // System.out.println(a + b);
        return a + b;

    }

    private static boolean isIn(int x, int y) {
        if (x >= 0 && x < row && y >= 0 && y < col) {

            return true;
        }
        return false;
    }

    private static int bfs(int startX, int starty, int endX, int endY) {
        boolean[][] visited = new boolean[row][col];
        visited[startX][starty] = true;

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startX, starty, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int count = node.cnt;

            if (x == endX && y == endY) {
                return count;
            }

            for (int n = 0; n < 4; n++) {
                int nx = x + dx[n];
                int ny = y + dy[n];
                if (isIn(nx, ny) && miro[nx][ny] != 'X' && !visited[nx][ny]) {
                    
                    q.offer(new Node(nx, ny, count + 1));
                    visited[nx][ny] = true;
                    
                }
            }

        }

        return -1;

    }

}