package 실습.김미림.실습Day08_09_그래프최소비용;

import java.util.*;

public class swea_1249_보급로 {
	static int N;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String line = sc.next();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}// 입력
			
			int result = dijkstra();
			
			System.out.printf("#%d %d\n", tc, result);

		}// tc
		sc.close();
	}// main

	static int dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
		pq.offer(new int[] {0, 0, 0}); // 행, 열, 거리
		
		int[][] distance = new int[N][N];
		for (int[] row : distance) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		distance[0][0] = 0;
		
		int[] dr = {1, 0, -1, 0};
		int[] dc = {0, 1, 0, -1};
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int r = cur[0];
			int c = cur[1];
			int w = cur[2];
			
			if (r == N - 1 && c == N - 1) break;
			
			if (w > distance[r][c]) continue;
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (isOnMap(nr, nc)) {
					int nw = cur[2] + map[nr][nc];
					
					if (nw < distance[nr][nc]) {
						distance[nr][nc] = nw;
						pq.offer(new int[] {nr, nc, nw});
					}
				}
				
			}//delta
		}// while
		
		return distance[N - 1][N - 1];
	}// dijkstra
	
	static boolean isOnMap(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < N) return true;
		return false;
	}
}
