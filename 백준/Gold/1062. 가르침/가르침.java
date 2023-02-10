

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static char[] alpha;
    static boolean[] visited;
    static Set<Character> result;
    static String[] words;
    static int count = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }
        alpha = new char[26];
        visited = new boolean[26];
        result = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            alpha[i] = (char)('a' + i);
        }
        visited['a'-'a'] = true;
        visited['n'-'a'] = true;
        visited['t'-'a'] = true;
        visited['i'-'a'] = true;
        visited['c'-'a'] = true;
        result.add('a');
        result.add('n');
        result.add('i');
        result.add('t');
        result.add('c');
        if (k < 5) {
            System.out.println(0);

        } else {
            find(0, 0);

            System.out.println(count);

        }



    }

    private static void find(int index, int start) {
        if (index == k - 5) {
            canLearn();
        }
        for (int i = start; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result.add(alpha[i]);
                find(index + 1, i+ 1);
                result.remove(alpha[i]);
                visited[i] = false;
            }
        }
    }

    private static void canLearn() {
        int count1= 0;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < words[i].length(); j++) {
                if (!result.contains(words[i].charAt(j))) {
                    flag = false;
                    break;
                }

            }
//            System.out.println(flag);
            if (flag) {
                count1 += 1;
            }

        }
        count = Math.max(count, count1);

    }
}
