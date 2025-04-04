package 공통주차별문제.Week06.SWEA실습문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class swea_1267_작업순서 {
	static StringTokenizer st;
	static BufferedReader input;

	static int[] degree; // 진입 차수
	static List<Integer>[] adj; // 인접 리스트
	static Deque<Integer> dq;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = 10;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(input.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			// 초기화
			degree = new int[V + 1]; // 1부터 시작

			adj = new ArrayList[V + 1]; // 인접 리스트
			for (int i = 0; i < V + 1; i++) {
				adj[i] = new ArrayList<>();
			}
			// 초기화 끝

			// 인접 리스트 & 진입차수 셋팅
			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < E; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());

				adj[start].add(end);
				degree[end]++;
			}

			dq = new ArrayDeque<>();

			// 1. 진입차수 0인 노드 queue에 넣기
			for (int i = 1; i < V + 1; i++) {
				if (degree[i] == 0) {
					dq.add(i);
				}
			}

			StringBuilder result = new StringBuilder("#" + t + " ");

			while (!dq.isEmpty()) {
				int curr = dq.poll();

				result.append(curr + " ");

				// 2. 인접 노드 진입차수 하나씩 줄이기
				for (int node : adj[curr]) {
					degree[node]--;

					// 3. 진입차수 0인 노드 queue에 넣기
					if (degree[node] == 0) {
						dq.add(node);
					}
				}
			}

			System.out.println(result);
		}

	}

}
