import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static Cuty[][] shark;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Map<Integer, Loc> sharkMap;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        shark = new Cuty[n][n];
        sharkMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int sharkNum = Integer.parseInt(st.nextToken());
                if (sharkNum > 0) {
                    sharkMap.put(sharkNum, new Loc(i,j));
                } else {
                    shark[i][j] = new Cuty(0, 0, 0);

                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            Loc loc = sharkMap.get(i);
            shark[loc.row][loc.col] = new Cuty(i, Integer.parseInt(st.nextToken()) - 1, k);
        }

        for (int t = 1; t <= m; t++) {
            int[][] arr = new int[4][4];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
            shark[sharkMap.get(t).row][sharkMap.get(t).col].setPriority(arr);

        }

//      sharkMap -> shark번호로 shark위치 조회
//         shark -> row, col로 실제 shark객체 조회
//        shark내부에는 번호와 방향, 방향에 따른 우선순위가 기록
        int time = 0;

        while (time++ <= 1000) {
            if (sharkMap.size() == 1) {
                System.out.println(time - 1);
                System.exit(0);
            }
            HashSet<Integer> deleteSet = new HashSet<>();
            for (int val : sharkMap.keySet()) {

                Loc loc = sharkMap.get(val);
                Cuty sh = shark[loc.row][loc.col];
                int[] priority = sh.priority[sh.dir];
                boolean flag = true;
                for (int t : priority) {
                    int row = loc.row + dx[t];
                    int col = loc.col + dy[t];
                    if (0 <= row && row < n && 0 <= col && col < n) {
                        if (shark[row][col].num > 0) {
                            if (shark[row][col].smell == k + 1 ) {
                                if (!shark[row][col].flag) {
                                    deleteSet.add(val);
                                    flag = false;
                                    break;
                                } else {
                                    continue;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            flag = false;
                            shark[row][col].num = val;
                            shark[row][col].smell = k + 1;
                            shark[row][col].dir = t;
                            shark[row][col].flag= false;
                            shark[row][col].setPriority(sh.priority);
                            sharkMap.get(val).row = row;
                            sharkMap.get(val).col = col;
                            break;
                        }
                    }
                }
                if (flag) {
                    for (int t : priority) {
                        int row = loc.row + dx[t];
                        int col = loc.col + dy[t];
                        if (0 <= row && row < n && 0 <= col && col < n) {
                            if (shark[row][col].num == val) {
                                shark[row][col].smell = k + 1;
                                shark[row][col].dir = t;
                                shark[row][col].setPriority(sh.priority);
                                sharkMap.get(val).row = row;
                                sharkMap.get(val).col = col;
                                break;
                            }
                        }
                    }
                }
            }
            for (int a : deleteSet) {
                sharkMap.remove(a);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (shark[i][j].num > 0) {
                        if (shark[i][j].smell == 1) {
                            shark[i][j].num = 0;
                            shark[i][j].dir = 0;
                            shark[i][j].smell -= 1;
                        } else {
                            shark[i][j].smell -= 1;
                            shark[i][j].flag = true;
                        }

                    }
                }
            }
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.print(shark[i][j].toString() + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//            System.out.println(sharkMap.size());
        }

        System.out.println(-1);

    }
}
class Loc{
    int row;
    int col;

    public Loc(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
class Cuty{
    int num;
    int dir;
    int smell;
    boolean flag = false;

    int[][] priority;

    public Cuty(int num, int dir, int smell) {
        this.num = num;
        this.dir = dir;
        this.smell = smell;
    }

    public void setPriority(int[][] arr) {
        priority = arr;
    }

    @Override
    public String toString() {
        return "Cuty{" +
                "num=" + num +
                ", dir=" + dir +
                ", smell=" + smell +
                '}';
    }
}