
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int testCase, n;
    static HashMap<String, String> parent;
    static HashMap<String, Integer> count;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {
            n = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            count = new HashMap<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();
                if (!parent.containsKey(friend1)) {
                    parent.put(friend1, friend1);
                    count.put(friend1, 1);
                }
                if (!parent.containsKey(friend2)) {
                    parent.put(friend2, friend2);
                    count.put(friend2, 1);
                }

                if (!find(friend1).equals(find(friend2))) {
                    union(friend1, friend2);

                } else {
                    sb.append(count.get(find(friend1))).append('\n');
                }
//                for (String key : parent.keySet()) {
//                    System.out.println(key + " " +parent.get(key));
//                }

            }
        }
        System.out.print(sb);

    }

    private static void union(String friend1, String friend2) {
        String parent1 = find(friend1);
        String parent2 = find(friend2);

        if (parent1.compareTo(parent2) < 0) {
            parent.put(parent2, parent1);
            count.put(parent1, count.get(parent2) + count.get(parent1));
            sb.append(count.get(parent1)).append('\n');
        } else {
            parent.put(parent1, parent2);
            count.put(parent2, count.get(parent1) + count.get(parent2));
            sb.append(count.get(parent2)).append('\n');

        }
    }

    private static String find(String name) {
        if (parent.get(name).equals(name)) {
            return name;
        }
        parent.put(name, find(parent.get(name)));
        return find(parent.get(name));
    }
}
