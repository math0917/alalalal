

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution{
	
	public static int[] par;
	public static int[] depth;
	public static int[] childCount;
	public static Map<Integer, List<Integer>> graph;
	public static int v;
	public static int e;
	public static int v1;
	public static int v2;
	public static int lca;
	public static int cc;
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t < testCase + 1; t++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			graph = new HashMap<>();
			par = new int [v+1];
			depth = new int [v+1];
			childCount = new int [v+1];
			for (int i = 0; i < e; i++) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				if (!(graph.containsKey(parent))){
					graph.put(parent, new ArrayList<>());
				}
				graph.get(parent).add(child);
				
				if (!(graph.containsKey(child))){
					graph.put(child, new ArrayList<>());
				}
				graph.get(child).add(parent);
				par[child] = parent;
			}
			depthFinder(1,1);
			lca = parentFinder(v1, v2);
			cc = childCount[lca] + 1;
			System.out.printf("#%d %d %d\n", t, lca, cc);
			
		}
		
		
	}
	static int depthFinder(int i, int level) {
		depth[i] = level;
		int child = 0;
		List<Integer> lst = graph.get(i);
		for (int n : lst) {
			if (depth[n] == 0) {
				child += 1;
				child += depthFinder(n, level+1);
			}	
		}
		childCount[i] = child;
		return child;
		
	}
	
	static int parentFinder(int leftPart, int rightPart) {
		if (leftPart == rightPart) {
			return rightPart;
		}
		if (depth[leftPart] == depth[rightPart]) {
			return parentFinder(par[leftPart], par[rightPart]);
		} else if (depth[leftPart] < depth[rightPart]){
			return parentFinder(leftPart, par[rightPart]);
		} else {
			return parentFinder(par[leftPart], rightPart);
		}
	}
	
}
