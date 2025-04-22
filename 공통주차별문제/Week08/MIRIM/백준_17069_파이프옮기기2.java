package 공통주차별문제.Week08.MIRIM;

import java.util.*;

public class 백준_17069_파이프옮기기2 {
	static int N;
	static int[][] map;
	static long[][][] dp; //int형으로 안하면 틀림....ㅠㅠㅠ
	static int[] dr = {0, 1, 1}; // 가로, 세로, 대각선
	static int[] dc = {1, 0, 1};
	static int[][] direction = { {0, 2}, {1, 2}, {0, 1, 2}}; // 이동 가능한 방향

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		} // 입력
		
		dp = new long[N][N][3];
		for (long[][] arr1 : dp) {
			for (long[] arr2 : arr1) {
				Arrays.fill(arr2, -1);
			}
		}
		
		System.out.println(dfs(0, 1, 0));
		sc.close();
	}// main
	
	static long dfs(int r, int c, int dir) {
		// 종료 조건
		if (r == N - 1 && c == N - 1) return 1;
		
		// 방문 체크
		if (dp[r][c][dir] != -1) return dp[r][c][dir];
		
		// 계산위해 초기화
		dp[r][c][dir] = 0;

		// 이동 가능한 경로 계산
		for (int d : direction[dir]) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
			
			if (d == 2) {
				if (map[r + 1][c] == 1 || map[r][c + 1] == 1 || map[r + 1][c + 1] == 1) continue;
			} else {
				if (map[nr][nc] == 1) continue;
			}
			
			dp[r][c][dir] += dfs(nr, nc, d); // 경로 누적
		}

		return dp[r][c][dir];
	} // dfs
	
}
