package samsungA형대비.EIZI;

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
 * 다익스트라 : 가중치 개념이 있는 한 노드 기준 최단 경로
 * 1. Node class 작성 (priorityQueue를 활용하기 위함)
 * 2. 모든 거리를 무한대로 초기화 & start[0] = 0 으로 초기화
 * 3. pq에 0을 넣고 pq.isEmpty하기 전까지 반복
 * 		A. pq.poll()
 * 		B. 현재 저장된 최단 거리 보다 크다면 continue(무시)
 * 		C. 작거나 같다면 현재 노드와 연결되 모든 노드 탐색
 * 			if(더 짧은 경로(현재거리+이동비용) 발견하면 업데이트) 
 * 				distance.put(nextNode, newDist)
 * 				pq.offer(new Node(nextNode, newDIst));
 * 
 * <우선순위 큐 pq의 핵심 동작>
 * 1. 모든 경로를 큐에 넣는다
 * 2. 현재까지 최소 비용으로 도달할 수 있는 노드를 먼저 꺼낸다
 * 3. 해당 노드에서 갈 수 있는 길을 확인하여 업데이트 한다
 * 4. 업데이트 된 정보는 다시 큐에 넣고 계속해서 탐색한다
 * 
 * 
 * 
 */

/* 다익스트라가 아직 잘 이해가 안되는 이유
 * 1. 우선순위 큐에 일반 고속도로와 지름길을 같이 넣는 방식이 이해가 잘 안 되었음
 * 2. 내가 생각한 것 -> 시작하는 곳에서 더 가까운 곳이 있다면 이동하는 건가? 아님
 * 				 -> 도착하는 곳에서 더 빨리 도착할 수 있는 루트가 있다면 도착지에서 업데이트 하는 것? 아님
 * 3. 정답 : 현재 노드에서 연결된 모든 노드를 탐색하는 방식
 * 
 * | pq.poll() | distance.get(node) | dist | 업데이트                 |      pq의 내용               |
 * | (0, 0)    | 0                  | 0    | 1km 이동 추가 및 지름길 추가 | (1,1) (50,10), (2, INF)    |
 * | (1, 1)    | INF                | 1    | 2km 추가                | (2,2) (50,10)              |
 * | (2, 2)    | INF                | 2    | 3km 추가                | (3,3) (50,10)              |
 * | (3, 3)    | INF                | 3    | 4km 추가                | (4,4) (50,10)              |
 * | ......    | ....               | .... | ........               | ...............            |
 * | (48, 48)  | INF                | 48   | 48km 추가               | (49, 49) (50, 10)          |
 * | (49, 49)  | INF                | 49   | 49km 추가               | (50, 50) (50, 10)          |
 * | (50, 10)  | INF                | 10   | 50km 추가 및 지름길 추가    | (50, 50) (51, 11) (100, 20)|
 */


public class 백준1446지름길 {
	static int N;
	static int D;
	static final int INF = Integer.MAX_VALUE; // 무한대의 값
	static List<List<Road>> graph;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/백준1446지름길.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지름길 개수
        D = Integer.parseInt(st.nextToken()); // 고속도로 길이
        graph = new ArrayList<>(); // 인접 리스트
        for (int i = 0; i<=D; i++) graph.add(new ArrayList<>()); // 고속도로 길이만큼 리스트 생성
        for (int i = 0; i<D; i++) graph.get(i).add(new Road(i+1, 1)); // 기본 도로 1km 이동 생성
        for (int i = 0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken());
        	int end = Integer.parseInt(st.nextToken());
        	int distance = Integer.parseInt(st.nextToken());
        	if (end > D) continue; // 지름길이 목적지 넘어가면 무시
        	graph.get(start).add(new Road(end, distance));
        }
        System.out.println(dijkstra());
    }
    static class Road implements Comparable<Road>{
    	int node; // 현재 노드
    	int distance; // 가중치
    	public Road() {
		}
		public Road(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}
		@Override
		public int compareTo(Road o) {
			return this.distance - o.distance;
		}
    	
    }
    
    static int dijkstra() {
    	PriorityQueue<Road> pq = new PriorityQueue<>();
    	int[] distance = new int[D+1];
    	Arrays.fill(distance, INF);
    	distance[0] = 0; // 시작점 초기화
    	pq.offer(new Road(0, 0));
    	while(!pq.isEmpty()) {
    		Road current = pq.poll();
    		int pos = current.node;
    		int dist = current.distance;
    		if (distance[pos]<dist) continue;
    		for (Road neighbor : graph.get(pos)) { // 이웃한 모든 노드 꺼내보기
    			int neighborPos = neighbor.node;
    			int neighborCost = neighbor.distance;
    			int newdist = dist + neighborCost;
    			if (newdist < distance[neighborPos]) {
    				distance[neighborPos] = newdist;
    				pq.offer(new Road(neighborPos, newdist));
    			}
    			
    		}
    	}
    	return distance[D];
    }
}


















