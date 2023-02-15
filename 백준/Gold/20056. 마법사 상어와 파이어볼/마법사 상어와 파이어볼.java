
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m, k;
    static ArrayList<Node>[][]fire;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        fire = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fire[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            fire[row][col].add(new Node(row, col, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < k; i++) {
            start();
//            for (int row = 0; row < n; row++) {
//                for (int col = 0; col < n; col++) {
//                    for (Node node : fire[row][col]) {
//                        System.out.print(node +", ");
//                    }
//                    System.out.print("         ");
//                }
//                System.out.println();
//            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (Node node : fire[i][j]) {
                    result += node.weight;
                }
            }
        }
        System.out.println(result);
    }

    private static void start() {
        Queue<Node> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (fire[i][j].size() != 0) {
                    for (Node node : fire[i][j]) {
                        int k = node.direction;
                        node.row = (i + node.velocity*dx[k] + n * 1001) % n;
                        node.col = (j + node.velocity*dy[k] + n * 1001) % n;
                        queue.add(node);
                    }
                    fire[i][j].clear();

                }
            }
        }
        boolean[][] flag = new boolean[n][n];
        int[][] count = new int[n][n];
        while (!queue.isEmpty()) {
            Node thisTurn = queue.poll();
            if (fire[thisTurn.row][thisTurn.col].size() == 0) {
                fire[thisTurn.row][thisTurn.col].add(thisTurn);
                count[thisTurn.row][thisTurn.col] += 1;
            } else {
                if (fire[thisTurn.row][thisTurn.col].get(0).direction % 2 != thisTurn.direction % 2) {
                    flag[thisTurn.row][thisTurn.col] = true;
                }
                count[thisTurn.row][thisTurn.col] += 1;
                Node node = fire[thisTurn.row][thisTurn.col].get(0);
                node.weight += thisTurn.weight;
                node.velocity += thisTurn.velocity;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (count[i][j] >= 2) {
                    Node node = fire[i][j].get(0);
                    int weight = node.weight / 5;
                    int velocity = node.velocity / count[i][j];
                    boolean isOdd = flag[i][j];
                    fire[i][j].clear();
                    if (weight != 0) {
                        for (int k = 0; k < 4; k++) {
                            fire[i][j].add(new Node(i, j, weight, velocity, isOdd ? 1 + 2 * k : 2 * k));
                        }
                    }
                }
            }
        }


    }
}
class Node {
    int row;
    int col;
    int weight;
    int velocity;
    int direction;

    @Override
    public String toString() {
        return "Node{" +
                "row=" + row +
                ", col=" + col +
                ", weight=" + weight +
                ", velocity=" + velocity +
                ", direction=" + direction +
                '}';
    }

    public Node(int row, int col, int weight, int velocity, int direction) {
        this.row = row;
        this.col = col;
        this.weight = weight;
        this.velocity = velocity;
        this.direction = direction;
    }


}
