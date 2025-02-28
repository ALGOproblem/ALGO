package 문제1260DFS와BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static List<Integer>[] arr;
	static boolean[] visited;
	static boolean[] visited2;
	static List<Integer> nums;
	public static void main(String[] args) throws IOException {
		// 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다
		// 입력으로 주어지는 간선은 양방향이다
		// 첫째줄에 DFS 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력
		// 단 방문할 수 있는 정점이 여러 개인 경우엔 정점 번호가 작은 것부터 방문한다.
		
		System.setIn(new FileInputStream("res/문제1260DFS와BFS.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int N = Integer.parseInt(st.nextToken()); // 정점의 수 5<=N<=1,000
		int M = Integer.parseInt(st.nextToken()); // 간선의 수 1<=M<=10,000
		int V = Integer.parseInt(st.nextToken()); // 시작 정점 1<=R<=N
		arr = new List[N+1];
		visited = new boolean[N+1];
		visited2 = new boolean[N+1];
		nums = new LinkedList<Integer>();
		for (int i = 1; i<N+1; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i<M; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if (!arr[A].contains(B)) {
				arr[A].add(B);
				arr[B].add(A);
				nums.add(A);
				nums.add(B);
			}
		}
		
		dfs(V);
		System.out.println();
		bfs(V);
	}
	public static void bfs(int node) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(node);
		visited[node] = true;
		while(!queue.isEmpty()) {
			int next = queue.poll();
			System.out.print(next+" ");
			Collections.sort(arr[next]); // 문제 조건 꼼꼼하게 읽지 않음
			for (int n : arr[next]) {
				if (!visited[n]) {
					queue.add(n);
					visited[n] = true;
				}
			}
		}
		System.out.println();
	}
	public static void dfs(int node) { // LinkedList의 remove 메소드를 헷갈림
		if (nums.isEmpty()) return;
		visited2[node] = true;
		if (nums.indexOf(node)!=-1) nums.remove(nums.indexOf(node));
		System.out.print(node+" ");
		Collections.sort(arr[node]);
		for (int i : arr[node]) {
			if (!visited2[i]) dfs(i);
		}
	}

}
