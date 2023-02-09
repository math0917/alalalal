

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int s, p;
    static char[] message;
    static int[] dna;

    static Map<Character, Integer> map;
    static int[] everSince;
    static int result= 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        message = br.readLine().toCharArray();

        st = new StringTokenizer(br.readLine());

        dna = new int[4];
        for (int i = 0; i < 4; i++) {
            dna[i] = Integer.parseInt(st.nextToken());
        }
        everSince = new int[4];
        int startIdx = 0;
        int finishIdx = p-1;
        map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);
        for (int i = startIdx; i <= finishIdx; i++) {
            everSince[map.get(message[i])] += 1;
        }

        for (;;) {
            if (check()) {
                result += 1;
            }
            if (finishIdx < s - 1) {
                everSince[map.get(message[startIdx++])] -= 1;
                everSince[map.get(message[++finishIdx])] += 1;
            } else {
                break;
            }
        }
        System.out.println(result);


    }

    private static boolean check() {
        for (int i = 0; i < 4; i++) {
            if (dna[i] > everSince[i]) {
                return false;
            }
        }
        return true;
    }
}
