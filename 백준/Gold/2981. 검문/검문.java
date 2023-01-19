

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static Queue<Integer> queue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        queue = new ArrayDeque<>();
//        첫번째 값 기준으로 마이너스 하기
        int standard = Integer.parseInt(br.readLine());
        for (int i = 1; i < n; i++) {
            queue.add(Math.abs(Integer.parseInt(br.readLine()) - standard));
        }

        while (true) {
//            만약 한개만 남으면 그거의 공약수들을 프린트
            if (queue.size() == 1) {
                divisor(queue.poll());
                break;
            }
//            만약 두개 이상이면 그거의 최대공약수를 계속 queue에 넣기
            int num1 = queue.poll();
            int num2 = queue.poll();
            queue.add(commonDivisor(num1, num2));
        }


    }

    private static int commonDivisor(int num1, int num2) {
        int smallerValue = Math.min(num1, num2);
        int biggerValue = Math.max(num1, num2);

        int value = biggerValue % smallerValue;
        if (value == 0) {
            return smallerValue;
        } else{
            return commonDivisor(smallerValue, value);
        }
    }

    private static void divisor(int value) {
        for (int i = 2; i <= value; i++) {
            if (value % i == 0) {
                System.out.printf("%d ", i);
            }
        }
    }


}
