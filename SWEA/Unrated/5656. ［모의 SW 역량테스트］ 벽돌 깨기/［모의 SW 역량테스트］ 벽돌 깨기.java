import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int testCase;
    static int n, w, h;
    static int[][] game;
    static int[] proc;
    static int[][] gameCopy;
    static int result;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            result = Integer.MAX_VALUE;
            game = new int[h][w];
            proc = new int[n];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    game[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ball(0);
            System.out.println("#"+t + " " + result);
        }
    }

    private static void ball(int index) {
        if (index == n) {
            start();
            return;
        }
        for (int i = 0; i < w; i++) {
            proc[index] = i;
            ball(index + 1);
        }
    }

    private static void start() {
        gameCopy = new int[h][w];
        for (int i = 0; i < h; i++) {
            gameCopy[i] = game[i].clone();
        }
        for (int i = 0; i < n; i++) {
            ballDrop(proc[i]);
            gravity();
        }
        int cnt = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (gameCopy[i][j] != 0) {
                    cnt += 1;
                }
            }
        }
//        for (int row = 0; row < h; row++) {
//            for (int col = 0; col < w; col++) {
//                System.out.print(gameCopy[row][col] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println(Arrays.toString(proc));
//
//        System.out.println(cnt);
        result = Math.min(result, cnt);

    }

    private static void gravity() {
        for (int j = 0; j < w; j++) {
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < h; i++) {
                if (gameCopy[i][j] > 0) {
                    stack.add(gameCopy[i][j]);
                    gameCopy[i][j] = 0;

                }
            }

            for (int i = h - 1; i >= 0 && !stack.isEmpty(); i--) {
                gameCopy[i][j] = stack.pop();
            }
        }
    }

    private static void ballDrop(int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        List<int[]> set = new ArrayList<>();
        visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            if (gameCopy[i][j] != 0) {
                visited[i][j] = true;
                set.add(new int[]{i, j});
                queue.add(new int[]{i, j, gameCopy[i][j]});
                break;
            }
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int k = 0; k < 4; k++) {
                for (int t = 1; t < poll[2]; t++) {
                    int row = poll[0] + dx[k] * t;
                    int col = poll[1] + dy[k] * t;
                    if (0 <= row && row < h && 0 <= col && col < w) {
                        if (visited[row][col]) {
                            continue;
                        } else {
                            visited[row][col] = true;
                            if (gameCopy[row][col] > 0) {
                                set.add(new int[]{row, col});
                                queue.add(new int[]{row, col, gameCopy[row][col]});
                            }
                        }
                    }
                }
            }
        }

        for (int[] a : set) {
            gameCopy[a[0]][a[1]] = 0;
        }

//        for (int i = 0; i < h; i++) {
//            for (int t = 0; t < w; t++) {
//                System.out.println(gameCopy[i][t]);
//            }
//            System.out.println();
//        }
//        System.out.println();

    }



}