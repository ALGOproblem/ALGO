package 실습.김미림.APS응용_Day08_09_10_그래프최소비용;

import java.util.*;

public class swea_1238_Contact {
	static List<Integer>[] graph;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			int N = sc.nextInt();
			int start = sc.nextInt();
			
			graph = new ArrayList[101];
			for (int i = 0; i <= 100; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < N / 2; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				
				if (!graph[from].contains(to)) {
					graph[from].add(to);
				}
			}// 입력, 방향성 존재
			
			System.out.printf("#%d %d\n", tc, bfs(start));
			
		}// tc
		sc.close();
	}// main

	static int bfs(int start) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {start, 0});
		
		boolean[] visited = new boolean[101];
		int maxDepth = -1;
		int max = 0;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int node = cur[0];
			int depth = cur[1];
			
			if (visited[node]) continue;
	
			visited[node] = true;
			
			if (maxDepth < depth) {
				maxDepth = depth;
				max = node;
			} else if (maxDepth == depth) {
				max = Math.max(max, node);
			}// 큰 값, 깊이 갱신
			
			for (int next : graph[node]) {
				if (visited[next]) continue;
				
				queue.offer(new int[] {next, depth + 1});
			}// next
		}// while
		
		return max;
	}
}
