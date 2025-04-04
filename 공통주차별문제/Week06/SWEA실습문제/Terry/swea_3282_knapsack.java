package 공통주차별문제.Week06.SWEA실습문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_3282_knapsack {
	static BufferedReader input;
	static StringTokenizer st;

	// 정순으로 dp를 순회하면 물건을 계속 넣게 됨.
	// 역순으로 조회하면서 물건을 한 번만 넣는다.

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		// input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(input.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] sizes = new int[N + 1]; // N은 1부터
			int[] costs = new int[N + 1];

			int[] dp = new int[K + 1]; // 가방 부피 K 까지

			for (int i = 1; i < N + 1; i++) {
				st = new StringTokenizer(input.readLine());
				sizes[i] = Integer.parseInt(st.nextToken());
				costs[i] = Integer.parseInt(st.nextToken());
			}

			// dp 계산
			// 물건 인덱스
			for (int i = 1; i < N + 1; i++) {
				// 배낭 용량
				// 배낭 용량을 큰 것부터 작은 것으로 순회해서 중복 피하기
				for (int j = K; j >= sizes[i]; j--) {
					// 현재 값을 물건을 넣기 전 + 물건의 값과 비교
					dp[j] = Math.max(dp[j], dp[j - sizes[i]] + costs[i]);
				}
			}

			System.out.println("#" + t + " " + dp[K]);
		}

	}
}
