package 공통주차별문제.Week05.SWEA실습문제.TERRY;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

public class swea1249보급로 {
	static BufferedReader input;

	static int N;
	static int[][] matrix;
	static int[][] dist;

	static PriorityQueue<Edge> pq;

	static int[] dx = { 0, 0, -1, 1 }; // 상 하 좌 우
	static int[] dy = { -1, 1, 0, 0 };

	// matrix 를 돌면서 dist를 갱신해주기!

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(input.readLine());
			matrix = new int[N][N];
			dist = new int[N][N];

			for (int[] arr : dist) {
				Arrays.fill(arr, Integer.MAX_VALUE);
			}

			for (int i = 0; i < N; i++) {
				String line = input.readLine();
				for (int j = 0; j < N; j++) {
					matrix[i][j] = line.charAt(j) - '0';
				}

			}

			dijkstra(0, 0);
			System.out.println("#" + t + " " + dist[N - 1][N - 1]);
		}

	} // main

	static class Edge implements Comparable<Edge> {

		int x;
		int y;
		int cost;

		public Edge(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

	}

	static void dijkstra(int x, int y) {
		pq = new PriorityQueue<>();

		// 시작 지점 초기화
		dist[y][x] = 0;
		pq.add(new Edge(0, 0, 0));

		while (!pq.isEmpty()) {
			Edge current = pq.poll();

			for (int d = 0; d < 4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}

				int newCost = current.cost + matrix[ny][nx];

				// 저장되어 있는 값이 새 값보다 클 경우 갱신
				if (dist[ny][nx] > newCost) {
					dist[ny][nx] = newCost;
					pq.add(new Edge(nx, ny, newCost));
				}
			}

		}
	}
}
