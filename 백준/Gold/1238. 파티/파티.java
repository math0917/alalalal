

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, x;
	static ArrayList<Node>[] graph;
	static int[] distFromFinish;
	static int[] distFromStart;
	static PriorityQueue<Node> pq;
	static boolean[] visited;
	static int result = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n+1];
		for (int i = 1; i < n + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int t = 0; t < m; t++) {
			st = new StringTokenizer(br.readLine());
			int fr = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[fr].add(new Node(to,weight));
		}
		visited = new boolean[n+1];
		pq = new PriorityQueue<>();
		distFromFinish = new int[n+1];
		for (int i = 1; i < n+1; i++) {
			distFromFinish[i] = Integer.MAX_VALUE;
		}
		dijkstra();
		
		for (int i = 1 ;i < n + 1; i++) {
			if (i != x) {
				startDijkstra(i);
			}
			
			
		}
		
		System.out.println(result);
		
		
	}
	
	public static void startDijkstra(int startIdx) {
		visited = new boolean[n+1];
		pq = new PriorityQueue<>();
		distFromStart = new int[n+1];
		for (int i = 1; i < n+1; i++) {
			distFromStart[i] = Integer.MAX_VALUE;
		}
		distFromStart[startIdx] = 0;
		pq.add(new Node(startIdx,0));
		while (!pq.isEmpty()) {
			Node thisTurn = pq.poll();
			if (visited[thisTurn.adjacent]) {
				continue;
			} 
			visited[thisTurn.adjacent] = true;
			for(Node next : graph[thisTurn.adjacent]) {
				if (distFromStart[next.adjacent] > thisTurn.weight + next.weight) {
					distFromStart[next.adjacent] = thisTurn.weight + next.weight;
					pq.add(new Node(next.adjacent, distFromStart[next.adjacent]));
				}
			}
		}
//		System.out.println(distFromStart[x]);
		result = Math.max(result, distFromStart[x] + distFromFinish[startIdx]);
	}
	
	public static void dijkstra() {
		distFromFinish[x] = 0;
		pq.add(new Node(x,0));
		while (!pq.isEmpty()) {
			Node thisTurn = pq.poll();
			if (visited[thisTurn.adjacent]) {
				continue;
			} 
			visited[thisTurn.adjacent] = true;
			for(Node next : graph[thisTurn.adjacent]) {
				if (distFromFinish[next.adjacent] > thisTurn.weight + next.weight) {
					distFromFinish[next.adjacent] = thisTurn.weight + next.weight;
					pq.add(new Node(next.adjacent, distFromFinish[next.adjacent]));
				}
			}
		}
		
	}
}


class Node implements Comparable<Node>{
	int adjacent;
	int weight;
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return Integer.compare(weight, o.weight);
	}
	public Node(int adjacent, int weight) {
		super();
		this.adjacent = adjacent;
		this.weight = weight;
	}
	
	
	
	
}