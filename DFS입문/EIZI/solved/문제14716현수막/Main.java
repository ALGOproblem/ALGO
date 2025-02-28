package 문제14716현수막;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static int[][] arr;
	static boolean[][] visited;
	static int[] dirx = {-1, 1, 0, 0, -1, -1, 1, 1}; // 상하좌우 + 대각선
	static int[] diry = {0, 0, -1, 1, -1, 1, -1, 1};
	public static void main(String[] args) throws IOException {
		// 상하좌우 대각선으로 인접하여 있다면 한 개의 글자
		System.setIn(new FileInputStream("res/문제14716현수막.txt"));
		// 마당에서 탈출할 수 있는 칸은 어떤 영역에도 속하지 않는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		arr = new int[M][N];
		visited = new boolean[M][N];
		for (int i = 0; i<M; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			for (int j = 0; j<N; j++) {
				int k = Integer.parseInt(st.nextToken());
				arr[i][j] = k;
			}
		}// 입력 받기
		int count = 0;
		for (int i = 0; i<M; i++) {
			for (int j = 0; j<N; j++) {
				if (arr[i][j]==1 && visited[i][j]!=true) {
					dfs(i, j);
					count++;
				}
			}
			
		}
		System.out.println(count);
	}
	public static void dfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		while(!queue.isEmpty()) {
			int[] xy = queue.poll();
			int curx = xy[0];
			int cury = xy[1];
			for (int i = 0; i<8; i++) {
				if (curx+dirx[i]>=0 && curx+dirx[i]<arr.length && cury+diry[i]>=0 && cury+diry[i] < arr[0].length) {
					if (arr[curx+dirx[i]][cury+diry[i]]==1 && visited[curx+dirx[i]][cury+diry[i]]!=true) {
						queue.add(new int[] {curx+dirx[i], cury+diry[i]});
						visited[curx+dirx[i]][cury+diry[i]]=true;
					}
				}
			}
		}
	}

}
