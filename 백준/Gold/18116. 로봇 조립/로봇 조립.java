
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int n;
	public static int maxCount = 1000001;
	public static int[] parent;
	public static int[] count;
    public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		parent = new int[maxCount];
		count = new int[maxCount];
		
		for (int i = 1; i < maxCount;i++) {
			parent[i] = i;
			count[i] = 1;
		}
		for (int i = 0; i< n; i++) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().equals("I")) {
				int fr = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				union(fr, to);
				
			} else {
                sb.append(count[find(Integer.parseInt(st.nextToken()))]);
                sb.append("\n");
			}
		}
        System.out.print(sb.toString());
		
	}

	public static int find(int num) {
		if (parent[num] == num) {
			return num;
		} 
		parent[num] = find(parent[num]);
		return parent[num];
	}
	public static void union(int num1, int num2) {
		int par1 = find(num1);
		int par2 = find(num2);
		if (par1 != par2) {
			if (par1 < par2) {
				count[par1] += count[par2];
				parent[par2] = par1;
			} else {
				count[par2] += count[par1];
				parent[par1] = par2;
			}
		}
	}
	
	
	
}
