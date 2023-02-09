

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        int start =(int) Math.pow(10, n - 1);
        int weight;

    
        for (int i = 2*start; i < 8*start; i++) {
            weight = start;
            boolean isAmazing = true;
            while (weight >= 1) {
                if (!isPrime(i / weight)) {
                    isAmazing = false;
                    break;
                }
                weight /= 10;
            }

            if (isAmazing) {
                bw.write(i + "\n");
            }
        }
        bw.flush();
        bw.close();

    }



    private static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
