import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        int rightMost = 0;
        int leftMost = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                rightMost = i;
            }
            if (arr[n - 1 - i] == 1) {
                leftMost = n - 1 - i;
            }
        }
//        System.out.println(rightMost + " "+ leftMost);
        if (rightMost + 1 < n - leftMost) {
            count = rightMost + 1;

            for (int i = 0; i < count; i++) {
                sb.append('L');
            }
        } else {
            count = n - leftMost;
            for (int i = 0; i < count; i++) {
                sb.append('R');
            }
        }


        int leftPart = 0;
        int rightPart = n-1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                if (i != 0) {
                    leftPart = i - 1;

                } else {
                    continue;
                }
                for (int j = i + 1; j < n; j++) {
                    if (arr[j] == 1) {
                        rightPart = j;
                        if (leftPart + 1 < n - rightPart) {
                            if (2 * (leftPart + 1) + n - rightPart < count) {
                                count = 2 * (leftPart + 1) + n - rightPart;
                                sb = new StringBuilder();
                                for (int t = 0; t < leftPart + 1; t++) {
                                    sb.append('L');
                                }
                                for (int t = 0; t < leftPart + 1 + n - rightPart; t++) {
                                    sb.append('R');
                                }
                            }
                        } else {
                            if (2 * (n - rightPart) + leftPart + 1 < count) {
                                count = 2 * (n - rightPart) + leftPart + 1;
                                sb = new StringBuilder();
                                for (int t = 0; t < n - rightPart; t++) {
                                    sb.append('R');
                                }
                                for (int t = 0; t < n - rightPart + leftPart + 1; t++) {
                                    sb.append('L');
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }




//        System.out.println(leftPart + " "+ rightPart);


        System.out.println(count);
        System.out.println(sb);
    }

}