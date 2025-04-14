
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Node implements Comparable<Node>{
	int start, end, cost;
	
	public Node(int start, int end, int cost) {
		super();
		this.start = start;
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
	
}
public class swea1249보급로 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/swea1249보급로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			for (int i = 0; i<N; i++) {
				String s = br.readLine();
				for (int j = 0; j<N; j++) {
					arr[i][j] = s.charAt(j) - '0';
				}
			}//arr입력
			int ans = dijkstra(0, 0, 0);
			System.out.println("#"+test+" "+ans);
		}

	}
	
	static int N;
	static int[][] arr;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int dijkstra(int startX, int startY, int startCost) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		int[][] dist =  new int[N][N];
		for (int i = 0; i<N; i++) {			
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		dist[startX][startY] = 0;
		pq.offer(new Node(startX, startY, 0));
		int ans = 0;
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int s = cur.start, e = cur.end, cost = cur.cost;
			if (cost > dist[s][e]) continue;
			if (s == N-1 && e == N-1) {
				ans = dist[s][e];
				break;
			}
			for (int i = 0; i<4; i++) {
				int ns = s + dx[i];
				int ne = e + dy[i];
				if (ns<0 || ns>=N || ne<0 || ne>=N) continue;
				int nextCost = cost + arr[ns][ne];
				if (nextCost < dist[ns][ne]) {
					dist[ns][ne] = nextCost;
					pq.offer(new Node(ns, ne, nextCost));
				}
			}
		}
		return ans;
	}

}
