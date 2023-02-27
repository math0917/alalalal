import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static boolean[][] scarf;
	static boolean[][] visited;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int result = 0;
	/*
	 * 흰색벽을 돌아다니며 색칠된 벽을 발견하면 result를 늘려가는 방식
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		scarf = new boolean[102][102];
		for (int p = 0; p < n; p++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) + 1;
			int y = Integer.parseInt(st.nextToken()) + 1;
//			0,0 90,90 과 같은점을 탐색하기 위해 길이를 1씩 늘려줬습니다.
			for (int i = 0; i< 10; i++) {
				for (int j = 0;j < 10;j ++) {
					scarf[x+i][y+j] = true;
				}
			}
		}
//		
//		방문을 위한 visited -> 칠해지지 않은 애들 기준으로 양방향에 칠해진 곳이 있는지 확인하는 코드 작성
		visited = new boolean[102][102];
		for (int i = 0;i < 102; i++) {
			for (int j =0;j < 102;j ++) {
//				만약 흰색이면서 방문하지 않았으면
				if (scarf[i][j] == false && !visited[i][j]) {
					
					Queue<int[]> queue = new ArrayDeque<>();
					queue.add(new int[] {i,j});
					visited[i][j] = true;
//					방문을 했다고 표시하고  bfs탐색 시작
					while (!queue.isEmpty()) {
						int[] thisTurn = queue.poll();
						int thisRow = thisTurn[0];
						int thisCol = thisTurn[1];

//						지금 점 기준으로 4방을 탐색하고
						for (int k = 0;k < 4;k++) {
							int row = thisRow + dx[k];
							int col = thisCol + dy[k];
							if (0 <= row && row < 102 && 0 <= col && col < 102) {
//								만약 scarf로칠해져 있다면 얘는 변이 한개 닿아있는거니까 + 1
								if (scarf[row][col]) {
//								
									result += 1;
								} else {
//									만약 방문하지 않았으면서 흰색이면 방문했다고 표시하고 다시 큐에 넣기
									if (!visited[row][col]) {
										visited[row][col] = true;
										queue.add(new int[] {row,col});
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println(result);

		
		
	}
}