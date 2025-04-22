package 공통주차별문제.Week08.MIRIM;

import java.util.*;

public class 백준_17144_미세먼지안녕 {
	static int R, C, T;
	static int[][] map;
	static int[] dr = {0, 1, 0, -1}; // 우, 하, 좌, 상 (시계)
	static int[] dc = {1, 0, -1, 0};
	static int lower;
	static int upper;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
			}
		}// 입력
		
		// 공기 청정기 위치 찾기
		for (int r = 0; r < R; r++) {
			if (map[r][0] == -1) {
				upper = r;
				lower = r + 1;
				break;
			}
		}
		
		for (int i = 0; i < T; i++) {
			diffuse();
			circulate();
		}
		
		int answer = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1) continue; 
				answer += map[i][j];
			}
		}
		
		System.out.println(answer);
		
		sc.close();
	}// main
	
	// 미세먼지 동시 확산, 새로운 map 만들기
	static void diffuse() {
		int[][] result = new int[R][C]; // 새로운 맵
		result[upper][0] = -1;
		result[lower][0] = -1;
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				
				if (map[r][c] <= 0) continue;
				
				int count = 0; // 확산 횟수
				int diffusion = map[r][c] / 5; //확산되는 양
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == -1) continue; 
					
					count++;
					result[nr][nc] += diffusion; // 누적으로 더해주기
				}// delta
				
				// 확산된 양 빼기
				result[r][c] += (map[r][c] - (diffusion * count));
			}
		}
		
		map = result; // 새로운 맵으로 갱신, 얕은 복사도 괜찮음
	}// diffuse
	
	// 공기 순환
	static void circulate() {
		// 반시계 순환
		for (int i = upper - 1; i > 0; i--)
			map[i][0] = map[i - 1][0];
		
		for (int j = 0; j < C - 1; j++)
			map[0][j] = map[0][j + 1];
		
		for (int i = 0; i < upper; i++) 
			map[i][C - 1] = map[i + 1][C - 1];
			
		for (int j = C - 1; j > 1; j--)
			map[upper][j] = map[upper][j - 1];
		
		map[upper][1] = 0;
		
		// 시계 순환
			
		for (int i = lower + 1; i < R - 1; i++)
			map[i][0] = map[i + 1][0];
		
		for (int j = 0; j < C - 1; j++)
			map[R - 1][j] = map[R - 1][j + 1];

		for (int i = R - 1; i > lower; i--)
			map[i][C - 1] = map[i - 1][C - 1];
			
		for (int j = C - 1; j > 1; j--)
			map[lower][j] = map[lower][j - 1];
		
		map[lower][1] = 0;
	}
	
}
