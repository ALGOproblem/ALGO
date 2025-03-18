package 공통주차별문제.Week04.APS응용과목평가.MIRIM;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class 문제2_룩과나이트 {
	static int N, M; // 체스판 크기
	static int K; // 이동횟수
	static int[][] map; // 체스판
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] dk = { { 2, -1 }, { 2, 1 }, { -2, -1 }, { -2, 1 }, { -1, -2 }, { 1, -2 }, { -1, 2 }, { 1, 2 } };
	// 나이트 이동

	static Set<String> result; // 결과 저장 : 중복제거

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();

			map = new int[N][M];
			int rr = 0, rc = 0; // 룩 위치
			int kr = 0, kc = 0; // 나이트 위치

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();

					if (map[i][j] == 1) { // 룩
						rr = i;
						rc = j;
					} else if (map[i][j] == 2) { // 나이트
						kr = i;
						kc = j;
					}
				}
			} // 체스판 입력 받기

			result = new HashSet<>();

			bfs(rr, rc, kr, kc);

			System.out.printf("#%d %d\n", tc, result.size());

		} // tc

	}// main

	static void bfs(int rr, int rc, int kr, int kc) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { rr, rc, kr, kc, 0 }); // 룩, 나이트, 이동횟수

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int rRook = cur[0], cRook = cur[1], rKnight = cur[2], cKnight = cur[3];
			int moveCount = cur[4]; // 이동 횟수

			if (moveCount == K) {
				if (rRook != rKnight || cRook != cKnight) {
					result.add(rRook + "," + cRook + "," + rKnight + "," + cKnight);
				}
				continue;
			}// 이동 종료: 룩과 나이트 같은 위치 아니라면 결과에 추가

			// 룩 이동: 한 방향으로 끝까지 이동
			for (int i = 0; i < 4; i++) {
				int nr = rRook, nc = cRook;

				while (true) {
					nr += dr[i];
					nc += dc[i];

					if (nr < 0 || nc < 0 || nr >= N || nc >= M)
						break;

					queue.add(new int[] { nr, nc, rKnight, cKnight, moveCount + 1 }); // 이동 1번한 룩, 이동하지 않은 나이트 위치 큐에 삽입

				} // while
				
			} // 룩 for문

			// 나이트 이동
			for (int i = 0; i < 8; i++) {
				int nr = rKnight + dk[i][0];
				int nc = cKnight + dk[i][1];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				
				queue.add(new int[] {rRook, cRook, nr, nc, moveCount + 1});
				
			}// 나이트 for문

		} // 큐

	}// bfs
}
