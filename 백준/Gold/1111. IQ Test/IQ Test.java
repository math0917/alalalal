
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] values;
    static int[] minusValues;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        if (n == 1) {
            System.out.println('A');
            System.exit(0);
        }
        values = new int[n];
        minusValues = new int[n - 1];
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n; i++) {
            minusValues[i - 1] = values[i] - values[i - 1];
        }

        if (minusValues.length == 1) {
            if (minusValues[0] == 0) {
                System.out.println(values[0]);
            } else {
                System.out.println('A');
            }
            System.exit(0);
        }

        int multiply=Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            try {
                if (minusValues[i + 1] % minusValues[i] == 0) {
                    if (multiply == Integer.MAX_VALUE) {
                        multiply = minusValues[i + 1] / minusValues[i];
                    } else {
                        if (multiply != minusValues[i + 1] / minusValues[i]) {
                            System.out.println('B');
                            System.exit(0);
                        }
                    }


                } else {
                    System.out.println('B');
                    System.exit(0);
                }
            } catch (Exception e) {

                for (int j = 1; j < n - 1; j++) {
                    if (minusValues[j] != 0) {
                        System.out.println('B');
                        System.exit(0);
                    }
                }
                multiply = 1;
            }

        }
        System.out.println(values[n-1] + minusValues[n-2] * multiply);


    }
}
