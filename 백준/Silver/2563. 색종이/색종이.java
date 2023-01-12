import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static boolean[][] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		visited = new boolean[100][100];
		for (int t = 0; t < n; t++) {
			st = new StringTokenizer(br.readLine());
			int leftLength = Integer.parseInt(st.nextToken());
			int rightLength = Integer.parseInt(st.nextToken());
			for (int i = 0; i < 10; i++) {
				for (int j = 0 ;j < 10;j++) {
					visited[leftLength+i][rightLength+j] = true;
				}
				
			}
		}
		int result = 0;
		for (int i = 0;i < 100;i++) {
			for (int j =0;j<100;j++) {
				if (visited[i][j]) {
					result += 1;
				}
			}
		}
		System.out.print(result);
	}
}
