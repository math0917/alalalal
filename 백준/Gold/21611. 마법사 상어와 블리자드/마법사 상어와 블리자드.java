import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] shark;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] rotateDx = {0, 1, 0, -1};
    static int[] rotateDy = {-1, 0, 1, 0};
    static int d, s;
    static int score = 0;
    static Queue<Integer> queue;
    static Deque<int[]> popQueue;
    static boolean flag;
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
//        flag = true;
        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken()) - 1;
            s = Integer.parseInt(st.nextToken());
            flag = true;
            delete();
//            print();
            shift();
//            print();
            while (flag) {
//                System.out.println("hi");
                flag = false;
                pop();
                shiftPop();
//                print();
            }

//            print();
            group();
            groupPop();
//            print();
        }
        System.out.println(score);

    }

    private static void groupPop() {
        popQueue.poll();
        Queue<Integer> queue1 = new ArrayDeque<>();
        while (!popQueue.isEmpty()) {
            int[] ints = popQueue.pollFirst();
            queue1.add(ints[1]);
            queue1.add(ints[0]);
        }
        int row = n / 2;
        int col = n / 2;
        int dir = 0;
        try {
            for (int i = 1; i < n - 1; i++) {
//            System.out.println(row+ " " + col);
                for (int j = 0; j < i; j++) {
                    row += rotateDx[dir];
                    col += rotateDy[dir];
                    shark[row][col] = queue1.poll();

                }
                dir = (dir + 1) % 4;
                for (int j = 0; j < i; j++) {
                    row += rotateDx[dir];
                    col += rotateDy[dir];
                    shark[row][col] = queue1.poll();

                }
                dir = (dir + 1) % 4;

            }
            for (int j = 0; j < n - 1; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                shark[row][col] = queue1.poll();


            }
            dir = (dir + 1) % 4;

            for (int j = 0; j < n - 1; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                shark[row][col] = queue1.poll();


            }
            dir = (dir + 1) % 4;

            for (int j = 0; j < n - 1; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                shark[row][col] = queue1.poll();

            }
        } catch (Exception e) {
            return;
        }


    }

    private static void group() {
        popQueue = new ArrayDeque<>();
        int row = n / 2;
        int col = n / 2;
        int dir = 0;
        popQueue.add(new int[]{-1, 0});
        for (int i = 1; i < n - 1; i++) {
//            System.out.println(row+ " " + col);
            for (int j = 0; j < i; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                if (shark[row][col] > 0) {
                    if (popQueue.peekLast()[0] != shark[row][col]) {
                        popQueue.addLast(new int[]{shark[row][col], 1});
                    } else {
                        popQueue.peekLast()[1] += 1;
                    }
                    shark[row][col] = 0;
                }

            }
            dir = (dir + 1) % 4;
            for (int j = 0; j < i; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                if (shark[row][col] > 0) {
                    if (popQueue.peekLast()[0] != shark[row][col]) {
                        popQueue.addLast(new int[]{shark[row][col], 1});
                    } else {
                        popQueue.peekLast()[1] += 1;
                    }
                    shark[row][col] = 0;
                }
            }
            dir = (dir + 1) % 4;

        }
        for (int j = 0; j < n - 1; j++) {
            row += rotateDx[dir];
            col += rotateDy[dir];
            if (shark[row][col] > 0) {
                if (popQueue.peekLast()[0] != shark[row][col]) {
                    popQueue.addLast(new int[]{shark[row][col], 1});
                } else {
                    popQueue.peekLast()[1] += 1;
                }
                shark[row][col] = 0;
            }

        }
        dir = (dir + 1) % 4;

        for (int j = 0; j < n - 1; j++) {
            row += rotateDx[dir];
            col += rotateDy[dir];
            if (shark[row][col] > 0) {
                if (popQueue.peekLast()[0] != shark[row][col]) {
                    popQueue.addLast(new int[]{shark[row][col], 1});
                } else {
                    popQueue.peekLast()[1] += 1;
                }
                shark[row][col] = 0;
            }

        }
        dir = (dir + 1) % 4;

        for (int j = 0; j < n - 1; j++) {
            row += rotateDx[dir];
            col += rotateDy[dir];
            if (shark[row][col] > 0) {
                if (popQueue.peekLast()[0] != shark[row][col]) {
                    popQueue.addLast(new int[]{shark[row][col], 1});
                } else {
                    popQueue.peekLast()[1] += 1;
                }
                shark[row][col] = 0;
            }
        }

    }

    private static void shiftPop() {
        int row = n / 2;
        int col = n / 2;
        int dir = 0;
        popQueue.poll();
        try {
            for (int i = 1; i < n - 1; i++) {
//            System.out.println(row+ " " + col);
                for (int j = 0; j < i; j++) {
                    row += rotateDx[dir];
                    col += rotateDy[dir];
                    shark[row][col] = popQueue.pollFirst()[0];

                }
                dir = (dir + 1) % 4;
                for (int j = 0; j < i; j++) {
                    row += rotateDx[dir];
                    col += rotateDy[dir];
                    shark[row][col] = popQueue.pollFirst()[0];
                }
                dir = (dir + 1) % 4;

            }
            for (int j = 0; j < n - 1; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                shark[row][col] = popQueue.pollFirst()[0];


            }
            dir = (dir + 1) % 4;

            for (int j = 0; j < n - 1; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                shark[row][col] = popQueue.pollFirst()[0];


            }
            dir = (dir + 1) % 4;

            for (int j = 0; j < n - 1; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                shark[row][col] = popQueue.pollFirst()[0];
            }
        } catch (Exception e) {
            return;
        }

    }

    private static void pop() {
        popQueue = new ArrayDeque<>();
        int row = n / 2;
        int col = n / 2;
        int dir = 0;
        popQueue.add(new int[]{-1, 0});
        for (int i = 1; i < n - 1; i++) {
//            System.out.println(row+ " " + col);
            for (int j = 0; j < i; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                if (shark[row][col] > 0) {
                    if (popQueue.peekLast()[0] != shark[row][col]) {

                        if (popQueue.peekLast()[1] >= 4) {
                            flag = true;
                            score += popQueue.peekLast()[1] * popQueue.peekLast()[0];
                            int v = popQueue.peekLast()[1];
                            for (int count = 0; count < v; count++) {
                                popQueue.pollLast();
                            }
                            popQueue.addLast(new int[]{shark[row][col], 1});
                        } else {
                            popQueue.addLast(new int[]{shark[row][col], 1});
                        }

                    } else {
                        popQueue.addLast(new int[]{shark[row][col], popQueue.peekLast()[1] + 1});
                    }
                    shark[row][col] = 0;
                }

            }
            dir = (dir + 1) % 4;
            for (int j = 0; j < i; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                if (shark[row][col] > 0) {
                    if (popQueue.peekLast()[0] != shark[row][col]) {

                        if (popQueue.peekLast()[1] >= 4) {
                            flag = true;

                            score += popQueue.peekLast()[1] * popQueue.peekLast()[0];
                            int v = popQueue.peekLast()[1];
                            for (int count = 0; count < v; count++) {
                                popQueue.pollLast();
                            }
                            popQueue.addLast(new int[]{shark[row][col], 1});
                        } else {
                            popQueue.addLast(new int[]{shark[row][col], 1});
                        }

                    } else {
                        popQueue.addLast(new int[]{shark[row][col], popQueue.peekLast()[1] + 1});
                    }
                    shark[row][col] = 0;
                }
            }
            dir = (dir + 1) % 4;

        }
        for (int j = 0; j < n - 1; j++) {
            row += rotateDx[dir];
            col += rotateDy[dir];
            if (shark[row][col] > 0) {
                if (popQueue.peekLast()[0] != shark[row][col]) {

                    if (popQueue.peekLast()[1] >= 4) {
                        flag = true;

                        score += popQueue.peekLast()[1] * popQueue.peekLast()[0];
                        int v = popQueue.peekLast()[1];
                        for (int count = 0; count < v; count++) {
                            popQueue.pollLast();
                        }
                        popQueue.addLast(new int[]{shark[row][col], 1});
                    } else {
                        popQueue.addLast(new int[]{shark[row][col], 1});
                    }

                } else {
                    popQueue.addLast(new int[]{shark[row][col], popQueue.peekLast()[1] + 1});
                }
                shark[row][col] = 0;
            }

        }
        dir = (dir + 1) % 4;

        for (int j = 0; j < n - 1; j++) {
            row += rotateDx[dir];
            col += rotateDy[dir];
            if (shark[row][col] > 0) {
                if (popQueue.peekLast()[0] != shark[row][col]) {

                    if (popQueue.peekLast()[1] >= 4) {
                        flag = true;

                        score += popQueue.peekLast()[1] * popQueue.peekLast()[0];
                        int v = popQueue.peekLast()[1];
                        for (int count = 0; count < v; count++) {
                            popQueue.pollLast();
                        }
                        popQueue.addLast(new int[]{shark[row][col], 1});
                    } else {
                        popQueue.addLast(new int[]{shark[row][col], 1});
                    }

                } else {
                    popQueue.addLast(new int[]{shark[row][col], popQueue.peekLast()[1] + 1});
                }
                shark[row][col] = 0;
            }

        }
        dir = (dir + 1) % 4;

        for (int j = 0; j < n - 1; j++) {
            row += rotateDx[dir];
            col += rotateDy[dir];
            if (shark[row][col] > 0) {
                if (popQueue.peekLast()[0] != shark[row][col]) {

                    if (popQueue.peekLast()[1] >= 4) {
                        flag = true;

                        score += popQueue.peekLast()[1] * popQueue.peekLast()[0];
                        int v = popQueue.peekLast()[1];
                        for (int count = 0; count < v; count++) {
                            popQueue.pollLast();
                        }
                        popQueue.addLast(new int[]{shark[row][col], 1});
                    } else {
                        popQueue.addLast(new int[]{shark[row][col], 1});
                    }

                } else {
                    popQueue.addLast(new int[]{shark[row][col], popQueue.peekLast()[1] + 1});
                }
                shark[row][col] = 0;
            }
        }
        if (popQueue.peekLast()[1] >= 4) {
            flag = true;
            score += popQueue.peekLast()[0] * popQueue.peekLast()[1];
            int v = popQueue.peekLast()[1];
            for (int j = 0; j < v; j++) {
                popQueue.pollLast();
            }
        }
    }

    private static void shift() {
        queueFill();
        queuePop();
    }

    private static void queuePop() {
        int row = n / 2;
        int col = n / 2;
        int dir = 0;
        try {
            for (int i = 1; i < n - 1; i++) {
                for (int j = 0; j < i; j++) {
                    row += rotateDx[dir];
                    col += rotateDy[dir];
                    shark[row][col] = queue.poll();
                }
                dir = (dir + 1) % 4;
                for (int j = 0; j < i; j++) {
                    row += rotateDx[dir];
                    col += rotateDy[dir];
                    shark[row][col] = queue.poll();
                }
                dir = (dir + 1) % 4;

            }
            for (int j = 0; j < n - 1; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                shark[row][col] = queue.poll();

            }
            dir = (dir + 1) % 4;

            for (int j = 0; j < n - 1; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                shark[row][col] = queue.poll();

            }
            dir = (dir + 1) % 4;

            for (int j = 0; j < n - 1; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                shark[row][col] = queue.poll();

            }
        } catch (Exception e) {
            return;
        }

    }

    private static void queueFill() {
        queue = new ArrayDeque<>();
        int row = n / 2;
        int col = n / 2;
        int dir = 0;
        for (int i = 1; i < n - 1; i++) {
//            System.out.println(row+ " " + col);
            for (int j = 0; j < i; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                if (shark[row][col] != 0) {
                    queue.add(shark[row][col]);
                }
                shark[row][col] = 0;
            }
            dir = (dir + 1) % 4;
            for (int j = 0; j < i; j++) {
                row += rotateDx[dir];
                col += rotateDy[dir];
                if (shark[row][col] != 0) {
                    queue.add(shark[row][col]);
                }
                shark[row][col] = 0;

            }
            dir = (dir + 1) % 4;

        }
        for (int j = 0; j < n - 1; j++) {
            row += rotateDx[dir];
            col += rotateDy[dir];
            if (shark[row][col] != 0) {
                queue.add(shark[row][col]);
            }
            shark[row][col] = 0;

        }
        dir = (dir + 1) % 4;

        for (int j = 0; j < n - 1; j++) {
            row += rotateDx[dir];
            col += rotateDy[dir];
            if (shark[row][col] != 0) {
                queue.add(shark[row][col]);
            }
            shark[row][col] = 0;

        }
        dir = (dir + 1) % 4;

        for (int j = 0; j < n - 1; j++) {
            row += rotateDx[dir];
            col += rotateDy[dir];
            if (shark[row][col] != 0) {
                queue.add(shark[row][col]);
            }
            shark[row][col] = 0;

        }
    }

    private static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(shark[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void delete() {
        for (int i = 1; i <= s; i++) {
            int row = n / 2 + i * dx[d];
            int col = n / 2 + i * dy[d];
            shark[row][col] = 0;
        }
    }
}