package 공통주차별문제.Week06.SWEA실습문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_1263_사람네트워크2 {
	// 각 사람이 다른 모든 사람에게 얼마나 가까운가? -> 모든 정점 거리를 구해야 한다. -> 플로이드

	// 1. dist 배열 계산 후 자기 행 다 더하기 (0행 제외)
	// 2. 제일 낮은 값 출력

	static BufferedReader input;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(input.readLine());
			int N = Integer.parseInt(st.nextToken());

			int[][] dist = new int[N + 1][N + 1]; // 번호는 1부터
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					dist[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 초기 dist 설정
			// 직접 연결되는 경우 숫자
			// 직접 연결 안되면 MAX_VALUE 값
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (i == j) {
						continue;
					}
					if (dist[i][j] == 0) {
						dist[i][j] = Integer.MAX_VALUE;
					}
				}
			}

			for (int k = 1; k < N + 1; k++) {
				for (int i = 1; i < N + 1; i++) {
					// 출발지 - 경유지 까지 무한대일 경우 넘기기
					if (dist[i][k] == Integer.MAX_VALUE)
						continue;
					for (int j = 1; j < N + 1; j++) {
						// 경유지 - 도착지 까지 무한대일 경우 넘기기
						if (dist[k][j] == Integer.MAX_VALUE)
							continue;
						// 1. 거리 갱신
						if (dist[i][j] > dist[i][k] + dist[k][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
						}
					} // 도착지
				} // 출발지
			} // 경유지

			int min = Integer.MAX_VALUE;
			for (int i = 1; i < N + 1; i++) {
				int cnt = 0;
				// 행별로 거리 카운트
				for (int j = 1; j < N + 1; j++) {
					cnt += dist[i][j];
				}

				if (cnt == 0) {
					continue;
				}
				// 2. 가까운 사람 갱신
				if (cnt < min) {
					min = cnt;
				}
			}
			System.out.println("#" + t + " " + min);
		}

	}

}
