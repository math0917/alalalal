import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int inning;
    static int[][] result;
    static int score = Integer.MIN_VALUE;
    static List<Integer> queue;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        inning = Integer.parseInt(br.readLine());
        result = new int[inning][9];
        for (int i = 0; i < inning; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[9];
        queue = new ArrayList<>();

        visited[0] = true;
        start();
        System.out.println(score);
    }

    private static void start() {
        if (queue.size() == 9) {
            game();
        }
        if (queue.size() == 3) {
            queue.add(0);
            start();
            queue.remove((Object) 0);
        }
        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                queue.add(i);
                start();
                queue.remove((Object) i);
                visited[i] = false;
            }
        }
    }

    private static void game() {
        int sc= 0;
        int in = 0;
        int startIdx = 0;
        int outCount = 0;
        boolean[] map = new boolean[3];
//        for (int a : queue) {
//            System.out.print( a + " ");
//        }
//        System.out.println();
        while (true) {

            if (in == inning) {
//                System.out.println(sc);
                score = Math.max(score, sc);
                break;
            }
            int player = queue.get(startIdx);
//            System.out.println(player + " "+ sc + " " + outCount);
            switch (result[in][player]){
                case 0:
                    if (outCount == 2) {
                        in += 1;
                        outCount = 0;
                        map[0] = map[1] = map[2] = false;
                    } else {
                        outCount += 1;
                    }
                    startIdx = (startIdx + 1) % 9;
                    break;
                case 1: case 2: case 3 :case 4:
                    for (int i = 2; i >= 0; i--) {
                        if (map[i]) {
                            try {
                                map[i + result[in][player]] = map[i];

                            } catch (Exception e) {
                                sc += 1;
                            }
                            map[i] = false;
                        }

                    }
                    try {
                        map[result[in][player] - 1] = true;
                    } catch (Exception e) {
                        sc += 1;
                    }
//                    System.out.println(sc);
                    startIdx = (startIdx + 1) % 9;
                    break;


                }


        }
    }
}