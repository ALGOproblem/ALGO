package 문제11725트리의부모찾기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
public class Main2 {
	static List<Integer>[] arr;
	static boolean[] visited;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/문제11725트리의부모찾기.txt"));
		// 마당에서 탈출할 수 있는 칸은 어떤 영역에도 속하지 않는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int N = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N+1];
		visited = new boolean[N+1];
		parent = new int[N+1];
		for (int i = 0; i<N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		for (int i = 0; i<N-1; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			arr[A].add(B);
			arr[B].add(A);
		}
		
		dfs(1);
		for (int i=2; i<N+1; i++) {
			System.out.println(parent[i]);
		}
		

	}
	public static void dfs(int A) {
		Stack<Integer> stack = new Stack<>();
		stack.add(A);
		visited[A] = true;
		while (!stack.isEmpty()) {
			int num = stack.pop();
			for (int i : arr[num]) {
				if (!visited[i]) {
					parent[i] = num;
					stack.add(i);
					visited[i] = true;
				}
			}
		}
	}

}
