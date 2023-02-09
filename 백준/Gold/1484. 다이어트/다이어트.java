

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static List<Integer> result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        result = new ArrayList<>();

        for (int i = 1; i * i < n; i++) {
            if (n % i == 0) {
                int j = n / i;
                if (((i + j) % 2) == 0 && ((j - i) % 2) == 0) {
//                    System.out.println(i + " " + j);
                    result.add((i + j) / 2);

                }
            }

        }
        if (result.size() == 0) {
            System.out.println(-1);
        } else {
            Collections.sort(result);


            for (int a : result) System.out.println(a);
        }


    }
}


