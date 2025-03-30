package 공통주차별문제.Week05.SWEA실습문제.TERRY;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class swea1251하나로 {
	static BufferedReader input;
	static StringTokenizer st;

	static int[] p; // 대표
	static double[] cost; // 간선 비용

	static int N; // 섬 개수
	static int[][] matrix; // 섬 위치
	static List<long[]> edges; // 간선과 비용

	// 0. 섬의 위치를 이용해 간선 가중치 구하기
	// 1. 간선 비용 오름차순 정렬
	// 2. 간선 N-1개 선택
	// 3. find 후 부모가 같으면 넘어가고, 다르면 union 후 비용 저장
	// 4. Double로 간선 비용^2 과 E 계산

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(input.readLine());

			p = new int[N];
			cost = new double[N - 1];

			matrix = new int[N][2];
			edges = new ArrayList<>();

			// 섬 번호는 0부터
			// 섬 위치 세팅
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(input.readLine());
				for (int j = 0; j < N; j++) {
					matrix[j][i] = Integer.parseInt(st.nextToken());
				}
			}

			double E = Double.parseDouble(input.readLine());

			// 0. 섬 사이의 간선 가중치 구하기
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					long dist = getDist(i, j);
					edges.add(new long[] { i, j, dist });
				}
			}

			// 1. 오름차순 정렬
			Collections.sort(edges, new Comparator<long[]>() {

				@Override
				public int compare(long[] o1, long[] o2) {
					return Long.compare(o1[2], o2[2]);
				}
			});

			// make
			for (int i = 0; i < N; i++) {
				p[i] = i;
			}

			double result = 0;

			// union & find
			// 2. 간선 선택
			int edgeCnt = 0;
			for (int i = 0; i < edges.size(); i++) {
				if (edgeCnt == N - 1) {
					break;
				}

				int px = findSet((int) edges.get(i)[0]); // 처음 섬
				int py = findSet((int) edges.get(i)[1]); // 둘째 섬

				// 3. 부모 같으면 집가
				if (px == py) {
					continue;
				}
				// 4. 부모 달라요? union
				union(px, py);
				result += edges.get(i)[2];
				edgeCnt++;
			}

			System.out.println("#" + t + " " + Math.round(result * E));
		}

	}

	static long getDist(int x, int y) {
		long dx = matrix[x][0] - matrix[y][0];
		long dy = matrix[x][1] - matrix[y][1];

		return dx * dx + dy * dy;
	}

	static int findSet(int x) {
		if (p[x] != x) {
			p[x] = findSet(p[x]);
		}

		return p[x];
	}

	static void union(int x, int y) {
		p[y] = x;
	}
}
