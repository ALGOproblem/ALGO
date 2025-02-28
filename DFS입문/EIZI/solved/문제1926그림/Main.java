package 문제1926그림;

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
	static int[] dirx = {-1, 1, 0, 0}; // 상하좌우
	static int[] diry = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/문제1926그림.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i<N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			for (int j = 0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 받기
		LinkedList<Integer> pictures = new LinkedList<>();
		for (int i = 0; i<N; i++) {
			for (int j = 0; j<M; j++) {
				if (arr[i][j]==1 && visited[i][j]!=true) {
					int n = bfs(i, j);
					pictures.add(n);
				}
			}
		}
		Collections.sort(pictures, Collections.reverseOrder());
		if (pictures.isEmpty()) {
		    System.out.println(0);
		    System.out.println(0);
		} else {
		    System.out.println(pictures.size());
		    System.out.println(pictures.get(0));
		}

		br.close();

	}
	public static int bfs (int x, int y) {
		int area = 0;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		while(!queue.isEmpty()) {
			area++;
			int[] xy = queue.poll();
			int curx = xy[0];
			int cury = xy[1];
			for (int i = 0; i<4; i++) {
				if (curx+dirx[i] >=0 && curx+dirx[i]<arr.length && cury+diry[i] >=0 && cury+diry[i] < arr[0].length) {
					if (arr[curx+dirx[i]][cury+diry[i]]==1 && visited[curx+dirx[i]][cury+diry[i]]!=true) {
						queue.add(new int[] {curx+dirx[i],cury+diry[i]});
						visited[curx+dirx[i]][cury+diry[i]]=true;
					}
				}
			}
		} // while문
		return area;
	}

}
