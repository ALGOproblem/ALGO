package 실습.김미림.실습Day08_09_그래프최소비용;

import java.util.*;

public class swea_1251_하나로_prim {
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		long weight;
		
		public Edge(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	static List<Edge> edges;
	static int N;
	static int[] parent;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			
			int[][] graph = new int[N][2];
			for (int i = 0; i < N; i++) {
				graph[i][0] = sc.nextInt();
			}
			for (int i = 0; i < N; i++) {
				graph[i][1] = sc.nextInt();
			}// x, y 값 입력
			
			double rate = sc.nextDouble();
			
			edges = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					long dx = graph[i][0] - graph[j][0];
					long dy = graph[i][1] - graph[j][1];
					long c = (dx * dx + dy * dy);
					edges.add(new Edge(i, j, c));
				}
			}// 간선, 가중치 저장
			Collections.sort(edges);
			
			// 간선 이어짐 확인: parent
			parent = new int[N];
			
			for (int i = 0; i < N; i++) {
				parent[i] = i;
			}
			
			// total로 최소 비용 구하기
			long total = 0;
			int count = 0;
			
			for (Edge edge : edges) {
				if (union(edge.from, edge.to)) {
					total += edge.weight;
					count++;
					if (count == N - 1) break;
				}
			}
			
			double result = total * rate;
			System.out.printf("#%d %d\n", tc, Math.round(result));
			
		
			
			
		}// tc
		sc.close();
	}// main
	
	static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if (px == py) return false;
		parent[py] = px;
		return true;
	}
	
}
