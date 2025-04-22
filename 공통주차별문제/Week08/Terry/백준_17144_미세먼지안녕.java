package 공통주차별문제.Week08.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_17144_미세먼지안녕 {
	static BufferedReader input;
	static StringTokenizer st;

	static int[][] matrix, matrixCopy;
	static int R, C, T;
	static int[] drCw = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dcCw = { 0, 1, 0, -1 };
	static int[] drCcw = { 1, 0, -1, 0 }; // 하 우 상 좌
	static int[] dcCcw = { 0, 1, 0, -1 };
	static int topR, topC, bottomR, bottomC; // 로봇의 위, 아래 좌표

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		st = new StringTokenizer(input.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		matrix = new int[R][C];
		matrixCopy = new int[R][C];

		boolean flag = false;

		// 매트릭스 입력
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(input.readLine());
			for (int j = 0; j < C; j++) {
				int number = Integer.parseInt(st.nextToken());
				matrix[i][j] = number;

				// 로봇 좌표 저장
				if (number == -1) {
					if (flag) {
						bottomR = i;
						bottomC = j;
						continue;
					}
					topR = i;
					topC = j;
					flag = true;
				}
			}
		}

//		작성할 함수
//		1. 4방향 확인 후 확산 후 남은 양 계산
//		2. copy랑 합치고 copy 초기화
//		3. 반시계 순환
//		4. 시계 순환
//		5. 범위 확인

		// T번 돌면서

		for (int t = 0; t < T; t++) {
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					spread(i, j);
				}
			}

			sumDust();

			rotate(topR, topC, false);
			rotate(bottomR, bottomC, true);

		}
		
		// 남은 미먼 양 합산
		int result = 0;
		for (int[] row : matrix) {
			for (int dust : row) {
				if (dust == -1) {
					continue;
				}
				result += dust;
			}
		}
		
		System.out.println(result);
	}

	// 1. 4방향 확인 후 확산 남은 양 계산
	static void spread(int r, int c) {
		int cnt = 0;
		int dust = matrix[r][c];

		if (dust == 0)
			return;

		for (int d = 0; d < 4; d++) {
			int nr = r + drCw[d];
			int nc = c + dcCw[d];

			if (!isMatrix(nr, nc)) {
				continue;
			}

			// 공청이 있는 곳은 확산 하지 않는다.
			if (matrix[nr][nc] == -1) {
				continue;
			}

			cnt++;
			matrixCopy[nr][nc] += dust / 5;
		}

		matrix[r][c] -= dust / 5 * cnt;
	}

	// 2. 합치고 초기화
	static void sumDust() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				matrix[i][j] += matrixCopy[i][j];
				matrixCopy[i][j] = 0;
			}
		}
	}

	// 3. 반시계 순환 / 4. 시계 순환
	static void rotate(int r, int c, boolean rotateClockwise) {
		int d = 0;

		// 바람이 시계 방향 회전이면 반시계로 돈다.
		int[] dr = rotateClockwise ? drCcw : drCw;
		int[] dc = rotateClockwise ? dcCcw : dcCw;

		int robotR = rotateClockwise ? bottomR : topR;
		int robotC = rotateClockwise ? bottomC : topC;

		// 로봇 자리에서 한 칸 이동
		r += dr[d];
		c += dc[d];

		// 반시계 / 시계로 순환하면서 값 업데이트
		while (true) {
			// 꼭짓점 확인
			if (rotateClockwise ? checkDownCornerPoints(r, c) : checkUpCornerPoints(r, c)) {
				d++;
			}

			int nr = r + dr[d];
			int nc = c + dc[d];

			// 로봇으로 돌아옴
			if (nr == robotR && nc == robotC) {
				matrix[r][c] = 0;
				break;
			}

			// 현재에 다음 먼지를 넣는다.
			matrix[r][c] = matrix[nr][nc];

			// 다음으로 이동 (좌표 업데이트)
			r = nr;
			c = nc;
		}
	}

	// 5. 범위 확인
	static boolean isMatrix(int r, int c) {
		return !(r < 0 || c < 0 || r >= R || c >= C);
	}

	// 6. 매트릭스 꼭짓점 확인
	// 로봇 윗부분은 왼쪽 아래 체크할 필요 없음 (로봇 자리)
	static boolean checkUpCornerPoints(int r, int c) {
		boolean leftTop = r == 0 && c == 0;
		boolean rightTop = r == 0 && c == C - 1;
		boolean rightBottom = r == topR && c == C - 1;

		return leftTop || rightTop || rightBottom;
	}

	// 로봇 아래 부분은 왼쪽 위 체크할 필요 없음 (로봇 자리)
	static boolean checkDownCornerPoints(int r, int c) {
		boolean rightTop = r == bottomR && c == C - 1;
		boolean leftBottom = r == R - 1 && c == 0;
		boolean rightBottom = r == R - 1 && c == C - 1;

		return rightTop || leftBottom || rightBottom;
	}
}
