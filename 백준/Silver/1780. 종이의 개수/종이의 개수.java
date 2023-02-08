

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] paper;
    static int[] count;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        paper = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        count = new int[3];

        start(0, 0, n, n);
        for (int a : count) {
            System.out.println(a);
        }
    }

    private static void start(int fromRow, int fromCol, int toRow, int toCol) {
        Set<Integer> thisSet = new HashSet<>();
//        System.out.println(fromRow+" "+ fromCol+" "+ toRow+" "+ toCol);
        boolean flag = true;
        int paperKind = -2;
        for (int i = fromRow; i < toRow && flag; i++) {
            for (int j = fromCol; j < toCol && flag; j++) {
                thisSet.add(paper[i][j]);
                if (thisSet.size() == 2) {
                    flag = false;
                    break;
                }
                paperKind = paper[i][j];
            }
        }

        if (flag) {
            count[paperKind + 1]++;
            return;
        }


        int gap = toRow - fromRow;


        start(fromRow, fromCol, fromRow + gap/3, fromCol + gap/3);
        start(fromRow, fromCol + gap/3, fromRow + gap/3, fromCol + 2 * gap/3);
        start(fromRow, fromCol + 2 * gap / 3 , fromRow + gap /3, toCol);

        start(fromRow+gap/3, fromCol, fromRow +  2* gap/3, fromCol + gap/3);
        start(fromRow+gap/3, fromCol + gap/3, fromRow + 2*gap/3, fromCol + 2 * gap/3);
        start(fromRow+gap/3, fromCol + 2 * gap / 3 , fromRow + 2* gap /3, toCol);

        start(fromRow+ 2 *gap/3, fromCol, toRow, fromCol + gap/3);
        start(fromRow + 2 *gap/3, fromCol + gap/3, toRow, fromCol + 2 * gap/3);
        start(fromRow + 2 *gap/3, fromCol + 2 * gap / 3 , toRow, toCol);



    }
}
