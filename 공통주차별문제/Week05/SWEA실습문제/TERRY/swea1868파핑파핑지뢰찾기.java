package D4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class swea1868파핑파핑지뢰찾기 {
	static BufferedReader input;

	static int N;
	static char[][] matrix;
	static int[][] numbers;
	static boolean[][] isNumber;

	// 12시부터 시계 방향
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };

	// 0. isNumber에 숫자는 true;
	// 1. numbers 구한다.
	// 2. bfs를 돌면서 맵에서 0을 밝힌다.
	// 3. true로 된 곳은 0의 8개 영역 밖의 숫자들이니 카운트
	// 4. bfs 동작했던 수 + 카운트 = 최소 눌러야 하는 수

	public static void main(String[] args) throws NumberFormatException, IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(input.readLine());

			// 초기화
			matrix = new char[N][N];
			isNumber = new boolean[N][N];
			numbers = new int[N][N];

			// 값 할당
			for (int i = 0; i < N; i++) {
				String line = input.readLine();
				for (int j = 0; j < N; j++) {
					char target = line.charAt(j);
					matrix[i][j] = target;

					// 0. isNumber ?
					if (target == '.') {
						isNumber[i][j] = true;
					}
				}
			}

			// 1. numbers 구하기
			// N * N * 8
			// 최대 300 * 300 * 8 = 720_000
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					int bombCnt = 0;

					// 본인이 *면 숫자 세지마
					if (matrix[y][x] == '*') {
						continue;
					}

					// 8방향 돌기
					for (int d = 0; d < 8; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];

						// 범위 체크
						if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
							continue;
						}

						if (matrix[ny][nx] == '*') {
							bombCnt++;
						}
					}

					numbers[y][x] = bombCnt;
				}
			}

			// 클릭해야 하는 숫자
			int clickCnt = 0;

			// 2. bfs 돌면서 0 주변 8방향 false
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					// 지뢰 아니고 0이면 들어가 
					if (isNumber[y][x] && numbers[y][x] == 0) {
						clickCnt++;
						bfs(y, x);
					}
				}
			}
			
			// 3. 눌러야 하는 남은 숫자 카운트
			// 시간 N * N * 8?
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if(isNumber[y][x]) {
						clickCnt++;
					}
				}
			}
			
			
			
			// 4. bfs 동작했던 수 + 카운트 = 최소 눌러야 하는 수
			System.out.println("#" + t + " "+ clickCnt);
			
			// 전체 시간복잡도 N^2 ?
			
		}
	}

	// 0의 8방향 false
	static void bfs(int y, int x) {
		Deque<int[]> dq = new ArrayDeque<>();

		dq.add(new int[] { y, x });
		isNumber[y][x] = false;

		while (!dq.isEmpty()) {
			int[] target = dq.poll();

			// 0이면 dq에 넣고 나머지 숫자는 false로
			for (int d = 0; d < 8; d++) {
				int nx = target[1] + dx[d];
				int ny = target[0] + dy[d];

				// 범위 체크
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
					continue;
				}

				// 0 일때 dq 넣기
				if (isNumber[ny][nx] && numbers[ny][nx] == 0) {
					dq.add(new int[] { ny, nx });
				}

				isNumber[ny][nx] = false;
			}
		}

	}
}
