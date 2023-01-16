
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static int n;
	public static int[] parent;
	public static Stack<Integer> stack;
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		parent = new int[n+1];
		for (int i = 1; i< n+ 1; i++) {
			parent[i] = i;
		}
		stack = new Stack<>();
		for (int i = 0; i < n-2; i++) {
			st = new StringTokenizer(br.readLine());
			int fr = Integer.parseInt(st.nextToken()),
					to = Integer.parseInt(st.nextToken());
			union(fr,to);
		}
		
		
		
		for (int i = 1;i < n+ 1;i++) {
			if (stack.isEmpty()) {
				stack.add(parent[i]);
			} else {
				int par = find(i);
				if (par != stack.peek()) {
					System.out.printf("%d %d", par, stack.peek());
					break;
				}
			}
		}
		
		
		
	}
	public static int find(int num) {
		if (parent[num] == num) {
			return num;
		}
		parent[num] = find(parent[num]);
		return parent[num];
	}
	
	public static void union(int num1, int num2) {
		int parent1 = find(num1);
		int parent2 = find(num2);
		if (parent1 != parent2) {
			if (parent1 < parent2) {
				parent[parent2] = parent1;
			} else {
				parent[parent1] = parent2;
			}
			
		}
	}

}
