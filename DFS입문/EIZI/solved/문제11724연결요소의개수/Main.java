package 문제11724연결요소의개수;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] arr;
	static int N;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/문제11724연결요소의개수.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		N = Integer.parseInt(st.nextToken()); // 1<=N<=1000
		int M = Integer.parseInt(st.nextToken()); // 0<=M<=N(N-1)/2 (하나도 없거나 전부 연결하거나 가능)
		arr = new ArrayList[N + 1];
		visited = new boolean[N+1];
		for (int i = 0; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			arr[A].add(B);
			arr[B].add(A);
		} // 입력
		int count = 0;
		for (int i =1; i<=N; i++) {
			if (visited[i]!=true && !arr[i].isEmpty()) {
				dfs(i);
				count++;
			}
			else if (visited[i]!=true && arr[i].isEmpty()) {
				count++; // 어느 노선에도 연결되지 않은 경우
			}
		}
		
		System.out.println(count);
		

	}
	public static void dfs(int A) {
		Stack<Integer> stack = new Stack<>();
		stack.add(A);
		visited[A] = true;
		while(!stack.isEmpty()) {
			int num = stack.pop();
			for (int n : arr[num]) { // 스택에 집어 넣고 방문 처리
				if (visited[n] !=true) { // 방문한 적이 없다면
					stack.add(n);
					visited[n] = true;					
				}
			}
		}
		return;
	}

}
