package 공통주차별문제.Week07.MIRIM;

import java.util.Scanner;

public class 백준_14889_스타트와링크 {
	static int N;
	static int[][] map;
	static boolean[] selected;
	static int min;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		selected = new boolean[N + 1];
		selected[1] = true;
		min = Integer.MAX_VALUE;
		dfs(2, 1);
		System.out.println(min);
		
		
		sc.close();
	}// main
	
	static void dfs(int idx, int level) {
		if (level == N / 2) {
			int first = calScore(true);
			int second = calScore(false);
			int diff = Math.abs(first - second);
			
			min = Math.min(min, diff);
			
			return;
		}
		
		if (idx == N + 1) return;
		
		if (!selected[idx]) {
			selected[idx] = true;
			dfs(idx + 1, level + 1);
			selected[idx] = false;
		}
		
		dfs(idx + 1, level);
		
		
	}// dfs
	
	static int calScore(boolean isFirst) {
		int sum = 0;
		
		for (int i = 1; i <= N; i++) {
			if (isFirst == selected[i]) {
				for (int j = i + 1; j <= N; j++) {
					if (isFirst == selected[j]) {
						sum += map[i - 1][j - 1] + map[j - 1][i - 1];
					}
				}
			}
		}
		
		return sum;
	}
}
