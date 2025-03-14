import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BattleField_1873 {
	static int H; // 행의 개수
	static int W; // 열의 개수
	static char[][] map;
	static int N; // 명령 개수
	static String command;
	static Map<Character, int[]> status = new HashMap<>(); // 사용자 형태
	static int r;
	static int c;
	static char userStatus;

	public static void main(String[] args) {
		status.put('^', new int[] {-1, 0});
		status.put('v', new int[] {1, 0});
		status.put('<', new int[] {0, -1});
		status.put('>', new int[] {0, 1});
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			H = sc.nextInt();
			W = sc.nextInt();
			sc.nextLine();
			
			map = new char[H][W];
			r = 0; 
			c = 0;
			userStatus = '0';
			
			for (int i = 0; i < H; i++) {
				String str = sc.nextLine();
				
				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j);
					
					if (status.containsKey(map[i][j])) {
						r = i;
						c = j;
						userStatus = map[i][j];
					}
				}
			}// 입력
			
			N = sc.nextInt();
			command = sc.next();
			
			stage(0);
			
			System.out.printf("#%d ", tc);
			for (int i = 0; i < H; i++) {
				 for (int j = 0; j < W; j++) {
					 System.out.print(map[i][j]);
				 }
				 System.out.println();
			}// 출력
			
		}// tc
		sc.close();
	}// main

	static void stage(int idx) {
		if (idx == N) return; // 종료조건
		
		char comm = command.charAt(idx);
		
		if (comm == 'S') {
			int[] dir = status.get(userStatus);
			bomb(r, c, dir);
			
		}  else {
			switch(comm) {
			case 'U':
				userStatus =  '^';
				map[r][c] = userStatus;
				break;
			case 'D':
				userStatus = 'v';
				map[r][c] = userStatus;
				break;
			case 'L':
				userStatus = '<';
				map[r][c] = userStatus;
				break;
			case 'R':
				userStatus = '>';
				map[r][c] = userStatus;
			}
			
			int[] dir = status.get(userStatus);
			go(dir);
		}
		
		stage(idx + 1);
	}
	
	static void bomb(int r, int c, int[] dir) {
		int nr = r + dir[0];
		int nc = c + dir[1];
		
		if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == '#') return;
		
		if (map[nr][nc] == '*') {
			map[nr][nc] = '.';
			return;
		}
		
		bomb(nr, nc, dir);
	}// bomb
	
	static void go(int[] dir) {
		int nr = r + dir[0];
		int nc = c + dir[1];
		
		if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == '#') return;
		
		if (map[nr][nc] == '.') {
			map[r][c] = '.';
			map[nr][nc] = userStatus;
			r = nr; c = nc;
		}
	}// 이동
			
}
