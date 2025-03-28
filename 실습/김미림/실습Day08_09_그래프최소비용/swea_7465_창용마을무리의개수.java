package 실습.김미림.실습Day08_09_그래프최소비용;

import java.util.*;

public class swea_7465_창용마을무리의개수 {
	static int[] parent;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}
			
			
			for (int i = 0; i < M; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				
				union(x, y);
			}
			
			Set<Integer> set = new HashSet<>();
			for (int i = 1; i <= N; i++) {
				find(i);
				if (!set.contains(parent[i])) {
					set.add(parent[i]);
				}
			}
			
			System.out.printf("#%d %d\n", tc, set.size());
			
		}// tc
	}// main

	static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if (px != py) {
			parent[py] = px; 
		}
	
	}
	
}
