import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int r, c, m;
    static Cutty[][] shark;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int sharkWeight = 0;
    static Map<Integer, Cutty> map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            if (d >= 2) {
                map.put(c * row + col, new Cutty(s % ((c - 1) * 2),d ,z ));
            } else {
                map.put(c * row + col, new Cutty(s % ((r - 1) * 2),d ,z ));
            }
        }

        for (int j = 0; j < c; j++) {
            for (int i = 0; i < r; i++) {
                if (map.containsKey(c * i + j)) {
                    sharkWeight += map.get(c * i + j).weight;
                    map.remove(c * i + j);
                    break;
                }
            }
            Map<Integer, Cutty> newMap = new HashMap<>();
            for (int rowCol : map.keySet()) {
                Cutty cutty = map.get(rowCol);
                int dir = cutty.dir;
                int weight = cutty.weight;
                int vel = cutty.vel;
                int[] result = go(rowCol/c, rowCol % c, dir, vel);
                if (newMap.containsKey(result[0] * c + result[1])) {
                    if (newMap.get(result[0] * c + result[1]).weight < weight) {
                        Cutty c = newMap.get((result[0] * Main.c) + result[1]);
                        c.weight = weight;
                        c.dir = result[2];
                        c.vel = vel;
                    }
                } else {
                    newMap.put(result[0] * c + result[1], new Cutty(vel, result[2], weight));
                }
            }
            map = newMap;
        }
        System.out.println(sharkWeight);
    }

    private static int[] go(int thisRow, int thisCol, int dir, int vel) {
        for (int i = 0; i < vel; i++) {
            int row = thisRow + dx[dir];
            int col = thisCol + dy[dir];
            if (0 <= row && row < r && 0 <= col && col < c) {
                thisRow = row;
                thisCol = col;
            } else {
                dir = nextDir(dir);
                i--;
            }
        }
        return new int[]{thisRow, thisCol, dir};
    }

    private static int nextDir(int dir) {
        if (dir * 2 < 4) {
            return (dir + 1) % 2;
        } else {
            return 2 + (dir + 1) % 2;
        }
    }
}

class Cutty{
    int vel;
    int dir;
    int weight;

    public Cutty(int vel, int dir, int weight) {

        this.vel = vel;
        this.dir = dir;
        this.weight = weight;
    }

}