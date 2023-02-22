import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static int testCase;
    static int r, c;
    static char[][] map;
    static int proc;
    static int tankX, tankY, tankDir;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Map<Character, Integer> direction;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());
        direction = new HashMap<>();
        direction.put('v', 2);
        direction.put('<', 3);
        direction.put('>', 1);
        direction.put('^', 0);
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            map = new char[r][c];
            for (int i = 0; i < r; i++) {
                String line = br.readLine();
                for (int j = 0; j < c; j++) {
                    map[i][j] = line.charAt(j);
                    if (direction.containsKey(map[i][j])) {
                        tankX = i;
                        tankY = j;
                        tankDir = direction.get(map[i][j]);
                    }


                }
            }

            proc = Integer.parseInt(br.readLine());
            String line = br.readLine();
            for (int i = 0; i < proc; i++) {
                procedure(line.charAt(i));
            }
            System.out.print("#"+t+" ");
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
            
        }
    }

    private static void procedure(char pro) {
        switch (pro) {
            case'S':
                int idx = 1;
                while (true) {
                    int phoX = tankX + idx * dx[tankDir];
                    int phoY = tankY + idx * dy[tankDir];
                    if (0 <= phoX && phoX < r && 0 <= phoY && phoY < c) {
                        if (map[phoX][phoY] == '*') {
                            map[phoX][phoY] = '.';
                            break;
                        } else if (map[phoX][phoY] == '#') {
                            break;
                        } else {
                            idx += 1;
                        }
                    } else {
                        break;
                    }

                }
                break;
            case 'U':
                tankDir = 0;
                map[tankX][tankY] = '^';
                int row = tankX + dx[0];
                int col = tankY + dy[0];
                if (0 <= row && row < r && 0 <= col && col < c) {
                    if (map[row][col] == '.') {
                        map[tankX][tankY] = '.';
                        map[row][col] = '^';
                        tankX = row;
                        tankY = col;
                    }
                }
                break;
            case 'R':
                tankDir = 1;
                map[tankX][tankY] = '>';
                row = tankX + dx[1];
                col = tankY + dy[1];
                if (0 <= row && row < r && 0 <= col && col < c) {
                    if (map[row][col] == '.') {
                        map[tankX][tankY] = '.';
                        map[row][col] = '>';
                        tankX = row;
                        tankY = col;
                    }
                }
                break;
            case 'D':
                tankDir = 2;
                map[tankX][tankY] = 'v';
                row = tankX + dx[2];
                col = tankY + dy[2];
                if (0 <= row && row < r && 0 <= col && col < c) {
                    if (map[row][col] == '.') {
                        map[tankX][tankY] = '.';
                        map[row][col] = 'v';
                        tankX = row;
                        tankY = col;
                    }
                }
                break;
            case 'L':
                tankDir = 3;
                map[tankX][tankY] = '<';
                row = tankX + dx[3];
                col = tankY + dy[3];
                if (0 <= row && row < r && 0 <= col && col < c) {
                    if (map[row][col] == '.') {
                        map[tankX][tankY] = '.';
                        map[row][col] = '<';
                        tankX = row;
                        tankY = col;
                    }
                }
                break;
        }
    }
}