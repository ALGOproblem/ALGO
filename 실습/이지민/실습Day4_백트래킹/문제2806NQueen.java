package 실습Day4_백트래킹;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문제2806NQueen {
/* N*N 보드에 N개의 퀸을 서로 다른 퀸이 공격하지 못하게 놓는 경우의 수
 * 
 * (1) int[] checked 배열
 * 		0 방문가능 1 (0행에서 방문표시) 2(1행에서 방문표시) ... N(N-1행에서방문표시)
 * (2) backtracking() 함수
 * 		A. 종료 (if idx(행길이) == N) return;
 * 		B. for문을 돌리면서 각 칸마다 checked == 0일 경우 
 * 			가. checked[]가 1이상이라면 전부 +1씩 더함
 *  * 		나. 퀸 놓고 아래 3가지(대각선왼쪽,아래,대각선오른쪽)방향 체크
 * 			다. backtracking(idx+1)
 * 		C. idx가 N에 도달하지 못했음에도 check할 수 있는 칸이 없다면 checked[]가 양수일 때 전부 -1하고 return;
 */
	static int[][] checked;
	static int[][] board;
	static int N;
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/문제2806NQueen.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			checked = new int[N][N];
			board = new int[N][N];
			answer = 0;
			backtracking(0);
			System.out.println("#"+tc+" "+answer);
			
		}
		

	}
	static void backtracking(int idx) {
		if (idx==N) {
//			for (int i = 0; i<N; i++) {
//				for (int j = 0; j<N; j++) {
//					System.out.print(board[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println("-------------------");
			answer++;
			return;
		}
		for (int y = 0; y<N; y++) {
			if(checked[idx][y]==0) {
				control(true); // 우선 기존 checked 전부 +1씩
				board[idx][y] = idx; // board판에 표시
				checked[idx][y]++;
				check(idx, y);
				backtracking(idx+1);
				////////// 초기화 하는 위치는 여기임 ///////////
				board[idx][y] = 0;
				control(false); // 기존에 했던 것은 지움
				checked[idx][y]++;
			}
		}
		
	}
	static void control(boolean c) {
		int count = (c ? 1 : -1);
		for (int i = 0; i<N; i++) {
			for (int j = 0; j<N; j++) {
				if (checked[i][j] > 0) checked[i][j] += count;
			}
		}
	}
	static void check(int x, int y) {
		for (int i =0; i<N; i++) {
			if(checked[x][i]==0) checked[x][i]++; // 한 행을 전부 checked
			if(checked[i][y]==0) checked[i][y]++; // 한 열을 전부 checked
			if(x+i<N && y+i<N && checked[x+i][y+i]==0) checked[x+i][y+i]++; // 대각선 오른쪽
			if(x+i<N && y-i>=0 && checked[x+i][y-i]==0) checked[x+i][y-i]++; // 대각선 왼쪽
		}
	}


}
