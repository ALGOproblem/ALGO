package week1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 특정 정점 s와 t가 연결되는 시점에서 간선 추가를 멈출 것
 * 
 * 1. List<List<Node>> graph = new ArrayList<>()
 * 		그래프 전체를 저장할 리스트 (2차원 리스트 형태)
 * 		각 노드(정점)가 가리키는 연결된 노드 목록 Node 리스트를 저장
 * 		graph.get(i) = 정점 i와 연결된 노드들의 리스트를 가져올 수 있음
 * 
 * 2. int[] distance = new int[N+1] Arrays.fill(distance, Integer.MAX_VALUE);
 * 		거리배열로, 출발지점의 거리는 0으로, 나머지는 전부 INF 무한대로 초기화
 * 
 * 3. Priorityqueue 우선순위 큐
 * 		다익스트라 알고리즘의 경우 거리(가중치)가 가장 작은 값부터 먼저 나오도록 구현해야 함
 * 		Node(int distance, int vertex) implements Comparable<Node> 구현
 * 		
 * 		pq.offer(new Node(시작 노드, 0))
 * 		while(!pq.isEmpty())
 * 			Node C=current = pq.poll();
 * 			
 * 			for (Node neighbor : graph.get(current.vertex)) // 현재 정점에서 모든 인접 정점 가져와서 확인
 * 				int newWeight = current.weight + neighbor.weight 
 * 				// 새로운 거리(현재 정점까지 온 거리 + 인접 노드로 가는 거리)
 * 				// 지금까지 이동한 총 거리 + 현재 정점에서 인접한 정점으로 가는 거리
 * 				if (newWeight < distance[neighbor.vertex]) // 이전보다 짧다면 업데이트
 * 				pq.offer(new Node(neighbor.vertex, newWeight)) // 노드까지 가는 새로운 최단 거리 pq 추가
 *				다익스트라는 현재까지의 최단 경로를 갱신하면서 진행하는 알고리즘으로, 더 짧은 거리로 도달할 수 있는 노드는 다시 탐색
 *				이 때 새로운 거리가(newWeight)가 더 작아진 노드를 pq.offer()로 구차하면 가장 작은 거리부터 방문 가능
 * 		
 * 
 * 
 */
public class 백준14284간선이어가기2 {
	static class Node implements Comparable<Node>{
		int end;
		int dist;

		public Node(int end, int dist) {
			super();
			this.end = end;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			// 우선 순위큐에서 가중치가 작은 노드가 먼저 나오도록 설정
			return this.dist- o.dist;
		}
		
	}
	static int dijkstra(int N, List<List<Node>> graph, int start, int target) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		pq.offer(new Node(start, 0));
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			int end = current.end;
			int dist = current.dist;
			
			if (end == target) return dist; // 목표 지점에 도달하면 종료
			if (dist > distance[end]) continue; // 현재보다 더 큰 가중치로 들어온 경우 무시
			for (Node neighbor : graph.get(end)) {
				int newWeight = dist + neighbor.dist;
				// 더 짧은 경로가 발견되었을 때만 업데이트
				if (newWeight < distance[neighbor.end]) {
					distance[neighbor.end] = newWeight;
					pq.offer(new Node(neighbor.end, newWeight));
				}
			}
		}
		return -1; // 도달 불가능한 경우
	}
	
	
	
	public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/백준14284간선이어가기2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점의 개수 <=5,000
        int M = Integer.parseInt(st.nextToken()); // 간선리스트 <=100,000
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i<=N; i ++) graph.add(new ArrayList<>());
        for (int i = 0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken()); // 가중치 <=100
        	graph.get(a).add(new Node(b, c));
        	graph.get(b).add(new Node(a, c));
        	
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()); // 정점
        int target = Integer.parseInt(st.nextToken()); // 정점
        int result = dijkstra(N, graph, start, target);
        System.out.println(result);
	}


}
