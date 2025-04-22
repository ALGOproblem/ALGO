package 공통주차별문제.Week08.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2533_사회망서비스 {
	static BufferedReader input;
	static StringTokenizer st;

	static List<Integer>[] adj;
	static int[][] dp;
	static boolean[] visited;

	// dp[node][0] 얼리어답터 X,
	// dp[node][1] : 얼리어답터 O
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("input.txt"));
		int N = Integer.parseInt(input.readLine());

		adj = new ArrayList[N + 1]; // 인접 노드
		visited = new boolean[N + 1]; // 방문 확인

		dp = new int[N + 1][2]; // 2 -> 얼리 O 또는 X
		for (int i = 1; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int j = 0; j < N - 1; j++) {
			st = new StringTokenizer(input.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adj[start].add(end);
			adj[end].add(start);
		}

		dfs(1); // 루트를 1번이라고 가정

		// 결과 값은 루트가 얼리 O 또는 얼리 X
		System.out.println(Math.min(dp[1][0],dp[1][1]));

	}

	// 리프 노드에서부터 값을 더하면서 루트까지 올라온다.
	static void dfs(int node) {
		visited[node] = true; // 재방문 방지
		dp[node][0] = 0; // 얼리어답터 X
		dp[node][1] = 1; // 얼리어답터 O

		// 인접 노드 방문하기
		for (int nextNode : adj[node]) {
			if (!visited[nextNode]) {
				dfs(nextNode);
				// 본인이 얼리X 면 친구는 얼리O 임
				dp[node][0] += dp[nextNode][1];
				// 본인이 얼리O 면 친구는 얼리O 거나 아니거나
				dp[node][1] += Math.min(dp[nextNode][0], dp[nextNode][1]);
			}
		}
	}
}
