import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int n, m, s, t;
	static List<List<int[]>> graph = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int w = sc.nextInt();
			
			graph.get(a).add(new int[] {b, w});
			graph.get(b).add(new int[] {a, w});
		}
		
		s = sc.nextInt();
		t = sc.nextInt();
		
		System.out.println(dijkstra(s, t));

	}// main
	
	// 여태까지 그래프에서 최단거리(최소 가중치) 구하기
	static int dijkstra(int start, int end) {
		int[] dis = new int[n + 1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[start] = 0;
		
		PriorityQueue<int[]> pq2 = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq2.offer(new int[] {start, 0});
		
		while (!pq2.isEmpty()) {
			int[] curr = pq2.poll();
			int currIdx = curr[0];
			int currSum = curr[1];
			
			if (currSum > dis[currIdx]) continue;
			
			for (int[] next: graph.get(currIdx)) {
				int nextIdx = next[0];
				int nextDis = next[1];
				int newDis = dis[currIdx] + nextDis;
				
				if (dis[nextIdx] > newDis) {
					dis[nextIdx] = newDis;
					pq2.offer(new int[] {nextIdx, newDis});
				}
			}
		}// while
		
		return dis[end];
	}
	
}
