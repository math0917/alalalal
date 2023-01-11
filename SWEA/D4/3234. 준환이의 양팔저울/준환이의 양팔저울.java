

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    public static int[] weight;
    public static BufferedReader br;
    public static StringTokenizer st;
    public static StringBuilder sb;
    public static int leftPart = 0;
    public static int rightPart = 0;
    public static int result;
    public static int[] numbers;
    public static int N;

    public static void main(String[] args) throws Exception {



        br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t < testCase + 1; t++) {
            N = Integer.parseInt(br.readLine());
            weight = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weight[i] = Integer.parseInt(st.nextToken());
            }
            result = 0;
            numbers = new int[N];
            permutations(0, 0);
            sb = new StringBuilder();
            sb.append("#").append(t).append(" ").append(result);
            System.out.println(sb.toString());
        }
    }

    private static void permutations(int bit, int index) {
        if (index == N) {
            leftPart = 0;
            rightPart = 0;
            count(numbers, 0);
        }
        for (int i = 0; i < N; i++) {
            if ((bit & (1 << i)) != 0) {
                continue;
            }
            numbers[index] = weight[i];
            permutations(bit | (1 << i), index + 1);
        }
    }

    private static void count(int[] numbers, int index) {
        if (index == N) {
            result += 1;
            return;
        }
        if (numbers[index] + rightPart <= leftPart) {
            rightPart += numbers[index];
            count(numbers, index + 1);
            rightPart -= numbers[index];
        }
        leftPart += numbers[index];
        count(numbers, index + 1);
        leftPart -= numbers[index];
    }

}