


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws Exception{
        input();
        logic();

    }

    private static void logic() {
        int sum = 0;
        while (!pq.isEmpty()) {
            int poll = pq.poll();
            if (sum + 1 >= poll) {
                sum += poll;
            } else {
                System.out.println(sum + 1);
                return;
            }
        }
        System.out.println(sum + 1);


    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(st.nextToken()));
        }

    }
}
