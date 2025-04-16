package 공통주차별문제.Week07.MIRIM;

import java.util.Arrays;
import java.util.Scanner;

public class 백준_1931_회의실배정_dp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[][] time = new int[N][2];
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			time[i][0] = sc.nextInt();
			time[i][1] = sc.nextInt();
			
			if (time[i][1] > max)
				max = time[i][1];
		}
		
		// 끝나는 시간 기준 정렬
		Arrays.sort(time, (a, b) -> {
			if (a[1] == b[1]) return Integer.compare(a[0], b[0]);
			return Integer.compare(a[1], b[1]);
		});
		
		int[] dp = new int[max + 1];
		int idx = 0;
		
		for (int i = 1; i < max + 1; i++) {
			dp[i] = dp[i - 1];
			
			// 지금 끝나는 회의 처리
			while (idx < N && time[idx][1] == i) {
				int start = time[idx][0];
				int end = time[idx][1];
				
				dp[i] = Math.max(dp[i], dp[start] + 1);
				idx++;
			}
			
		}
		
		System.out.println(dp[max]);
		
		sc.close();
	}

}

