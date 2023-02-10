
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static String line;
    static Set<Character> set;
    static Stack<Character> stack;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        set = new HashSet<>();
        set.add('[');
        set.add('<');
        set.add('(');
        set.add('{');
        for (int t = 1; t <= 10; t++) {
            n = Integer.parseInt(br.readLine());
            line = br.readLine();
            stack = new Stack<>();
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (set.contains(line.charAt(i))) {
                    stack.add(line.charAt(i));
                } else {
                    Character pop = stack.pop();
                    if (line.charAt(i) - pop != 2 && line.charAt(i) - pop != 1) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                System.out.println("#" + t + " 1");
            } else {
                System.out.println("#" + t + " 0");

            }
        }
    }
}
