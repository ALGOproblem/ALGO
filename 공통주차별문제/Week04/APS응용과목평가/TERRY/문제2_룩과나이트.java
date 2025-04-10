package 공통주차별문제.Week04.APS응용과목평가.TERRY;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 문제2_룩과나이트 {
	static BufferedReader input;
	static StringTokenizer st;
	static int[][] matrix;
	static int N, M, K;
	static int cnt;

	static int rookX;
	static int rookY;

	static int knightX;
	static int knightY;

	static Set<String> result;
	// 룩 이동 좌표
	// 왼쪽 위&아래 , 아래 왼&오, 오른 아래&위, 위 오른&왼
	static int[] dx = { -2, -2, -1, 1, 2, 2, 1, -1 };
	static int[] dy = { -1, 1, 2, 2, 1, -1, -2, -2 };

	public static void main(String[] args) throws IOException {
//		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(input.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			matrix = new int[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(input.readLine());
				for (int j = 0; j < M; j++) {
					int target = Integer.parseInt(st.nextToken());
					matrix[i][j] = target;
					if (target == 1) {
						rookY = i;
						rookX = j;
					}
					if (target == 2) {
						knightY = i;
						knightX = j;
					}
				}
			}

			result = new HashSet<>();
			// 룩이나 나이트가 N번 이동.
			// 나이트만 N번, 룩만 N번
			// 나이트 0번부터 N번까지 나머지 룩이 움직이기

			recur(0, knightX, knightY, rookX, rookY);

			System.out.println("#" + t + " " + result.size());

		}
	}

	static void recur(int level, int kx, int ky, int rx, int ry) {
		// rook과 knight가 같은 위치면 넘기기
		if (ky == ry && kx == rx)
			return;

		if (level == K) {
			result.add(ky + "," + kx + "-" + ry + "," + rx);
			return;
		}

		// knight 만 k번
		for (int j = 0; j < 8; j++) {
			int knx = kx + dx[j];
			int kny = ky + dy[j];
			// 범위 벗어나면 돌기
			if (knx < 0 || knx >= M || kny < 0 || kny >= N) {
				continue;
			}

			// 나이트만 움직이기
			recur(level + 1, knx, kny, rx, ry);
		}

		// 룩이 이동 시 나이트에 막히는 경우가 존재.
		// 나이트보다 위에 있을 경우 위에만
		// 나이트보다 아래에 있을 경우 아래만 탐색

		// 룩이 나이트랑 같은 열일 경우
		// 행 변경
		if (rx == kx) {
			// 룩이 위에 있는 경우
			if (ry < ky) {
				for (int k = 0; k < ky; k++) {
					// 룩은 현재 자리에 놓으면 안됨.
					if (ry == k)
						continue;
					recur(level + 1, kx, ky, rx, k);
				}
			}
			// 룩이 아래에 있는 경우
			else if (ry > ky) {
				for (int k = ky + 1; k < N; k++) {
					if (ry == k)
						continue;
					recur(level + 1, kx, ky, rx, k);
				}
			}
		} else {
			for (int k = 0; k < N; k++) {
				if (ry == k)
					continue;
				recur(level + 1, kx, ky, rx, k);
			}
		}

		// 같은 행일 경우
		// 열 변경
		if (ry == ky) {
			// 룩이 왼쪽에 있는 경우
			if (rx < kx) {
				for (int v = 0; v < kx; v++) {
					if (rx == v)
						continue;
					recur(level + 1, kx, ky, v, ry);
				}
			}
			// 룩이 오른쪽에 있는 경우
			else if (rx > kx) {
				for (int v = kx + 1; v < M; v++) {
					if (rx == v)
						continue;
					recur(level + 1, kx, ky, v, ry);
				}
			}
		} else {
			for (int v = 0; v < M; v++) {
				if (rx == v)
					continue;
				recur(level + 1, kx, ky, v, ry);
			}
		}

	}
}
