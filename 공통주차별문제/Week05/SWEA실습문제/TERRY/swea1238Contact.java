package 공통주차별문제.Week05.SWEA실습문제.TERRY;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class swea1238Contact {
	static BufferedReader input;
	static StringTokenizer st;

	static boolean[] visited;
	static int[] dist; // 정점별 거리
	static List<Integer>[] adj; // 정점과 인접한 정점들
	static int maxDist; // 이동거리

	// 1. 정점에 인접한 정점들 방문
	// 2. 이전 거리 + 1 < 정점 거리 면 정점 거리 갱신
	// 3. maxDist 갱신
	// 4. maxDist 중에 제일 큰 번호 출력
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = 10;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(input.readLine());
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());

			// 값들 초기화
			visited = new boolean[101];
			dist = new int[101];
			Arrays.fill(dist, Integer.MAX_VALUE);

			adj = new ArrayList[101];
			for (int i = 0; i < 101; i++) {
				adj[i] = new ArrayList<>();
			}

			maxDist = 0;

			// 인접 리스트에 정점 넣기
			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < N / 2; i++) {
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());

				adj[A].add(B);
			}

			dijkstra(start);

			// 4. maxDist 중에 제일 큰 번호 출력
			int result = 0;
			for (int i = 0; i < 101; i++) {
				if (maxDist == dist[100 - i]) {
					result = 100 - i;
					break;
				}
			}

			System.out.println("#" + t + " " + result);
		}

	}

	static void dijkstra(int start) {
		Deque<Integer> dq = new ArrayDeque<>();

		// start dq에 넣기
		dq.add(start);
		dist[start] = 0;
		
		while (!dq.isEmpty()) {
			int target = dq.poll();
			if (visited[target]) {
				continue;
			}
			visited[target] = true;

			// 1. 정점에 인접한 정점들 방문
			for (int next : adj[target]) {
				// 2. 이전 거리 + 1 < 정점 거리 면 정점 거리 갱신
				if (dist[next] > dist[target] + 1) {
					dist[next] = dist[target] + 1;
					// 3. maxDist 갱신
					maxDist = Math.max(maxDist, dist[next]);
				}
			}
			dq.addAll(adj[target]);
		}
	}
}
