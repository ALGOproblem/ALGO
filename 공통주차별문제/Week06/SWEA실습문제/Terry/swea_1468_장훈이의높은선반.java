package 공통주차별문제.Week06.SWEA실습문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_1468_장훈이의높은선반 {
	static BufferedReader input;
	static StringTokenizer st;

	static int N, B;
	static int[] heights;
	static int min;

	// 재귀 돌면서 점원 선택, 미선택으로 탑 높이 구하기
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(input.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			heights = new int[N];
			min = 0;

			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < N; i++) {
				int target = Integer.parseInt(st.nextToken());
				heights[i] = target;

				// 최대 높이로 초기화
				min += target;
			}

			recur(0, 0);

			System.out.println("#" + t + " " + (min - B));

		} // test case
	} // main

	static void recur(int level, int height) {
		// 탈출
		if (level == N) {
			if (height >= B) {
				min = Math.min(min, height);
			}
			return;
		}

		// 재귀
		// 현재 점원 포함
		recur(level + 1, height + heights[level]);

		// 현재 점원 미포함
		recur(level + 1, height);
	}
}
