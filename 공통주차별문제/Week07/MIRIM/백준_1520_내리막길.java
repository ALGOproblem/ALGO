package 공통주차별문제.Week07.MIRIM;

import java.util.Arrays;
import java.util.Scanner;

public class 백준_1520_내리막길 {
	
	static int M, N;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//내리막길로만 이동하는 경로의 개수
		
		M = sc.nextInt(); // 행
		N = sc.nextInt(); // 열
	
		map = new int[M][N];
		dp = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}// 입력 
		
		for (int i = 0; i < M; i++)
			Arrays.fill(dp[i], -1);

		System.out.println(dfs(0, 0));
		
		sc.close();
	}// main
	
	static int dfs(int r, int c) {
		if (r == M - 1 && c == N - 1) return 1; // 도착!
		if (dp[r][c] != -1) return dp[r][c]; // 방문한 적 있음
		
		dp[r][c] = 0; // 처음 방문
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nc < 0 || nr >= M || nc >= N) continue;
			if (map[nr][nc] >= map[r][c]) continue;
			
			dp[r][c] += dfs(nr, nc); // 도착지까지 가는 경로의 총 합
		}
		
		return dp[r][c];
	}// dfs
}
