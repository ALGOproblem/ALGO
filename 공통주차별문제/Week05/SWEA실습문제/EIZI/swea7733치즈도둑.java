package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class swea7733치즈도둑 {
	static int[][] arr;
	static boolean[][] visited;
	static int[] dirx = {0, 0, -1, 1};//상하좌우
	static int[] diry = {1, -1, 0, 0};
	static int N;
	static int cntCheese;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/swea7733치즈도둑.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			for (int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}//입력
			int maxAns = 1;
			for (int i = 0; i<100; i++) {
				if (maxAns<findMaxCheese()) {
					maxAns = findMaxCheese();
				}
//				if (maxAns>findMaxCheese()) break; // 이 조건 없어야함!
				eatCheese();
			}
			System.out.println("#"+test+" "+maxAns);
		}
	}
	static void eatCheese() {
		for (int i = 0; i<N; i++) {
			for (int j = 0; j<N; j++) {
				arr[i][j]--;
			}
		}
	}
	static void dfs(int x, int y) {
		Stack<int[]> stack = new Stack<>();
		stack.add(new int[] {x, y});
		visited[x][y] = true;
		while(!stack.isEmpty()) {
			int[] cur = stack.pop();
			for (int i = 0; i<4; i++) {
				if(isOk(cur[0]+dirx[i], cur[1]+diry[i])) {
					stack.add(new int[] {cur[0]+dirx[i], cur[1]+diry[i]});
					visited[cur[0]+dirx[i]][cur[1]+diry[i]] = true;
				}
			}
		}
		cntCheese++;
	}
	static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i<4; i++) {
				if(isOk(cur[0]+dirx[i], cur[1]+diry[i])){
					queue.add(new int[] {cur[0]+dirx[i], cur[1]+diry[i]});
					visited[cur[0]+dirx[i]][cur[1]+diry[i]] = true;
				}
			}
		}
		cntCheese++;
	}
	static boolean isOk(int x, int y) {
		if (x<0 || y<0 || x>=N || y>=N) return false;//범위
		if (arr[x][y]<=0) return false;//이어진치즈
		if (visited[x][y]) return false;//방문여부
		return true;
	}
	static int findMaxCheese() {
		visited = new boolean[N][N];
		cntCheese = 0;
		for (int i = 0; i<N; i++) {
			for (int j = 0; j<N; j++) {
				if (arr[i][j]>0 && !visited[i][j]) {//이어져있는 치즈
					bfs(i, j);
				}
			}
		}
//		System.out.println(cntCheese);
		return cntCheese;
	}

}
