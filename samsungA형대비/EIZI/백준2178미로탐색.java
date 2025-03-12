package week3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * N*M 미로. 0.0시작해서 N.M도착하는 최단 거리를 구하라.
 * 단 미로는 0, 1로 이루어져 있으며 1이 이동 가능
 * BFS를 쓰자
 */
public class 백준2178미로탐색 {
	static int N;
	static int M;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/백준2178미로탐색.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i<N; i++) {
			String s = br.readLine();
			for (int j = 0; j<M; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}//arr입력
		System.out.println(bfs(0,0));
		
		
	}
	public static int bfs(int x, int y) {
		int count = 1;
		int level = 0;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y}); // 첫번째 것은 넣기
		visited[x][y] = true;
		while(!queue.isEmpty()) {
			for(int j = 0; j<level; j++) {
				int[] xy = queue.poll();
				int curx = xy[0];
				int cury = xy[1];
				for (int i = 0; i<4; i++) {
					if (curx + dx[i] >=0 && curx + dx[i]<N && cury +dy[i] >=0 && cury + dy[i]<M) {
						if (arr[curx+dx[i]][cury+dy[i]]==1 && !visited[curx+dx[i]][cury+dy[i]]) {
							if(curx+dx[i] == N-1 && cury+dy[i]==M-1) return count;
							queue.add(new int[] {curx+dx[i], cury+dy[i]});
							visited[curx+dx[i]][cury+dy[i]] = true;
						}
					}
				}
			}
			level = queue.size();
			count++;
		}
		return -1;
	}

}
