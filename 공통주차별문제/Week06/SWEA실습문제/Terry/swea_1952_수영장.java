package 공통주차별문제.Week06.SWEA실습문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_1952_수영장 {
	static BufferedReader input;
	static StringTokenizer st;

	// 1. 이전 값과 비교해서 min 갱신해야하기 때문에 1년치로 초기화한다. 
	// 2. 1일부터 차례대로 dp 진행
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(input.readLine());

			// 비용 세팅
			int[] costs = new int[4];
			for (int i = 0; i < costs.length; i++) {
				costs[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(input.readLine());

			int[] days = new int[13]; // 12월 이용 계획
			for (int i = 1; i < days.length; i++) {
				days[i] = Integer.parseInt(st.nextToken());
			}

			int[] dp = new int[13];

			// 1. 1년치로 초기화
			Arrays.fill(dp, costs[3]);
			dp[0] = 0;

			// 2. 1일부터 차례대로 dp
			for (int i = 1; i < 13; i++) {
				// 1일
				dp[i] = Math.min(dp[i], dp[i - 1] + days[i] * costs[0]);

				// 1개월
				dp[i] = Math.min(dp[i], dp[i - 1] + costs[1]);

				// 3개월
				if (i >= 3) {
					dp[i] = Math.min(dp[i], dp[i - 3] + costs[2]);
				}
			}

			System.out.println("#" + t + " " + dp[12]);
		}
	}
}
