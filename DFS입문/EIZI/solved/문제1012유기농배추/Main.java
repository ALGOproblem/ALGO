package 문제1012유기농배추;

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
		System.setIn(new FileInputStream("res/문제1012유기농배추.txt"));
		// 마당에서 탈출할 수 있는 칸은 어떤 영역에도 속하지 않는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int T = Integer.parseInt(st.nextToken());
		for (int test=0; test<T; test++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			int N = Integer.parseInt(st.nextToken()); // 50
			int M = Integer.parseInt(st.nextToken()); // 50
			int K = Integer.parseInt(st.nextToken()); // 2500
			arr = new int [N][M];
			visited = new boolean[N][M];
			for (int i = 0; i<K; i++) {
				s = br.readLine();
				st = new StringTokenizer(s);
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				arr[A][B] = 1;
			} // 입력
			int count = 0;
			for (int i = 0; i<N; i++) {
				for (int j = 0; j<M; j++) {
					if (arr[i][j]==1 && visited[i][j]!=true) {
						bfs(i, j);
						count++;
					}
				}
			}
			System.out.println(count);
			
		}

	}
	public static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		while(!queue.isEmpty()) {
			int[] xy = queue.poll();
			int curx = xy[0];
			int cury = xy[1];
			for (int i = 0; i<4; i++) {
				if (curx+dirx[i]>=0 && curx+dirx[i]<arr.length && cury+diry[i] >= 0 && cury+diry[i] <arr[0].length) {
					if (arr[curx+dirx[i]][cury+diry[i]]==1 && visited[curx+dirx[i]][cury+diry[i]]!=true) {
						queue.add(new int[] {curx+dirx[i], cury+diry[i]});
						visited[curx+dirx[i]][cury+diry[i]] = true;
					}
				}
			}
		
		} //while
	}

}
