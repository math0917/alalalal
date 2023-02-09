

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int testCase;
    static int m;
    static boolean[] isPrime;
    static List<Integer> prime;
    static int[][] sums;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCase = Integer.parseInt(br.readLine());
        isPrime = new boolean[10000000];
        prime = new ArrayList<>();

        init();
        for (int t = 1; t <= testCase; t++) {
            m = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            sums = new int[m][3];

            for (int i = 0; i < m; i++) {
                int count = Integer.parseInt(st.nextToken());
                int sum1 = 0;

                for (int index = 0; index < count; index++) {
                    sum1 += prime.get(index);
                }
                sums[i] = new int[] {0, count - 1, sum1};
            }

            if (m == 1) {
                while (isPrime[sums[0][2]]) {
                    sums[0][2] += prime.get(++sums[0][1]) - prime.get(sums[0][0]++);
                }
                sb.append("Scenario ").append(t).append(":\n").append(sums[0][2]).append("\n").append("\n");
            } else {
                while (true) {
                    boolean flag = true;
                    Arrays.sort(sums, Comparator.comparing(x -> -x[2]));

                    int sum = sums[0][2];

                    for (int i = 1; i < m; i++) {
                        if (sum != sums[i][2]) {
                            while (sum > sums[i][2]) sums[i][2] += prime.get(++sums[i][1]) - prime.get(sums[i][0]++);
                            flag = false;
                        }
                        sum = Math.max(sum, sums[i][2]);
                    }
                    if (flag && !isPrime[sum]) {
                        sb.append("Scenario ").append(t).append(":\n").append(sum).append("\n").append("\n");

                        break;
                    }
                }
            }




        }
        System.out.println(sb.toString());

    }

    private static void init() {
        for (int i = 2; i < isPrime.length; i++) {
            if (!isPrime[i]) {
                prime.add(i);
                for (int j = 2; i * j < isPrime.length; j++) {
                    isPrime[i*j] = true;
                }
            }
        }

    }
}

