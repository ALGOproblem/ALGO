package 공통주차별문제.Week08.MIRIM;

import java.util.*;

public class 백준_14503_로봇청소기 {
	static int N, M;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		
		int r = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}// 입력
		
		System.out.println(clean(r, c, d));
		sc.close();
	}
	
	static int clean(int sr, int sc, int sd) {
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] {sr, sc, sd});
		int count = 0;
		
		Loop: while (!stack.isEmpty()) {
			int[] cur = stack.pop();
			
			int r = cur[0];
			int c = cur[1];
			int d = cur[2];
			
			// 1. 현재 방 청소
			if (map[r][c] == 0) {
				map[r][c] = 2;
				count++;
			}
			
			// 2. 상하좌우 탐색
			for (int i = 1; i < 5; i++) {
				int nd = (d + 4 - i) % 4;
				int nr = r + dr[nd];
				int nc = c + dc[nd];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				
				if (map[nr][nc] == 0) {
					stack.push(new int[] {nr, nc, nd});
					continue Loop;
				}
			}
			
			// 후진 탐색
			int nd = (d + 2) % 4;
			int nr = r + dr[nd];
			int nc = c + dc[nd];
			
			if (nr < 0 || nc < 0 || nr >= N || nc >= M) break;
			if (map[nr][nc] == 1) break;
			
			stack.push(new int[] {nr, nc, d});
			
		}//while
		
		return count;
	}

}
