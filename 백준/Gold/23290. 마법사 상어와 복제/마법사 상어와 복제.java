import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int m, s;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static ArrayList<Integer>[][] water;
    static int sharkRow;
    static int sharkCol;
    static int[] sharkDx = {-1, 0, 1, 0};
    static int[] sharkDy = {0, -1, 0, 1};
    static boolean[][] visited;
    static int[] maxDir;
    static int maxValue;
    static int[] resultDir;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        water = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                water[i][j] = new ArrayList<>();
            }
        }
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        for (int k = 0; k < m; k++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            water[row][col].add(dir);
        }

        st = new StringTokenizer(br.readLine());
        sharkRow = Integer.parseInt(st.nextToken()) - 1;
        sharkCol = Integer.parseInt(st.nextToken()) - 1;

        while (--s >= 0) {
            ArrayList<Integer>[][] saveWater = new ArrayList[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    saveWater[i][j] = new ArrayList<>();
                    for (int a : water[i][j]) {
                        saveWater[i][j].add(a);
                    }
                }
            }
            Stack<int[]> stack = new Stack<>();
            ArrayList<int[]> list = new ArrayList<>();
//            2.의 과정
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {

                    for (int d : water[i][j]) {
                        if (d < 0) {
                            Integer val = water[i][j].get(0);
                            list.add(new int[]{i, j, val - 1});
                            continue;
                        }
//                            상어가 이제 한칸이동을 하는데... 상어가 있는칸 물고기 냄새가 있는 칸을 가지 않도록 + 갈 수 있는 곳이 없으면 모두 가만히 두자
//                            순회하며 갈 수 있는곳 찾고 그 값 스택에 넣어두자
                        boolean flag = true;
                        for (int k = 0; k < 8; k++) {
                            int row = i + dx[(8- k + d) % 8];
                            int col = j + dy[(8 - k + d) % 8];
                            if (canGo(row, col)) {
                                stack.add(new int[]{row, col, (8 - k + d) % 8});
                                flag = false;
                                break;
                            }
                        }

                        if (flag) {
                            stack.add(new int[]{i, j, d});
                        }
//                            break해서 나왔으면 그건 갈 수 있는 곳이 있다는 것 즉, flag = false, flag = true라는 것은 모든 애들이 갈 수 있는 곳이 없다는 거고 그렇다면 걍 
//                            모든 애들 stack에 넣자 그대로


                    }
                }
            }
//            이제 water에 다 넣자
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    water[i][j].clear();
                }
            }
            for (int i = 0; i < list.size(); i++) {
                int[] pop = list.get(i);
                int row = pop[0];
                int col = pop[1];
                int dir = pop[2];
                if (dir == -3) {
                    continue;
                }
                water[row][col].add(dir);
            }
            while (!stack.isEmpty()) {
                int[] pop = stack.pop();
                int row = pop[0];
                int col = pop[1];
                int dir = pop[2];

                water[row][col].add(dir);
            }
//            System.out.println("이건 - 칸에 무ㅠㄹ고기있으면안됨" );
//            print();
            maxValue = Integer.MIN_VALUE;
            maxDir = new int[3];
            resultDir = new int[3];
            visited = new boolean[4][4];
//            visited[sharkRow][sharkCol] = true;

            dfs(sharkRow, sharkCol, 0, 0);

            for (int k = 0; k < 3; k++) {
                sharkRow += sharkDx[resultDir[k]];
                sharkCol += sharkDy[resultDir[k]];
//                System.out.println(resultDir[k]);
                if (water[sharkRow][sharkCol].size() >= 1) {
                    if (water[sharkRow][sharkCol].get(0) < 0) {
                        if (water[sharkRow][sharkCol].size() >= 2) {
                            water[sharkRow][sharkCol].clear();
                            water[sharkRow][sharkCol].add(-1);
                        } else {
                            continue;
                        }
                    } else {
                        water[sharkRow][sharkCol].clear();
                        water[sharkRow][sharkCol].add(-1);
                    }
                }



            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {

                        for (int a : saveWater[i][j]) {
                            if (a >= 0) {
                                water[i][j].add(a);

                            }
                        }


                }
            }
//            System.out.println(sharkRow +" "  + sharkCol);
//            print();



        }
        int result = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int a : water[i][j]) {
                    result += a < 0 ? 0 : 1;
                }
            }
        }
        System.out.println(result);

    }

    private static void dfs(int sharkRow, int sharkCol, int length, int fishCount) {
        if (length == 3) {
            if (maxValue < fishCount) {
                maxValue = fishCount;
                resultDir[0] = maxDir[0];
                resultDir[1] = maxDir[1];
                resultDir[2] = maxDir[2];
            }
            return;
        }
        for (int k = 0; k < 4; k++) {
            int row = sharkRow + sharkDx[k];
            int col = sharkCol + sharkDy[k];
            if (0 <= row && row < 4 && 0 <= col && col < 4) {
                if (!visited[row][col]) {
                    maxDir[length] = k;
                    visited[row][col] = true;
                    if (water[row][col].size() >= 1) {
                        if (water[row][col].get(0) < 0) {
                            dfs(row, col, length + 1, fishCount + water[row][col].size() - 1);
                        } else {
                            dfs(row, col, length + 1, fishCount + water[row][col].size());

                        }
                    } else {
                        dfs(row, col, length + 1, fishCount);
                    }

                    visited[row][col] = false;
                } else {
                    maxDir[length] = k;

                    dfs(row, col, length + 1, fishCount);

                }
            }
        }
    }

    private static void print() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int a : water[i][j]) {
                    System.out.print(a+" ");
                }
                System.out.print("|");

            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean canGo(int row, int col) {
        return 0 <= row && row < 4 && 0 <= col && col < 4 && !(water[row][col].size() >= 1 && water[row][col].get(0) < 0) && !(row == sharkRow && col ==sharkCol) ;
    }
}