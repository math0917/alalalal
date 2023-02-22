import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int testCase;
    static int m, a;
    static PriorityQueue<BC>[][] map;
    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 1, 0, -1};
    static int[] userAMove;
    static int[] userBMove;
    static int[] userA;
    static int[] userB;
    static int result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            result = 0;
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            map = new PriorityQueue[10][10];
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    map[i][j] = new PriorityQueue<>();
                }
            }
            userAMove = new int[m + 1];
            userBMove = new int[m + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= m; i++) {
                userAMove[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= m; i++) {
                userBMove[i] = Integer.parseInt(st.nextToken());
            }

            for (int k = 0; k < a; k++) {
                st = new StringTokenizer(br.readLine());
                Queue<int[]> queue = new ArrayDeque<>();
                int col = Integer.parseInt(st.nextToken()) - 1;
                int row = Integer.parseInt(st.nextToken()) - 1;
                int range = Integer.parseInt(st.nextToken());
                int health = Integer.parseInt(st.nextToken());
//                System.out.println(row + " " + col);
                BC bc = new BC(health);
                bc.num = k;
                boolean[][] visited = new boolean[10][10];
                visited[row][col] = true;
                map[row][col].add(bc);
                queue.add(new int[]{row, col, 0});
                while (!queue.isEmpty()) {
                    int[] thisTurn = queue.poll();
                    int thisRow = thisTurn[0];
                    int thisCol = thisTurn[1];
                    int thisLength = thisTurn[2];
                    if (thisLength == range) {
                        break;
                    }
                    for (int p = 1; p < 5; p++) {
                        row = thisRow + dx[p];
                        col = thisCol + dy[p];
                        if (0 <= row && row < 10 && 0 <= col && col < 10) {
                            if (!visited[row][col]) {
                                visited[row][col] = true;
                                queue.add(new int[]{row, col, thisLength + 1});
                                map[row][col].add(bc);
                            }
                        }
                    }
                }
            }

            start();
            System.out.println("#" + t + " " +result);
        }
    }

    private static void start() {
        userA = new int[]{0, 0};
        userB = new int[]{9, 9};
        for (int i = 0; i < m + 1; i++) {
//            System.out.println(i+"시간");
            move(i);
//            System.out.println(userA[0] + " " +userA[1] + " "+ userB[0] + " "+ userB[1]);
            cal();

        }
    }

    private static void cal() {
//        System.out.println(result);
        if ((userA[0] == userB[0]) && (userA[1] == userB[1])) {
            PriorityQueue<BC> bc = map[userA[0]][userA[1]];
            if (bc.size() == 1) {
                result += bc.peek().value;
            } else if (bc.size() > 1) {
                BC poll = bc.poll();
                result += poll.value;
                result += bc.peek().value;
                bc.add(poll);
            }
            return;
        }
        PriorityQueue<BC> intsA = map[userA[0]][userA[1]];
        PriorityQueue<BC> intsB = map[userB[0]][userB[1]];
        if (intsA.size() != 0) {
            BC bcA = intsA.poll();
//            System.out.println(bcA.num);
            if (intsB.size() != 0) {
                BC bcB = intsB.poll();
//                System.out.println(bcB.num);
                if (bcA == bcB) {

                    int thisResult = bcA.value;
                    if (intsA.size() > 0) {
                        BC bcA2 = intsA.poll();
                        thisResult = Math.max(thisResult, bcA2.value + bcA.value);
                        intsA.add(bcA2);
                    }
                    if (intsB.size() > 0) {
                        BC bcB2 = intsB.poll();
                        thisResult = Math.max(thisResult, bcB2.value + bcA.value);
                        intsB.add(bcB2);
                    }
                    result += thisResult;
                } else {
                    result += bcA.value;
                    result += bcB.value;
                }
                intsB.add(bcB);
            } else {
                result += bcA.value;
            }
            intsA.add(bcA);
        } else {
            if (intsB.size() != 0) {

                result += intsB.peek().value;
//                System.out.println(intsB.peek().num);
            }
        }

    }

    private static void move(int i) {
        userA[0] += dx[userAMove[i]];
        userA[1] += dy[userAMove[i]];

        userB[0] += dx[userBMove[i]];
        userB[1] += dy[userBMove[i]];
    }
}

class BC implements Comparable<BC>{
    int num;
    int value;

    public BC(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(BC o) {
        return Integer.compare(o.value, value);
    }
}