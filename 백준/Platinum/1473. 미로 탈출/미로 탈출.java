


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r, c;
    static char[][] maze;
    static int result = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<int[]> queue;
    static Map<Character, Integer> door;
    static Set<Integer>[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        visited = new Set[r][c];

        maze = new char[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                maze[i][j] = line.charAt(j);
                visited[i][j] = new HashSet<>();
            }
        }

        door = new HashMap<>();
        door.put('A', 15);
        door.put('C', 5);
        door.put('D', 10);
        door.put('B', 0);

        queue = new ArrayDeque<>();

        queue.add(new int[]{0, 0, 0, 0});
        visited[0][0].add(0);
        while (!queue.isEmpty()) {
            int[] thisTurn = queue.poll();
            int thisTurnRow = thisTurn[0];
            int thisTurnCol = thisTurn[1];
            int thisTurnState = thisTurn[2];
            int thisTurnLength = thisTurn[3];
//            System.out.print("thisTurnRow = " + thisTurnRow + " ");
//            System.out.print("thisTurnCol = " + thisTurnCol+ " ");
//            System.out.print("thisTurnState = " + thisTurnState+ " ");
//            System.out.println("thisTurnLength = " + thisTurnLength+ " ");
            if (thisTurnRow == r - 1 && thisTurnCol == c - 1) {
                result = Math.min(result, thisTurnLength);
                break;
            }
            int changeBit = thisTurnState;
            boolean flag = false;
            for (int index = 0; index < r + c; index++) {
                if (index < r) {
                    if (index == thisTurnRow) {
                        if ((thisTurnState & (1 << index)) != 0) {
                            flag = !flag;
                        }
                        changeBit ^= (1 << index);
                    }
                } else {
                    int realIndex = index - r;
                    if (realIndex == thisTurnCol) {
                        if ((thisTurnState & (1 << index)) != 0) {
                            flag = !flag;
                        }
                        changeBit ^= (1 << index);
                    }
                }
            }
//            System.out.println(changeBit);
            char thisTurnAlpha = maze[thisTurnRow][thisTurnCol];

            if (flag) {
                if (maze[thisTurnRow][thisTurnCol] == 'C') {
                    thisTurnAlpha = 'D';
                } else if (maze[thisTurnRow][thisTurnCol] == 'D') {
                    thisTurnAlpha = 'C';
                }
            }



            if (!visited[thisTurnRow][thisTurnCol].contains(changeBit)) {
                visited[thisTurnRow][thisTurnCol].add(changeBit);
                queue.add(new int[]{thisTurnRow, thisTurnCol, changeBit, thisTurnLength + 1});
            }



            for (int k = 0; k < 4; k++) {
                int row = thisTurnRow + dx[k];
                int col = thisTurnCol + dy[k];
                if (0 <= row && row < r && 0 <= col && col < c && maze[row][col] != 'B') {
                    flag = false;
                    for (int index = 0; index < r + c; index++) {
                        if (index < r) {
                            if (index == row) {
                                if ((thisTurnState & (1 << index)) != 0) {
                                    flag = !flag;
                                }
                            }
                        } else {
                            int realIndex = index - r;

                            if (realIndex == col) {
                                if ((thisTurnState & (1 << index)) != 0) {
                                    flag = !flag;
                                }
                            }
                        }
                    }
//                    System.out.println(flag);
                    char moveAlpha = maze[row][col];

                    if (flag) {
                        if (maze[row][col] == 'C') {
                            moveAlpha = 'D';
                        } else if (maze[row][col] == 'D') {
                            moveAlpha = 'C';
                        }
                    }
//                    System.out.println(thisTurnAlpha+ " " + moveAlpha);
                    if (canGo(thisTurnAlpha, moveAlpha, k % 2 == 0)) {

                        if (!visited[row][col].contains(thisTurnState)) {
                            visited[row][col].add(thisTurnState);
                            queue.add(new int[]{row, col, thisTurnState, thisTurnLength + 1});
                        }

                    }

                }
            }


        }
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);

        } else {
            System.out.println(result);

        }



    }

    private static boolean canGo(char thisTurnAlpha, char moveAlpha, boolean isUpper) {

        int thisTurnNum = door.get(thisTurnAlpha);
        int moveNum = door.get(moveAlpha);
        if (isUpper) {
            if (thisTurnNum % 2 == 1 && moveNum % 2 == 1) {
                return true;
            }
            return false;
        } else {
            if ((thisTurnNum == 15 || thisTurnNum == 10) && (moveNum == 10 || moveNum == 15)) {
                return true;
            }
            return false;
        }

    }
}