package 공통주차별문제.Week05.SWEA실습문제.TERRY;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea3289서로소집합 {
	static BufferedReader input;
	static StringTokenizer st;

	static StringBuilder result;
	static int[] p;
	static int N, M;

	// 1. 0일 경우 union
	// 2. 1일 경우 find
	public static void main(String[] args) throws NumberFormatException, IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(input.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			p = new int[N + 1];

			// make
			for (int i = 0; i < N + 1; i++) {
				p[i] = i;
			}

			result = new StringBuilder("#" + t + " ");
			
			// 연산하기
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(input.readLine());
				int order = Integer.parseInt(st.nextToken());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());

				// 0이면 union
				if (order == 0) {
					union(B, A);
					continue;
				}
				
				// 1이면 A & B find
				// 같은 집합이면 1
				if (findSet(A) == findSet(B)) {
					result.append(1);
					continue;
				}

				// 같은 집합 아니면
				result.append(0);

			}

			System.out.println(result);
		}
	}

	// 부모 찾아요
	static int findSet(int x) {
		if (p[x] != x) {
			p[x] = findSet(p[x]);
		}

		return p[x];
	}

	static void union(int x, int y) {
		// 부모 찾아요
		x = findSet(x);
		y = findSet(y);

		// 합쳐요
		p[y] = x;
	}
}
