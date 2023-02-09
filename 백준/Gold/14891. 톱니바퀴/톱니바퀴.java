

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] wheel;
    static int left = 1 << 6;

    static int right = 1 << 2;

    static int m;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        wheel = new int[4];

        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            int value = 0;
            for (int j = 0; j < 8; j++) {
                if (line.charAt(j) == '1') {
                    value |= (1 << (j));
                }
            }
            wheel[i] = value;
        }

        m = Integer.parseInt(br.readLine());

        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine());
            int wh = Integer.parseInt(st.nextToken()) -1 ;
            int dir = Integer.parseInt(st.nextToken());
            changeWheel(wh, dir, true, true);

        }
        int result = 0;


        for (int i = 0; i < 4; i++) {
            if (wheel[i] % 2 == 1) {
                result |= (1 << i);
            }
//            System.out.println(wheel[i]);

        }
        System.out.println(result);

    }

    private static void changeWheel(int wh, int dir, boolean leftGo, boolean rightGo) {
//        System.out.println(wh+"뒤집기");
        if (leftGo) {
            if (wh > 0) {
//                System.out.println("left "+wheel[wh]+" "+ (wheel[wh] & left)+ " "+ wheel[wh-1]+" " +(wheel[wh-1]& right));
                boolean leftPart = (wheel[wh] & left) == 0;
                boolean rightPart = (wheel[wh - 1] & right) == 0;
                if (rightPart ^ leftPart) {
                    changeWheel(wh - 1, dir * -1, true, false);
                }
            }
        }
        if (rightGo) {
            if (wh < 3) {
//                System.out.println("right"+wheel[wh]+" "+ (wheel[wh] & right)+ " "+wheel[wh+1]+ " "+(wheel[wh+1]& left));
                boolean rightPart = (wheel[wh] & right) == 0;
                boolean leftPart = (wheel[wh + 1] & left) == 0;
                if (rightPart ^ leftPart) {
                    changeWheel(wh + 1, dir * -1, false, true);
                }
            }
        }

        if (dir == -1) {
            shiftRight(wh);
        } else {
            shiftLeft(wh);
        }


    }

    private static void shiftLeft(int wh) {
        int whe = wheel[wh];
        int wheLeft = whe & (1 << 7);
        whe &= 127;
        whe <<= 1;
        if (wheLeft != 0) {
            whe += 1;
        }
        wheel[wh] = whe;

    }

    private static void shiftRight(int wh) {

        int whe = wheel[wh];
        int wheRight = whe % 2;
        whe >>= 1;
        if (wheRight != 0) {
            whe |= (1 << 7);
        }
        wheel[wh] = whe;

    }
}
