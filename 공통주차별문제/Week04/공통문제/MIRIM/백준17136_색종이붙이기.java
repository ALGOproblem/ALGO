package 공통주차별문제.Week04.공통문제.MIRIM;

import java.util.Scanner;

public class 백준17136_색종이붙이기 {
    static int[][] map = new int[10][10];
	static int[] amount = { 0, 5, 5, 5, 5, 5 };// 1~5 길이의 색종이 남은 개수
	static int minCount;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = sc.nextInt();
			}
		} // 입력

		minCount = Integer.MAX_VALUE;

		dfs(0, 0, 0);
		
		if (minCount == Integer.MAX_VALUE)
			minCount = -1;
		
		System.out.println(minCount);

		sc.close();
	}// main

	// 종이 5 -> 1로 거꾸로 붙이기
	static void dfs(int r, int c, int count) {
		if (r >= 9 && c >= 10) {
			if (isAllCovered()) 
				minCount = Math.min(count, minCount);
			return;
		}
		
		// 가지치기
		if (minCount <= count) return;
		
		// 아래 행으로 이동
		if (c >= 10) {
			dfs(r + 1, 0, count);
			return;
		}
		
		if (map[r][c] == 0) {
			dfs(r, c + 1, count);
			return;
		}
		
		
		for (int idx = 5; idx > 0; idx--) {
			// 해당 종이를 붙일 수 있는지 검사 -> 붙이기, 더 작은 종이 붙이기
			if (amount[idx] > 0 && isValid(r, c, idx)) {
				// 1. 해당 종이 붙이기 -> 다른 영역으로 넘어가기
				select(r, c, idx, 0);
				amount[idx]--;
				
				dfs(r, c + 1, count + 1);
				
				// 2. 원상복귀, 작은 종이 붙이기
				amount[idx]++;
				select(r, c, idx, 1);
			}
		}
	}// dfs
	
	static boolean isValid(int r, int c, int idx) {
		for (int i = 0; i < idx; i++) {
			int nr = r + i;
			
			for (int j = 0; j < idx; j++) {
				int nc = c + j;
				if (nr < 0 || nc < 0 || nr >= 10 || nc >= 10) return false;
				
				if (map[nr][nc] == 0) return false;
			}
		}
		return true;
	}
	
	static void select(int r, int c, int idx, int state) {
		for (int i = 0; i < idx; i++) {
			for (int j = 0; j < idx; j++) {
				map[r + i][c + j] = state;
			}
		}
	}
	
	static boolean isAllCovered() {
	    for (int i = 0; i < 10; i++) {
	        for (int j = 0; j < 10; j++) {
	            if (map[i][j] == 1) return false;
	        }
	    }
	    return true;
	}
}
