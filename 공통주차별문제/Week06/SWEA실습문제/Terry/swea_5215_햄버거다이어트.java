package 공통주차별문제.Week06.SWEA실습문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_5215_햄버거다이어트 {
	static BufferedReader input;
	static StringTokenizer st;

	// 정해진 칼로리 내에서 가장 맛있는 조합 햄버거 만들기!
	// 재료는 한 번만 사용 가능하다.
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(input.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			int[] cals = new int[N + 1]; // 재료는 1부터 N 까지
			int[] scores = new int[N + 1];
			int[] dp = new int[L + 1]; // 칼로리 1부터 L까지

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(input.readLine());
				scores[i] = Integer.parseInt(st.nextToken());
				cals[i] = Integer.parseInt(st.nextToken());
			}

			// 재료 선택
			for (int i = 0; i < N + 1; i++) {
				int score = scores[i];
				int cal = cals[i];

				for (int j = L; j >= cal; j--) {
					dp[j] = Math.max(dp[j], dp[j - cal] + score);
				}
			}

			System.out.println("#" + t + " " + dp[L]);
		}
	}
}
