import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    static int testCase, n, k;
    static Set<Long> set;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            String line = br.readLine();
            set = new HashSet<>();
            for (int i = 0; i < n / 4; i++) {
                int count = 0;
                for (int j = 0; count++ < 4; j += n/4) {
                    int startIdx = (i + j) % n;
                    long value = 0;
                    for (int k = 0; k < n / 4; k++) {
                        value += (long) Math.pow(16, n / 4 - k - 1) * getValue(line.charAt((startIdx + k) % n));
                    }
                    set.add(value);
                }
            }
            List<Long> collect = set.stream().collect(Collectors.toList());
            collect.sort(Collections.reverseOrder());
            System.out.println("#" + t+" "+collect.get(k - 1));
        }

    }
    private static int getValue(char a){
        return '0' <= a && a <= '9' ? a - '0' : a - 'A' + 10;
    }
}