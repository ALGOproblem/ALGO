package 실습.김미림.실습Day08_09_10_그래프최소비용;

import java.util.*;

public class swea_1251_하나로_prim {
	static int N;
	static int[][] graph;
	static boolean[] visited;
	static long[] minDis;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			
			graph = new int[N][2];
			for (int i = 0; i < N; i++) {
				graph[i][0] = sc.nextInt();
			}
			for (int i = 0; i < N; i++) {
				graph[i][1] = sc.nextInt();
			}// x, y 값 입력
			
			double rate = sc.nextDouble();

			long result = prim(rate);
			
			System.out.printf("#%d %d\n", tc, result);
		}// tc
		sc.close();
	}// main
	
	static long prim(double rate) {
		visited = new boolean[N + 1];
		minDis = new long[N];
		
		Arrays.fill(minDis, Long.MAX_VALUE);
		minDis[0] = 0;
		
		long total = 0;
		
		for (int i = 0; i < N; i++) { // 반복횟수 N번(노드의 개수)
			long min = Long.MAX_VALUE;
			int minIdx = -1;
			
			for (int j = 0; j < N; j++) {
				if (!visited[j] && minDis[j] < min) {
					min = minDis[j];
					minIdx = j;
				}
			}// 현재까지 최소 비용 idx 찾기
			
			// 최소 비용 idx 방문
			visited[minIdx] = true;
			total += min;
			
			// 다른 정점들의 최소 비용 갱신해주기
			for (int j = 0; j < N; j++) {
				if (visited[j]) continue;
				
				long dx = graph[minIdx][0] - graph[j][0];
				long dy = graph[minIdx][1] - graph[j][1];
				long cost = dx * dx + dy * dy;
				
				if (cost < minDis[j]) {
					minDis[j] = cost;
				}
			}// 갱신
		}

		return Math.round(total * rate);	
	}
		
}
