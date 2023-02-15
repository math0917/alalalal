
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static String line;
    static Set<Character> visited;
    static int result = Integer.MIN_VALUE;
    static Map<Character, int[]> map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        line = br.readLine();
        int s = 0;
        int f = 0;
        visited = new HashSet<>();
        map = new HashMap<>();
        char init = '0';
        while (f < line.length()) {

//            System.out.println(s+" " + f + " " + line.charAt(f) + " " +visited.size());


            char cha = line.charAt(f);
            if (visited.size() == 0) {
                visited.add(cha);
                map.put(cha, new int[]{f, f});
                init = cha;
                f++;
            } else {
                if (!visited.contains(cha)) {
                    if (visited.size() < n) {
                        map.put(cha, new int[]{f, f});
                        visited.add(cha);
                        f++;
                    } else {
                        result = Math.max(result, f - s);
                        visited = new HashSet<>();
                        s = map.get(init)[0] + 1;
                        f = map.get(init)[0] + 1;

                    }
                } else {
                    map.get(cha)[1] = f;
                    f++;
                }
            }
        }
        result = Math.max(result, f - s);

        System.out.println(result);
    }
}
