package 문제3187양치기꿍;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	static char[][] arr;
	static boolean[][] visited;
	static int[] dirX = {-1, 1, 0, 0}; // 상하좌우
	static int[] dirY = {0, 0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/문제3187양치기꿍.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test <=T; test++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int R = Integer.parseInt(st.nextToken()); // 3<=R<=250
			int C = Integer.parseInt(st.nextToken()); // 3<=R<=250
			arr = new char[R][C];
			visited = new boolean[R][C]; 
			for (int i = 0; i<R; i++) {
				String ss = br.readLine();
				for (int j = 0; j<C; j++) {
					arr[i][j] = ss.charAt(j);
				}
			} // 여기까지 입력받기
			int sheep = 0;
			int wolf = 0;
			for (int i = 0; i<R; i++) {
				for (int j = 0; j<C; j++) {
					if (arr[i][j]!='#' && visited[i][j] !=true) {
						int[] count = findSurvive(i, j);
						sheep += count[0];
						wolf += count[1];
					}
				}
			}// 탐색하기
			System.out.println(sheep+" "+wolf);
			
		}
			br.close();
	}
	public static int[] findSurvive(int x, int y) {
		Stack<int[]> stack = new Stack<>();
		int sheep = 0;
		int wolf = 0;
		boolean isOpen = false;
		stack.push(new int[] {x, y});
		while(!stack.isEmpty()) {
			int[] xy = stack.pop();
			int cur_x = xy[0];
			int cur_y = xy[1];
			// (x, y)는 현재 탐색 중인 칸
			// 1) 우선 테두리에 닿았는지 판단
			if (x == 0 || x == arr.length - 1 || y == 0 || y == arr[0].length - 1) {
			    // 2) 그 칸이 울타리가 아니라면
			    if (arr[x][y] != '#') {
			        // 3) 이 구역은 바깥에 열려있다고 판단
			        isOpen = true;
			    }
			}
			visited[cur_x][cur_y] = true;
			switch(arr[cur_x][cur_y]) {
			case 'v' : wolf++;
			break;
			case 'k' : sheep++;
			break;
			}
			// 이 부분 틀림
			// 만약 테두리라면 isOPEN = true;
			
			
			for (int i = 0; i<4; i++) {
				if (cur_x+dirX[i] < arr.length && cur_x+dirX[i] >= 0 && cur_y+dirY[i] < arr[0].length && cur_y+dirY[i] >= 0) {
					if (arr[cur_x+dirX[i]][cur_y+dirY[i]] != '#' && visited[cur_x+dirX[i]][cur_y+dirY[i]]!=true) {// 울타리가 아닐 때 
						stack.push(new int[] {cur_x + dirX[i], cur_y + dirY[i]});
					}
				}
			}
		}
		if (!isOpen) {
			if (sheep > wolf) wolf = 0;
			else sheep = 0;	
		}
		return new int[] {sheep, wolf};
		
	}

}
