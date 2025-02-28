package 문제24480깊이우선탐색2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
public class Main {
	static List<Integer>[] arr;
	static boolean[] visited;
	static List<Integer> all;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/문제24480깊이우선탐색2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int N = Integer.parseInt(st.nextToken()); // 정점의 수 5<=N<=100,000
		int M = Integer.parseInt(st.nextToken()); // 간선의 수 1<=M<=200,000
		int R = Integer.parseInt(st.nextToken()); // 시작 정점 1<=R<=N
		arr = new ArrayList[N+1];
		visited = new boolean[N+1];
		all = new ArrayList<>();
		for (int i = 1; i<N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		for (int i = 0; i<M; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			all.add(A);
			all.add(B);
			arr[A].add(B);
			arr[B].add(A);
		} // 입력
		for (int i = 1; i<N+1; i++) {
			System.out.println(dfs(R, i, 1));
			Arrays.fill(visited, false);
		}
	}
	public static int dfs(int start, int end, int count) {
		if (start==end) return count;
		else {
			all.remove(start);
			visited[start] = true;
			System.out.println("start"+start+"방문처리"+arr[start]);
			for (int i = 0; i<arr[start].size(); i++) {
				if (!visited[arr[start].get(i)]) return dfs(arr[start].get(i), end, count+1);
			}
			if (all.isEmpty()) return -2; // 방문할 수 있는 모든 정점 다 방문
		}
		return count;
		
	}

}
