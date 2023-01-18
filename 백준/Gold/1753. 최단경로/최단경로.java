import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int v, e, startNode;
	static ArrayList<Node>[] graph;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static boolean[] visited;
	static long[] dist;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		startNode = Integer.parseInt(br.readLine());
		graph = new ArrayList[v+1];
		visited = new boolean[v+1];
		dist = new long[v+1];
		
		for (int i = 1; i < v+ 1; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = Long.MAX_VALUE;
		}
		for (int t = 0; t < e; t++) {
			
			st = new StringTokenizer(br.readLine());
			int fr = Integer.parseInt(st.nextToken()),
					to = Integer.parseInt(st.nextToken()),
					weight = Integer.parseInt(st.nextToken());
			
			
			graph[fr].add(new Node(to,weight));
		
			
		}
		
		dijkstra();
		for (int i = 1; i < v + 1;i++) {
	
			if (dist[i] == Long.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(dist[i]);
			}
				
			
		}
	}
	
	static void dijkstra() {
		
		dist[startNode] = 0;
		
		pq.add(new Node(startNode, 0));
		while(!pq.isEmpty()) {
			Node thisTurn = pq.poll();
			if (visited[thisTurn.adjacent]) {
				continue;
			}
			visited[thisTurn.adjacent] = true;
			int destination = thisTurn.adjacent;
			for (Node next : graph[destination]) {
				if (dist[next.adjacent] > thisTurn.weight + next.weight) {
					dist[next.adjacent] = thisTurn.weight + next.weight;
					pq.add(new Node(next.adjacent, thisTurn.weight + next.weight));
				}
			}
		}
	}
}



class Node implements Comparable<Node>{
	int adjacent;
	int weight;
	public Node(int adjacent, int weight) {
		super();
		this.adjacent = adjacent;
		this.weight = weight;
	}
	@Override
	public int compareTo(Node o) {
		return Integer.compare(weight, o.weight);
	}
	
	
	
	
	
	
}