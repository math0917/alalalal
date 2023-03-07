import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String line = br.readLine();
        String[] split = line.split("-");
        Queue<Integer> queue = new ArrayDeque<>();
//        System.out.println(Arrays.toString(split));
        for (String a : split) {
            int val = 0;
            String[] split1 = a.split("\\+");
            for (String b : split1) {
                val += Integer.parseInt(b);
            }
            if (queue.isEmpty()) {
                queue.add(val);
            } else {
                queue.add(queue.poll() - val);
            }
        }
        System.out.println(queue.poll());



    }
}