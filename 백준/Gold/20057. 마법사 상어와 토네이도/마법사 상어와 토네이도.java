
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] sand;
    static int[] case0Dx = {-2, -1, -1, -1, 0, 1, 1, 1, 2};
    static int[] case0Dy = {0, -1, 0, 1, -2, -1, 0, 1, 0};
    static double[] case0Rate = {0.02, 0.1, 0.07, 0.01, 0.05, 0.1, 0.07, 0.01, 0.02};

    static int[] case1Dx = {0, 1, 0, -1, 2, 1, 0, -1, 0};
    static int[] case1Dy = {-2, -1, -1, -1, 0, 1, 1, 1, 2};
    static double[] case1Rate = {0.02, 0.1, 0.07, 0.01, 0.05, 0.1, 0.07, 0.01, 0.02};

    static int[] case2Dx = {-2, -1, -1, -1, 0, 1, 1, 1, 2};
    static int[] case2Dy = {0, 1, 0, -1, 2, 1, 0, -1, 0};
    static double[] case2Rate = {0.02, 0.1, 0.07, 0.01, 0.05, 0.1, 0.07, 0.01, 0.02};

    static int[] case3Dx = {0, -1, 0, 1, -2, -1, 0, 1, 0};
    static int[] case3Dy = {-2, -1, -1, -1, 0, 1, 1, 1, 2};
    static double[] case3Rate = {0.02, 0.1, 0.07, 0.01, 0.05, 0.1, 0.07, 0.01, 0.02};
    static int[] nextRow = {0, 1, 0, -1};
    static double[] reverseRate ={0.02, 0.01, 0.07, 0.1, 0.05, 0.01, 0.07, 0.1, 0.02};
    static int[] nextCol = {-1, 0, 1, 0};
    static int sandCount = 0;
    static Queue<Node> queue;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        sand = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                sand[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        queue = new ArrayDeque<>();

        queue.add(new Node(n / 2, n / 2, 0, 1, false));

        while (!queue.isEmpty()) {
            Node thisTurn = queue.poll();
            if (thisTurn.length == n) {
                break;
            }
            int thisRow = thisTurn.row;
            int thisCol = thisTurn.col;
            int thisDir = thisTurn.direction;
            int thisLength = thisTurn.length;
            boolean isLast = thisTurn.flag;
//            System.out.println(thisRow + " " + thisCol + " " + thisLength + " " +sand[thisRow][thisCol] + " " + thisDir + " " +sandCount);

            for (int i = 0; i < thisLength; i++) {
                thisRow += nextRow[thisDir];
                thisCol += nextCol[thisDir];
                int sc = sand[thisRow][thisCol];
                scatter(thisRow, thisCol, sc, thisDir);
                sand[thisRow][thisCol] = 0;
            }
            if (isLast) {
                queue.add(new Node(thisRow, thisCol, (thisDir + 1) % 4, thisLength + 1, false));
            } else {
                if (thisRow == n - 1 && thisCol == n - 1) {
                    queue.add(new Node(thisRow, thisCol, (thisDir + 1) % 4, thisLength, false));

                }
                queue.add(new Node(thisRow, thisCol, (thisDir + 1) % 4, thisLength, true));
            }



        }
        System.out.println(sandCount);



    }

    private static void scatter(int thisRow, int thisCol, int sc, int dir) {
        int value = 0;
        switch (dir) {
            case 0:
                for (int i = 0; i < case0Dx.length; i++) {
                    int row = thisRow + case0Dx[i];
                    int col = thisCol + case0Dy[i];
                    value += (int) (sc * case0Rate[i]);
//                    System.out.println(value);
                    if (0 <= row && row < n && 0 <= col && col < n) {

                        sand[row][col] += (int) (sc * case0Rate[i]);
                    } else {
                        sandCount += (int) (sc * case0Rate[i]);
                    }
                }
//                System.out.println((thisRow + nextRow[0]) + " " +  (thisCol + nextCol[0]) + " 이네?" + sc + value+ " "+ sandCount);
                if (0 <= thisRow + nextRow[0] && thisRow + nextRow[0] < n && 0 <= thisCol + nextCol[0] && thisCol + nextCol[0] < n) {
                    sand[thisRow + nextRow[0]][thisCol + nextCol[0]] += Math.max(sc - value, 0);
                } else {
                    sandCount += Math.max(sc - value, 0);
                }
                break;
            case 1:
                for (int i = 0; i < case0Rate.length; i++) {
                    int row = thisRow + case1Dx[i];
                    int col = thisCol + case1Dy[i];
                    value += (int) (sc * case1Rate[i]);
                    if (0 <= row && row < n && 0 <= col && col < n) {

                        sand[row][col] += (int) (sc * case1Rate[i]);
                    } else {
                        sandCount += (int) (sc * case1Rate[i]);
                    }
                }
                if (0 <= thisRow + nextRow[1] && thisRow + nextRow[1] < n && 0 <= thisCol + nextCol[1] && thisCol + nextCol[1] < n) {
                    sand[thisRow + nextRow[1]][thisCol + nextCol[1]] +=Math.max(sc - value, 0);
                } else {
                    sandCount += Math.max(sc - value, 0);
                }
                break;
            case 2:
                for (int i = 0; i < case0Rate.length; i++) {
                    int row = thisRow + case2Dx[i];
                    int col = thisCol + case2Dy[i];
                    value += (int) (sc * case2Rate[i]);
//                    System.out.println(value);
                    if (0 <= row && row < n && 0 <= col && col < n) {

                        sand[row][col] += (int) (sc * case2Rate[i]);
                    } else {
                        sandCount += (int) (sc * case2Rate[i]);
                    }
                }
//                System.out.println(sc + " " + value);
//                System.out.println(sc - value);
                if (0 <= thisRow + nextRow[2] && thisRow + nextRow[2] < n && 0 <= thisCol + nextCol[2] && thisCol + nextCol[2] < n) {
                    sand[thisRow + nextRow[2]][thisCol + nextCol[2]] += Math.max(sc - value, 0);
                } else {
                    sandCount += Math.max(sc - value, 0);
                }
                break;
//            static int[] dx = {-2, -1, -1, -1, 0, 1, 1, 1, 2};
//            static int[] dy = {0, -1, 0, 1, -2, -1, 0, 1, 0};
            case 3:
                for (int i = 0; i < case2Rate.length; i++) {
                    int row = thisRow + case3Dx[i];
                    int col = thisCol + case3Dy[i];
                    value += (int) (sc * case3Rate[i]);
                    if (0 <= row && row < n && 0 <= col && col < n) {

                        sand[row][col] += (int) (sc * case3Rate[i]);
                    } else {
                        sandCount += (int) (sc * case3Rate[i]);
                    }
                }
                if (0 <= thisRow + nextRow[3] && thisRow + nextRow[3] < n && 0 <= thisCol + nextCol[3] && thisCol + nextCol[3] < n) {
                    sand[thisRow + nextRow[3]][thisCol + nextCol[3]] += Math.max(sc - value, 0);
                } else {
                    sandCount += Math.max(sc - value, 0);
                }
                break;

        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(sand[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

    }
}

class Node{
    int row;
    int col;
    int direction;
    int length;
    boolean flag;

    public Node(int row, int col, int direction, int length, boolean flag) {
        this.row = row;
        this.col = col;
        this.direction = direction;
        this.length = length;
        this.flag = flag;
    }


}
