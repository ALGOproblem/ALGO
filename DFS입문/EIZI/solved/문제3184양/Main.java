package 문제3184양;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char[][] arr;
	static boolean[][] visited;
	static int[] dirx = {-1, 1, 0, 0}; // 상하좌우
	static int[] diry = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("res/문제3184양.txt"));
		// 마당에서 탈출할 수 있는 칸은 어떤 영역에도 속하지 않는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0 ; i<R; i++) {
			s = br.readLine();
			for (int j = 0; j<C; j++) {
				char n = s.charAt(j);
				arr[i][j] = n;
			}
		}// 입력 받기
		int sheep = 0;
		int wolf = 0;
		
		for (int i = 0;i<R; i++) {
			for (int j = 0; j<C; j++) {
				if (arr[i][j]!='#' && arr[i][j]!='.' && visited[i][j]!=true) {
					int[] counts = dfs(i, j);
					sheep += counts[0];
					wolf += counts[1];
					
				}
			}
		}
		System.out.println(sheep+" "+wolf);
		
	}
	public static int[] dfs(int x, int y) {
		int sheep = 0;
		int wolf = 0;
		boolean isOpen = false;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		while(!queue.isEmpty() && !isOpen) {
			int[] xy = queue.poll();
			int curx = xy[0];
			int cury = xy[1];
			if (arr[curx][cury]=='o') sheep++;
			else if (arr[curx][cury]=='v') wolf++;
			for (int i = 0; i<4; i++) {
				if (curx+dirx[i] >=0 && curx+dirx[i] <arr.length && cury+diry[i] >=0 && cury+diry[i] <arr[0].length) {
					if (curx+dirx[i] ==0 || curx+dirx[i] ==arr.length-1 || cury+diry[i] ==0 || cury+diry[i]==arr[0].length-1) {
						// 끝 부분일 때
						if (arr[curx+dirx[i]][cury+diry[i]]!='#') {
							isOpen = true;
							break;
						}
					}
					
					if (arr[curx+dirx[i]][cury+diry[i]]!='#' && visited[curx+dirx[i]][cury+diry[i]]!=true) {
						queue.add(new int[] {curx+dirx[i], cury+diry[i]});
						visited[curx+dirx[i]][cury+diry[i]]=true;
					}
				}
			}// 여기까지가 for문			
		}// 여기까지가 while문
		if (isOpen) {
			return new int[] {0, 0};
		}
		else {
			if (sheep>wolf) return  new int[] {sheep, 0};
			else return  new int[] {0, wolf};
		}
	}

}


















