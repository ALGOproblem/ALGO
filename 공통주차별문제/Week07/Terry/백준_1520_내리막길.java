package 공통주차별문제.Week07.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1520_내리막길 {
	static BufferedReader input;
	static StringTokenizer st;

	static int N, M;
	static int[][] matrix;
	// 상 하 좌 우
	static int[] dy = new int[] { -1, 1, 0, 0 };
	static int[] dx = new int[] { 0, 0, -1, 1 };
	static int[][] dp;

	// 도달할 수 있는 경우의 수를 저장하자! -> dp사용하기
	// 1. 상하좌우 탐색하기
	// 2. 값이 있으면 기존 값 반환
	// 3. 목표 지점에 도달할 경우 1반환 => 경우의 수 쌓기
	
	// - 쭉 타고 들어가서 목표지점 부근에서 값을 갱신하면서 0, 0으로 온다.
	
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		st = new StringTokenizer(input.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		matrix = new int[M][N];
		dp = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		int result = dfs(0, 0);
		
		System.out.println(result);
	}

	static int dfs(int x, int y) {
		// 3. 목표 도달 반환
		if (x == N - 1 && y == M - 1) {
			return 1;
		}

		// 2. 값이 있으면 반환
		if (dp[y][x] != -1) {
			return dp[y][x];
		}
		
		// 처음 방문 시 값을 초기화 해준다 
		// 없으면 도달한 경우에만 메모이제이션이 적용된다.
		// -> 메모이제이션이 제대로 동작 하지 않아 시간초과
		dp[y][x] = 0;

		// 1. 상하좌우 탐색하기
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			// 벗어날 경우
			if (ny < 0 || nx < 0 || ny >= M || nx >= N) {
				continue;
			}

			// 내리막이 아닌 경우
			if (matrix[ny][nx] >= matrix[y][x]) {
				continue;
			}

			dp[y][x] += dfs(nx, ny);
		}

		return dp[y][x];
	}
}
