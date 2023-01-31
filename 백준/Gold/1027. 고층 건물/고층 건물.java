

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] building;
    static int result = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        input();
        findBuilding();
        System.out.println(result);
    }

    private static void findBuilding() {
        for (int i = 0; i < n; i++) {
            result = Math.max(findLeft(i) + findRight(i), result);
        }
    }

    private static int findRight(int index) {
        int value = 0;
        int compareIdx = index + 1;
        double maxA = Long.MIN_VALUE;
        double[] x1 = {index, building[index]};
        while (compareIdx < n) {
            double thisTurnA = findA(x1, new double[]{compareIdx, building[compareIdx]});
            if (thisTurnA > maxA) {
                maxA = thisTurnA;
                value += 1;
            }
            compareIdx ++;
        }
        return value;
    }

    private static int findLeft(int index) {
        int value = 0;
        int compareIdx = index - 1;
        double minA = Long.MAX_VALUE;
        double[] x1 = {index, building[index]};
        while (compareIdx >= 0) {
            double thisTurnA = findA(x1, new double[]{compareIdx, building[compareIdx]});
            if (thisTurnA < minA) {
                minA = thisTurnA;
                value += 1;
            }
            compareIdx --;
        }
        return value;


    }

    private static double findA(double[] x1, double[] x2) {
        return (x1[1] - x2[1]) / (x1[0] - x2[0]);
    }


    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        building = new int[n];

        for (int i = 0; i < n; i++) {
            int token = Integer.parseInt(st.nextToken());
            building[i] = token;
        }
    }
}
