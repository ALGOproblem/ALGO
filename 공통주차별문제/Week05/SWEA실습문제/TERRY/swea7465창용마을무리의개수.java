package 공통주차별문제.Week05.SWEA실습문제.TERRY;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class swea7465창용마을무리의개수 {
	static StringTokenizer st;
	static BufferedReader input;

	static int[] p;
	static int[] rank;

	// 몇 개의 무리가 존재? -> 대표 몇개?
	// 0. make, find 만들기
	// 1. union 한다.
	// 2. 대표들 다 set 에 넣고 size 출력
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(input.readLine());
			int N = Integer.parseInt(st.nextToken()); // 정점
			int M = Integer.parseInt(st.nextToken()); // 간선

			p = new int[N + 1];
			rank = new int[N + 1];

			// make
			for (int i = 0; i < N + 1; i++) {
				p[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(input.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());

				union(B, A);
			}

			Set<Integer> set = new HashSet<>();
			// 대표 세기
			// 0 빼고 세자
			for (int i = 1; i < p.length; i++) {
				set.add(findSet(p[i]));
			}

			System.out.println("#" + t + " " + set.size());

		}
	} // main

	static int findSet(int x) {
		if (x != p[x]) {
			p[x] = findSet(p[x]);
		}

		return p[x];
	}

	static void union(int x, int y) {
		x = findSet(x);
		y = findSet(y);

		if (rank[x] > rank[y]) {
			p[y] = x;
			return;
		}

		p[x] = y;
	}
}
