package 공통주차별문제.Week06.공통문제.MIRIM;

import java.util.*;

public class 백준_1916_최소비용구하기 {
	// 시작점, 도착점 명확
	// 간선마다 비용 차이 -> 가중치 다름
	// 최소 비용 -> 다익스트라
	// 유향 그래프
	
	static int N, M; // 정점의 개수, 간선의 개수
	static List<int[]>[] graph;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		graph = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}// 객체 생성
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int w = sc.nextInt();
	
			graph[from].add(new int[] {to, w});
		}// 간선 입력
		
		int start = sc.nextInt();
		int end = sc.nextInt();
		
		System.out.println(dijkstra(start, end));
		
	}//main
	
	static int dijkstra(int start, int end) {
		// 현재 위치, 현재까지의 거리 입력
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.offer(new int[] {start, 0});
		
		// 거리 설정
		int[] distance = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		while (!pq.isEmpty()) {
			int[] curNode = pq.poll();
			int cur = curNode[0];
			int curDis = curNode[1];
			
			if (cur == end) break;
			if (curDis > distance[cur]) continue;
			
			for (int[] nextNode : graph[cur]) {
				int next = nextNode[0];
				int weight = nextNode[1];
				int nextDis = curDis + weight;
				
				if (nextDis < distance[next]) {
					distance[next] = nextDis;
					pq.add(new int[] {next, nextDis});
				}
			}// 최단 거리 노드 방문
		}//while
		
		return distance[end];
	}//dijkstra
	
}
