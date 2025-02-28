package 문제11725트리의부모찾기;

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
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/문제11725트리의부모찾기.txt"));
		// 마당에서 탈출할 수 있는 칸은 어떤 영역에도 속하지 않는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int N = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N+1];
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
		for (int i=2; i<N+1; i++) {
			System.out.println(dfs(i));
		}
		

	}
	public static int dfs(int A) {
		for (int num : arr[A]) {
			// 하나만 연결되어 있다 (A가 leaf 노드)  & 1이 있다 (무조건 1이 최상위이므로 부모 노드)
			if (num==1 && arr[A].size()==1) return num;
			else {
				for (int i = 0; i<arr[A].size(); i++) { // i는 부모 노드 후보자들
					Stack<Integer> stack = new Stack<>();
					boolean[] visited = new boolean[arr.length];
					stack.add(arr[A].get(i));
					visited[arr[A].get(i)] = true;
					while(!stack.isEmpty()) { // 1을 찾기 위한 여정
						int n = stack.pop();
						visited[n] = true;
						for (int j = 0; j<arr[n].size(); j++) {
							if (arr[n].get(j)==1) return arr[A].get(i);
							if (visited[arr[n].get(j)]!=true) stack.add(arr[n].get(j));
						}
					}
				}
				
			}
		}
		return -1;
	}

}
