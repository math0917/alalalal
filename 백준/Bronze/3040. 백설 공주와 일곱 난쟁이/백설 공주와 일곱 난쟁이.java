
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] princess;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        princess = new int[9];

        for (int i = 0; i < 9; i++) {
            princess[i] = Integer.parseInt(br.readLine());
        }

        index(0, 0, 0);
    }

    private static void index(int index, int start, int bit) {

        if (index == 7) {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                if ((bit & (1 << i)) != 0) {
                    sum += princess[i];
                }
            }

            if (sum == 100) {
                for (int i = 0; i < 9; i++) {
                    if ((bit & (1 << i)) != 0) {
                        System.out.println(princess[i]);
                    }
                }
            }
            return;
        }

        for (int i = start; i < 9; i++) {
            if ((bit | (1 << i)) != 0) {
                index(index + 1, i + 1, bit | (1 << i));
            }
        }
    }
}
