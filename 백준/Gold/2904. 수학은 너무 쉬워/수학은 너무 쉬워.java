

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] numbers;
    static boolean[] primes;
    static int result = 1;
    static int countResult = 0;
    static Queue<Integer> queue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        st = new StringTokenizer(br.readLine());
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            maxValue = Math.max(maxValue, numbers[i]);
        }
        primes = new boolean[maxValue + 1];
        for (int i = 2; i < primes.length; i++) {
            if (!primes[i]) {
                for (int j = i; j < primes.length; j += i) {

                    primes[j] = true;
                }
                queue = new ArrayDeque<>();
                int mulCount = 0;
                for (int a : numbers) {
                    int count = 0;
                    while (true) {
                        if ((a % i) == 0) {
                            count += 1;
                            a /= i;
                        } else {
                            break;
                        }
                    }
                    mulCount += count;

                    queue.add(count);
                }

                if (mulCount / numbers.length >= 1) {
                    result *= (int) Math.pow(i,  mulCount / numbers.length);

                    while (!queue.isEmpty()) {
                        Integer poll = queue.poll();

                        if (poll < mulCount / numbers.length) {
                            countResult += mulCount / numbers.length - poll;
                        }
                    }
                }
            }
        }
        System.out.println(result+ " " + countResult);
    }
}
