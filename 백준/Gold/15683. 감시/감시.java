import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
//    static Set<Integer>[][]visited;
    static int[] cctv;

    static List<int[]> cctvLoc;
    static int[] proc;
    static int cnt = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
//        visited = new HashSet[n][m];
        cctvLoc = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= map[i][j] && map[i][j] <= 5) {
//                    visited[i][j] = new HashSet<>();
                    cctvLoc.add(new int[]{i, j});
                }
            }
        }
        proc = new int[cctvLoc.size()];

        cctv = new int[6];
        cctv[1] = 2;
        cctv[2] = 10;
        cctv[3] = 3;
        cctv[4] = 11;
        cctv[5] = 15;
        count(0);
        System.out.println(cnt);

    }

    private static void count(int index) {
//        System.out.println(index);
        if (index == cctvLoc.size()) {
//            System.out.println(Arrays.toString(proc));
            int result = 0;
            int[][] copy = new int[n][m];
            for (int r = 0; r < n; r++) {
                copy[r] = map[r].clone();
            }
            for (int i = 0; i < cctvLoc.size(); i++) {
                int thisRow = cctvLoc.get(i)[0], thisCol = cctvLoc.get(i)[1];
                int bit = proc[i];
//                System.out.println(thisRow + " " + thisCol+" "+proc[i]);
                for (int k = 0; k < 4; k++) {
                    if ((bit & (1 << k)) != 0) {
                        int idx = 1;
                        while (true) {
                            int row = thisRow + dx[k] * idx;
                            int col = thisCol + dy[k] * idx;
//                            System.out.println(row + " " + col);
                            if (0 <= row && row < n && 0 <= col && col < m) {
                                if (copy[row][col] == 6) {
                                    break;
                                } else if (copy[row][col] == 0) {
                                    copy[row][col] = 7;
                                    idx++;
                                } else {
                                    idx ++;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }


            }
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    result += copy[row][col] == 0 ? 1 : 0;
                }
            }
//            System.out.println(result);
            cnt = Math.min(result, cnt);

            return;
        }
        int[] thisCctvLoc = cctvLoc.get(index);
        int thisRow = thisCctvLoc[0];
        int thisCol = thisCctvLoc[1];
        int cctvSort = cctv[map[thisRow][thisCol]];
        Set<Integer> visited = new HashSet<>();

        while (true) {
            if (!visited.contains(cctvSort)) {
                proc[index] = cctvSort;
                visited.add(cctvSort);
                count(index + 1);
                cctvSort = shiftLeft(cctvSort);
            } else {
                break;
            }
        }

    }



    private static int shiftLeft(int num) {
        int value = num;
        num = (num << 1) & 15;
        if ((value << 1) > 15) {
            num |= 1;
        }
         return num;


    }
}