package 공통주차별문제.Week05.SWEA실습문제.TERRY;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea7733치즈도둑 {
	static BufferedReader input;
	static StringTokenizer st;

	static int N, maxChunks;
	static int[][] matrix;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());

		// 요정이 100일 동안 N인 칸을 먹는다. N인 칸 다먹어.
		// 100일 중에서 가장 많을 떄의 덩어리 개수는?

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(input.readLine());

			matrix = new int[N][N];

			// 치즈가 맛있는 정도 입력 받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(input.readLine());
				for (int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			maxChunks = 0;
			// 100 * 100 * 100
			// 100일 동안 반복
			for (int day = 0; day <= 100; day++) {
				visited = new boolean[N][N]; // 매일 방문 배열 초기화

				// 요정이 내 치즈 먹음
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						// <= day : 지금까지 지나온 날들 다 먹기
						if (matrix[i][j] <= day) {
							visited[i][j] = true; // 요정이 해당 칸 먹음
						}
					}
				}

				int chunks = 0;

				// 덩어리 세기
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!visited[i][j]) {
							dfs(i, j);
							chunks++;
						}
					}
				}
				maxChunks = Math.max(maxChunks, chunks);
			}

			System.out.println("#" + t + " " + maxChunks);

		}

	}

	// 경계 체크 해야 됨!
	static void dfs(int row, int col) {

		visited[row][col] = true;

		for (int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];

			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				continue;
			}

			// 안먹으면 들어가!
			if (!visited[nr][nc]) {
				dfs(nr, nc);
			}
		}
	}
}
