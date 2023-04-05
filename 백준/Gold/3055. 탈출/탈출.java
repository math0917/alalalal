import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, answer;
    static int[] S;
    static List<int[]> wList = new ArrayList<>();
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S') {
                    S = new int[] {i, j};
                    visited[i][j] = true;
                }
                else if(map[i][j] == '*') {
                    wList.add(new int[] {i, j});
                    visited[i][j] = true;
                }
            }
        }

        move();

        if(answer == -1) System.out.println("KAKTUS");
        else System.out.println(answer);
    }

    static void move() {
        Queue<int[]> queueS = new ArrayDeque<>();
        Queue<int[]> queueW = new ArrayDeque<>();
        for(int i = 0; i < wList.size(); i++) {
            queueW.offer(wList.get(i));
            visited[wList.get(i)[0]][wList.get(i)[1]] = true;

        }
        queueS.offer(S);
        visited[S[0]][S[1]] = true;

        int time = 1;

        while(!queueS.isEmpty()) {
            int sizeS = queueS.size();
            int sizeW = queueW.size();

//            for(int i = 0; i < R; i++) {
//                System.out.println(Arrays.toString(map[i]));
//            }
//            System.out.println();
//
//            for(int i = 0; i < R; i++) {
//                System.out.println(Arrays.toString(visited[i]));
//            }
//            System.out.println();
//

            for(int i = 0; i < sizeW; i++) {
                int[] tmp = queueW.poll();
                for(int j = 0; j < 4; j++) {
                    int nr = tmp[0] + dr[j];
                    int nc = tmp[1] + dc[j];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if (map[nr][nc] == '.' || map[nr][nc] == 'S' ) {
                        map[nr][nc] = '*';
                        queueW.offer(new int[]{nr, nc});
                    }
                }
            }
            for(int i = 0; i < sizeS; i++) {
                int[] tmp = queueS.poll();

                for(int j = 0; j < 4; j++) {
                    int nr = tmp[0] + dr[j];
                    int nc = tmp[1] + dc[j];
                    if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if(!visited[nr][nc] && map[nr][nc] == '.') {
                        visited[nr][nc] = true;
                        queueS.offer(new int[]{nr, nc});
                    } else if(map[nr][nc] == 'D') {
                        answer = time;
                        return;
                    }
                }
            }


            time++;
        }

        answer = -1;
    }

    


}