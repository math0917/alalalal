
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static String aim;
    static StringBuilder sb;
    static Map<Integer, Integer> mixMap;
    static Set<String> visited;
    static Queue<Map1> queue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(st.nextToken());
        }
        aim = sb.toString();
        st = new StringTokenizer(br.readLine());
        mixMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mixMap.put(i, Integer.parseInt(st.nextToken()));
        }
        sb = new StringBuilder();

        for (int i = 0; i < n; i+= 3) {
            sb.append("012");
        }
        visited = new HashSet<>();
        queue = new ArrayDeque<>();
        queue.add(new Map1(sb.toString(), 0));
        visited.add(sb.toString());

        while (!queue.isEmpty()) {
            Map1 thisTurnMap1 = queue.poll();
            if (thisTurnMap1.text.equals(aim)) {
                System.out.println(thisTurnMap1.length);
                System.exit(0);
            }
            sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(thisTurnMap1.text.charAt(mixMap.get(i)));
            }
            if (!visited.contains(sb.toString())) {
                visited.add(sb.toString());
                queue.add(new Map1(sb.toString(), thisTurnMap1.length + 1));
            }
        }
        System.out.println(-1);
    }
}
class Map1 {
    String text;
    int length;

    public Map1(String text, int length) {
        this.text = text;
        this.length = length;
    }
}