import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[][][] tomato;
	public static int []dx = new int[] {-1,0,1,0,0,0};
	public static int []dy = new int[] {0,1,0,-1,0,0};
	public static int []dz = new int[] {0,0,0,0,1,-1};
	public static Queue<int[]> queue;
	public static int result;
	public static boolean flag = true;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()),
				n = Integer.parseInt(st.nextToken()),
				h = Integer.parseInt(st.nextToken());
		tomato = new int[h][n][m];
		queue = new LinkedList<>();
		for (int k = 0; k < h; k++) {
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i< m;i++) {
					tomato[k][j][i] = Integer.parseInt(st.nextToken());
					if (tomato[k][j][i] == 1) {
						queue.add(new int[] {k,j,i,0});
					}
				}
			}
		}
		
		while (!(queue.isEmpty())){
			int[] thisTurn = queue.poll();
			int thisRow = thisTurn[1], thisCol = thisTurn[2], thisHeight = thisTurn[0], thisLength = thisTurn[3];
			result = thisLength;
			for (int i = 0; i< 6; i++) {
				int row = thisRow + dx[i], col = thisCol + dy[i], height = thisHeight + dz[i];
				
				if ( 0 <= row && row < n && 0 <= col && col < m && 0 <= height && height < h) {
						if (tomato[height][row][col] == 0) {
							tomato[height][row][col] = 1;
							queue.add(new int[] {height, row, col, thisLength + 1});
						} 
							
						
				}
			}
		}
		
		for (int i = 0; i< h; i++) {
			for (int j = 0; j < n; j++) {
				for(int k = 0; k < m; k++) {
					if (tomato[i][j][k] == 0) {	
						flag = false;
					}
				}
			}
		}
		if (flag) {
			System.out.print(result);
		} else {
			System.out.print(-1);
		}
		
		
		
		
		
	}
}
