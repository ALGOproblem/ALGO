package 공통주차별문제.Week06.공통문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1463_1로만들기 {
	static BufferedReader input;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
	//	input = new BufferedReader(new FileReader("input.txt"));

		// 1 <= N <= 1_000_000
		int N = Integer.parseInt(input.readLine());

		int[] dp = new int[N + 1];

		dp[1] = 0;

		for (int i = 2; i < N + 1; i++) {
			dp[i] = dp[i - 1] + 1;
			
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
		}
		
		System.out.println(dp[N]);
	}
}
