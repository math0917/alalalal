

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int v, e;
	static boolean[] visited;
	
	static int[] dist1;
	static int[] dist2;
	static int[] dist3;
	
	static ArrayList<Node>[] graph;
	static Queue<int[]> queue = new ArrayDeque<>();
	static PriorityQueue<Node> pq;
	static int result;
	static int startNode, midNode1, midNode2, finishNode;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		graph = new ArrayList[v+1];
		dist1 = new int[v+1];
		dist2 = new int[v+1];
		dist3 = new int[v+1];
		for (int i = 1;i < v+1;i ++) {
			graph[i] = new ArrayList<>();
			dist1[i] = Integer.MAX_VALUE;
			dist2[i] = Integer.MAX_VALUE;
			dist3[i] = Integer.MAX_VALUE;
		}
		startNode = 1;
		finishNode = v;
		
		for (int t = 0; t < e ;t++) {
			st = new StringTokenizer(br.readLine());
			int fr = Integer.parseInt(st.nextToken()),
					to = Integer.parseInt(st.nextToken()),
					weight = Integer.parseInt(st.nextToken());
			graph[fr].add(new Node(to,weight));
			graph[to].add(new Node(fr,weight));
		}
		
		st = new StringTokenizer(br.readLine());
		midNode1 = Integer.parseInt(st.nextToken());
		midNode2 = Integer.parseInt(st.nextToken());
		dijkstra();
	
		
		long result1 = (long)dist1[midNode1] + dist2[midNode2] + dist3[finishNode];
		long result2 = (long)dist1[midNode2] + dist3[midNode1] + dist2[finishNode];
		
		long ans = Math.min(result1,  result2);
		
		if (ans >= Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
		
	}
	static void dijkstra() {
		dist1[startNode] = 0;
		pq = new PriorityQueue<>();
		pq.add(new Node(startNode,0));
		visited = new boolean[v+1];
		while (!pq.isEmpty()) {
			Node thisTurn = pq.poll();
			if (visited[thisTurn.adjacent]) {
				continue;
			}
			visited[thisTurn.adjacent] = true;
			for (Node next : graph[thisTurn.adjacent]) {
				int node = next.adjacent;
				int weight = next.weight;
				
				if (dist1[node] > thisTurn.weight + weight) {
					dist1[node] = thisTurn.weight + weight;
					pq.add(new Node(node, thisTurn.weight+weight));
				}
			}
		}
		
		dist2[midNode1] = 0;
		pq = new PriorityQueue<>();
		pq.add(new Node(midNode1,0));
		visited = new boolean[v+1];
		while (!pq.isEmpty()) {
			Node thisTurn = pq.poll();
			if (visited[thisTurn.adjacent]) {
				continue;
			}
			visited[thisTurn.adjacent] = true;
			for (Node next : graph[thisTurn.adjacent]) {
				int node = next.adjacent;
				int weight = next.weight;
				
				if (dist2[node] > thisTurn.weight + weight) {
					dist2[node] = thisTurn.weight + weight;
					pq.add(new Node(node, thisTurn.weight+weight));
				}
			}
		}
		
		dist3[midNode2] = 0;
		pq = new PriorityQueue<>();
		pq.add(new Node(midNode2,0));
		visited = new boolean[v+1];
		while (!pq.isEmpty()) {
			Node thisTurn = pq.poll();
			if (visited[thisTurn.adjacent]) {
				continue;
			}
			visited[thisTurn.adjacent] = true;
			for (Node next : graph[thisTurn.adjacent]) {
				int node = next.adjacent;
				int weight = next.weight;
				
				if (dist3[node] > thisTurn.weight + weight) {
					dist3[node] = thisTurn.weight + weight;
					pq.add(new Node(node, thisTurn.weight+weight));
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
	
		this.adjacent = adjacent;
		this.weight = weight;
	}
	

	
	
}