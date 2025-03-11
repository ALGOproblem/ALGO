import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	// 바꿔야할 방의 개수 = 가중치 = 최솟값
	static int n;
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String str = sc.next();
			for (int j = 0; j < n; j++) {
				 map[i][j] = str.charAt(j) - '0';
			}
		}// 입력
		
		System.out.println(dijkstra(new int[] {0,0}, new int[] {n-1, n-1}));
	}// main
	
	static int dijkstra(int[] start, int[] end) {
		int[][] dis = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dis[i], Integer.MAX_VALUE);
		}
		dis[start[0]][start[1]] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
		pq.add(new int[] {start[0], start[1], 0});
		

		int[] dr = new int[] {-1, 1, 0, 0};
		int[] dc = new int[] {0, 0, -1, 1};
		
		while (!pq.isEmpty()) {
			int[] node = pq.poll();
			int r = node[0];
			int c = node[1];
			int currSum = node[2];
			
			if (currSum > dis[r][c]) continue;
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr >= 0 && nc >= 0 && nr < n && nc < n) {
					int nextDis = 0;
					if (map[nr][nc] == 0) nextDis = 1;
					
					int newDis = nextDis + dis[r][c];
					
					if (newDis < dis[nr][nc]) {
						dis[nr][nc] = newDis;
						pq.add(new int[] {nr, nc, newDis});
					}
				}
			}
		}// while
		
		return dis[end[0]][end[1]];
	}
	
}
