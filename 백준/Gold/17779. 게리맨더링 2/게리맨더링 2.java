
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] election;
    static int x, y;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] cover;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        election = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                election[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x = i;
                y = j;
                int possibleD1 = j;
                int possibleD2 = n - j - 1;
                for (int k = 1; k <= possibleD1; k++) {
                    for (int p = 1; p <= possibleD2; p++) {
                        if (x + p + k < n) {
                            /*
                            *   x, y는 가장위의점
                            *   k는 왼쪽으로 뻗치는 정도, p는 오른쪽으로 뻗치는 정도
                             */
                            cover = new int[n][n];
                            int thisRow = x;
                            int thisCol = y;
                            for (int t = 0; t < k; t++) {
                                cover[thisRow++][thisCol--] = 5;
                            }
                            for (int t = 0; t < p; t++) {
                                cover[thisRow++][thisCol++]= 5;
                            }

                            for (int t = 0; t < k; t++) {
                                cover[thisRow--][thisCol++] = 5;
                            }
                            for (int t = 0; t < p; t++) {
                                cover[thisRow--][thisCol--]= 5;
                            }

                            int leftUpperRow = x + k;
                            int leftUpperCol = y + 1;
                            for (int row = 0;row < leftUpperRow; row++){
                                for (int col = 0; col < leftUpperCol; col++) {
                                    if (row + col < x + y) {
                                        cover[row][col] = 1;
                                    }
                                }
                            }

                            int rightUpperCol = y + 1;
                            int rightUpperRow = x + p + 1;
                            for (int col = rightUpperCol; col < n; col++) {
                                for (int row = 0; row < rightUpperRow; row++) {
                                    int diffRow = row - x;
                                    int diffCol = col - y;
                                    if (diffRow < diffCol) {
                                        cover[row][col] = 2;
                                    }
                                }
                            }

                            int rightLowerRow = x + p;
                            int rightLowerCol = y + p - k;
                            int rr = x + p;
                            int cc = y + p;

                            for (int row = n-1;row > rightLowerRow; row--){
                                for (int col = rightLowerCol; col < n; col++) {
                                    if (row + col > rr + cc) {
                                        cover[row][col] = 4;
                                    }
                                }
                            }

                            int leftLowerCol = y-k+p;
                            int leftLowerRow = x + k;
                            for (int col = 0; col < leftLowerCol; col++) {
                                for (int row = leftLowerRow; row < n; row++) {
                                    int diffRow = row - (x + k);
                                    int diffCol = col - (y - k);
                                    if (diffRow > diffCol) {
                                        cover[row][col] = 3;
                                    }
                                }
                            }
                            int[] elec = new int[5];
                            for (int row = 0; row < n; row++) {
                                for (int col = 0; col < n; col++) {
                                    switch (cover[row][col]) {
                                        case 0: case 5:
                                            elec[4] += election[row][col];
                                            break;
                                        case 1:
                                            elec[0] += election[row][col];
                                            break;
                                        case 2:
                                            elec[1] += election[row][col];
                                            break;
                                        case 3:
                                            elec[2] += election[row][col];
                                            break;
                                        case 4:
                                            elec[3] += election[row][col];
                                    }
                                }
                            }
//                            System.out.println(Arrays.stream(elec).max().getAsInt() + " " + Arrays.stream(elec).min().getAsInt()+" " + i +" "+j+" "+k+" "+p);
//                            for (int row = 0; row < n; row++) {
//                                for (int col = 0; col < n; col++) {
//                                    System.out.print(cover[row][col] + " ");
//                                }
//                                System.out.println();
//                            }
//                            System.out.println();

                            result = Math.min(result, Arrays.stream(elec).max().getAsInt() - Arrays.stream(elec).min().getAsInt());
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }
}
