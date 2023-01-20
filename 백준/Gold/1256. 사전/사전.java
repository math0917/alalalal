

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static long k;
    static ArrayList<Node>[] factorial;
    static StringBuilder sb = new StringBuilder();
    static final char A = 'a';
    static final char Z = 'z';
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());
        factorial = new ArrayList[n + m + 1];
        for (int i = 1; i < n + m + 1; i++) {
            factorial[i] = new ArrayList<>();
        }
        fillFactorial();

//        for (int i = 1; i < n + m + 1; i++) {
//            for (int j = 0; j < i + 1; j++) {
//                System.out.print(factorial[i].get(j).value);
//            }
//            System.out.println();
//        }

        if ((factorial[n + m].get(n).value).compareTo(new BigInteger(String.valueOf(k))) < 0) {

            System.out.println(-1);
            return;
        }
        k -= 1;
        find(n, m);

        System.out.println(sb.toString());

    }

    private static void find(int aCount, int zCount) {
        if (aCount == 0) {
            while (zCount > 0) {
                sb.append(Z);
                zCount --;
            }
            return;
        }

        if (zCount == 0) {
            while (aCount > 0) {
                sb.append(A);
                aCount --;
            }
            return;
        }
//        System.out.println("aCount = " + aCount);
//        System.out.println("zCount = " + zCount);
//        System.out.println(k);
        int ifA = aCount - 1;

        BigInteger cal = factorial[ifA + zCount].get(ifA).value;
//        System.out.println("cal = " + cal);
        if (cal.compareTo(new BigInteger(String.valueOf(k))) > 0) {
            sb.append(A);
            find(ifA, zCount);
        } else{
            k -= cal.intValue();
            sb.append(Z);
            find(aCount, zCount - 1);
        }

    }


    private static void fillFactorial() {

        for (int i = 1; i < n + m + 1; i++) {
            factorial[i].add(new Node(0, i, new BigInteger("1")));

            for (int j = 1; j < i ; j++) {
                factorial[i].add(new Node(j, i-j, factorial[i-1].get(j-1).value.add(factorial[i-1].get(j).value)));
            }
            factorial[i].add(new Node(i, 0, new BigInteger("1")));
        }
    }
    static class Node{
        int x;
        int y;
        BigInteger value;

        public Node(int x, int y, BigInteger value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
