package samsungA형대비.EIZI;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 3. "0-1 BFS" 방식으로 풀려는 접근 방식 실패
	하지만 이 문제는 단순한 0-1 가중치 문제가 아니라, 가중치가 0~9까지 다양하게 존재하는 문제!
	즉, 일반적인 BFS(0-1 BFS) 방식이 아니라, 다익스트라(우선순위 큐)를 사용해야 함.
	
	출력 형식이 Problem인데 problem 소문자로 씀
 */
public class 백준4485녹색옷입은애가젤다지 {
	static int N;
	static int time = 0;
	static final int INF = Integer.MAX_VALUE;
	static int[][] arr;
	static int[] dirx = {-1, 1, 0, 0}; //상하좌우
	static int[] diry = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/백준4485녹색옷입은애가젤다지.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
        	N = Integer.parseInt(br.readLine());
        	if (N == 0) break;
        	time++;
        	arr = new int[N][N];
        	for (int i = 0; i<N; i++) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
        		for (int j = 0; j<N; j++) {
        			arr[i][j] = Integer.parseInt(st.nextToken()); // 0이상 9이하 한 자리수
        		}
        	}//입력
        	System.out.println("Problem "+time+": "+dijkstra());
        	
        }
	}
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int cost;
		public Node() {
		}
		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
	}
	static int dijkstra() {
		int[][] dist = new int[N][N];
		for (int i = 0; i<N; i++) Arrays.fill(dist[i], INF); // INF로 초기화
		dist[0][0] = arr[0][0];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, arr[0][0])); // x, y, dist
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			int curx = current.x;
			int cury = current.y;
			int cost = current.cost;
			if (cost > dist[curx][cury]) continue;
			for (int i = 0; i<4; i++) {
				int nextx = curx + dirx[i];
				int nexty = cury + diry[i];
				if (nextx>=0 && nextx<N && nexty >=0 && nexty<N) {
					int nextCost = cost + arr[nextx][nexty];
					if (nextCost < dist[nextx][nexty]) {
						dist[nextx][nexty] = nextCost;
						pq.offer(new Node(nextx, nexty, nextCost));
					}	
				}
			}
			
		}
		return dist[N-1][N-1];
	}

}
