import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] roller;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        roller = new int[n][m];
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                roller[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if (n % 2 == 0 && m % 2 == 0) {
            search();
        } else if (n % 2 == 0 && m % 2 == 1) {
            for (int j = 0; j < m; j++) {
                if (j % 2 == 0) {
                    for (int i = 0; i < n-1; i++) {
                        sb.append('D');
                    }
                } else {
                    for (int i = 0; i < n-1; i++) {
                        sb.append('U');
                    }
                }
                if (j != m - 1) {
                    sb.append('R');
                }
            }
        } else if (n % 2 == 1 && m % 2 == 0) {
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    for (int j = 0; j < m - 1; j++) sb.append('R');
                } else {
                    for (int j = 0; j < m - 1; j++) sb.append('L');
                }
                if (i != n - 1) {
                    sb.append('D');
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    for (int j = 0; j < m - 1; j++) sb.append('R');
                } else {
                    for (int j = 0; j < m - 1; j++) sb.append('L');
                }
                if (i != n - 1) {
                    sb.append('D');
                }
            }
        }
        System.out.println(sb.toString());
    }

    private static void search() {
        int min = Integer.MAX_VALUE;
        int[] loc = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i + j) % 2 == 1) {
                    if (min > roller[i][j]) {
                        min = roller[i][j];
                        loc[0] = i;
                        loc[1] = j;
                    }
                }
            }
        }
        roller[loc[0]][loc[1]] = -1;
        boolean flag = true;
        for (int i = 0; i < n; i += 2) {
            if (i <= loc[0] && i + 1 >= loc[0]) {
                flag = false;

                if (i != 0) {
                    sb.append('D');
                }
                for (int j = 0; j < loc[1]; j++) {
                    if (j % 2 == 0) {
                        sb.append("DR");
                    } else {
                        sb.append("UR");
                    }
                }

                for (int j = loc[1] + 1; j < m; j++) {
                    if (j % 2 == 0) {
                        sb.append("RU");
                    } else {
                        sb.append("RD");
                    }
                }

            } else {
                if (flag) {
                    if (i != 0) {
                        sb.append('D');
                    }
                    for (int j = 0; j < m - 1; j++) {
                        sb.append('R');
                    }
                    sb.append('D');
                    for (int j = 0; j < m - 1; j++) {
                        sb.append('L');
                    }
                } else {
                    if (i != 0) {
                        sb.append('D');
                    }
                    for (int j = 0; j < m - 1; j++) {
                        sb.append('L');
                    }
                    sb.append('D');
                    for (int j = 0; j < m - 1; j++) {
                        sb.append('R');
                    }
                }
            }
        }
    }
}