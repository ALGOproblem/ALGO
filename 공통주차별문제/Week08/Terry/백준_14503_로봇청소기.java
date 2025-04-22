package 공통주차별문제.Week08.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14503_로봇청소기 {
	static BufferedReader input;
	static StringTokenizer st;

	static int N, M; // 3 <= N, M <= 50
	static int[][] matrix; // N x M // 1은 벽, 0은 청소되지 않은 칸
	static int direction; // 청소기 바라보는 방향 // 북동남서 0123
	static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };
	static int cleanCnt = 0; // 청소한 칸의 개수

	static final int DIRTY = 0;
	static final int WALL = 1;
	static final int CLEAN = 2;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));


		st = new StringTokenizer(input.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(input.readLine());

		int r = Integer.parseInt(st.nextToken()); // 현재 좌표 r
		int c = Integer.parseInt(st.nextToken()); // 현재 좌표 c
		int d = Integer.parseInt(st.nextToken()); // 현재 방향

		matrix = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;
		while (true) {
			// 1. 청소
			if (isDirty(r, c)) {
				cnt++;
				cleanFloor(r, c);
			}

			// 2. 주변칸 확인 // 깨끗
			if (!isDirtyAround(r, c)) {
				// 2-1. 후진 불가하면 중단
				if (!canReverse(r, c, d)) {
					break;
				}

				// 2-1. 후진 가능하면 후진 후 1번으로
				int[] next = getReverseDirection(r, c, d);
				r = next[0];
				c = next[1];
				continue;
			}

			// 3. 주변칸 더러우면
			// 3-1. 반시계 회전
			d = rotate(d);

			// 3-2. 앞이 더러우면 전진
			if (isDirtyFront(r, c, d)) {
				// 전진
				int[] next = getFrontDirection(r, c, d);
				r = next[0];
				c = next[1];
				continue;
			}
		} // while

		System.out.println(cnt);
	}

	// 1. 현재칸 더러워? (isDirty) (좌표)
	static boolean isDirty(int r, int c) {
		if (!isMatrix(r, c)) {
			return false;
		}
		return matrix[r][c] == DIRTY;
	}

	// 2. 주변칸 더러워? (isDirtyAround) (좌표)
	static boolean isDirtyAround(int r, int c) {

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 범위 밖이면
			if (!isMatrix(nr, nc)) {
				continue;
			}

			if (matrix[nr][nc] == DIRTY) {
				return true;
			}
		}

		return false;
	}

	// 3. 앞에 더러워? (isDirtyFront) (좌표, direction)
	// 북동남서 0123
	static boolean isDirtyFront(int r, int c, int direction) {
		int[] nextPos = getFrontDirection(r, c, direction);
		int nr = nextPos[0];
		int nc = nextPos[1];

		return isDirty(nr, nc);
	}

	// 4. 치워
	static void cleanFloor(int r, int c) {
		matrix[r][c] = CLEAN;
	}

	// 5. 후진 가능? (canReverse) (좌표, direction)
	static boolean canReverse(int r, int c, int direction) {
		int[] nextPos = getReverseDirection(r, c, direction);
		int nr = nextPos[0];
		int nc = nextPos[1];

		return !isWall(nr, nc);
	}

	// 6. 반시계 회전 (rotate) (direction)
	static int rotate(int direction) {
		return (direction - 1 + 4) % 4;
	}

	// 7. 방향에 따른 좌표 리턴 함수 (좌표, direction)

	// 7-1 좌표 앞 방향 리턴
	static int[] getFrontDirection(int r, int c, int direction) {
		int nr = r;
		int nc = c;

		switch (direction) {
		case 0:
			nr = r - 1;
			break;
		case 1:
			nc = c + 1;
			break;
		case 2:
			nr = r + 1;
			break;
		case 3:
			nc = c - 1;
			break;
		}
		return new int[] { nr, nc };
	}

	// 7-2. 좌표 뒷 방향 리턴
	static int[] getReverseDirection(int r, int c, int direction) {
		int nr = r;
		int nc = c;
		// 뒤 확인
		switch (direction) { // 북동남서
		case 0:
			nr = r + 1;
			break;
		case 1:
			nc = c - 1;
			break;
		case 2:
			nr = r - 1;
			break;
		case 3:
			nc = c + 1;
			break;
		}

		return new int[] { nr, nc };
	}

	// 8. 범위 안?
	static boolean isMatrix(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= M);
	}

	// 9. 벽?
	static boolean isWall(int r, int c) {
		if (!isMatrix(r, c)) {
			return false;
		}
		return matrix[r][c] == WALL;
	}

}

