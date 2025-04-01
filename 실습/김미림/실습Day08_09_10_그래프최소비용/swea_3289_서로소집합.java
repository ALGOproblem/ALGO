package 실습.김미림.실습Day08_09_10_그래프최소비용;

import java.util.*;

public class swea_3289_서로소집합 {
	static int[] parent;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			parent = new int[n + 1];
			for (int i = 0; i <= n; i++) {
				parent[i] = i;
			}
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < m; i++) {
				int com = sc.nextInt();
				int x = sc.nextInt();
				int y = sc.nextInt();
				
				if (com == 0) {
					union(x, y);
					continue;
				}
				
				int px = find(x);
				int py = find(y);
				if (px == py) sb.append("1");
				else sb.append("0");
			}
			
			System.out.printf("#%d %s\n", tc, sb.toString());
		}//tc
	}//main

	static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if (px != py) parent[py] = px;
	}
}
