package 실습.김미림.APS응용_Day12_동적계획법;

import java.util.Scanner;

public class swea_9296_피보나치수열 {
	static int[] memo;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] input = new int[5];
		int max = 0;
		for (int i = 0; i < 5; i++) {
			input[i] = sc.nextInt();
			max = Math.max(max, input[i]);
		}// 입력
		
		dp(max);
		
		for (int i = 0; i < 5; i++) {
			System.out.println(memo[input[i]]);
		}
	}
	
	static void dp(int max) {
		memo = new int[max + 1];
		memo[0] = 0;
		memo[1] = 1;
		
		for (int i = 2; i <= max; i++) {
			memo[i] = memo[i - 1] + memo[i - 2];
		}
	}
}
