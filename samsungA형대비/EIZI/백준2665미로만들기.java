package samsungA형대비.EIZI;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 문제 조건 : n*n 바둑판 모양
 * 가장 적게 검은 타일을 바꿔서 흰 색길을 만드자. 
 */

/* -> 틀린 점 : priorityqueue에 검은색 타일이 최소한으로 가는 값을 계산해야 하는데 각 point가 목적지랑 가까운 경우를 가중치로 씀
 * -> BFS랑 다익스트라를 혼용해서 쓸 수 있지만 둘이 잘 구분해서 써야함
 * 각 point별 가중치 = 목적x - 현재x + 목적 y-현재 y
 * 0. BFS 탐색 -> 목적지 도착시 return answer;
 * 1. 더 이상 갈 수 없는 지점에 도달한다면
 * 		pq.offer(point)
 * 2. pq.poll() 가중치 제일 작은 point 꺼냄
 * 		해당 point를 흰색으로 바꾸고 answer++;
 */



public class 백준2665미로만들기 {
	static int N;
	static int[][] arr;
	static int[][] distance;
	static int[] dirx = {-1, 1, 0, 0}; // 상하좌우
	static int[] diry = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/백준2665미로만들기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        distance = new int[N][N];
        for (int i = 0; i<N; i++) {
        	String s = br.readLine();
        	for (int j = 0; j<N; j++) {
        		arr[i][j] = s.charAt(j) - '0';
        		distance[i][j] = Integer.MAX_VALUE;
        	}
        }// 입력
//        for (int i = 0; i<N; i++) {
//        	System.out.println();
//        	for (int j = 0; j<N; j++) {
//        		System.out.print(arr[i][j]+" ");
//        	}
//        }// 입력
        
//        System.out.println(dijkstra());
        System.out.println(bfsdijkstra());
	}
	
	static int dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0)); // 시작점 (0,0)에서 시작
        while(!pq.isEmpty()) {
        	Node current = pq.poll();
        	int x = current.x;
        	int y = current.y;
        	int cost = current.cost;
        	if (x == N-1 && y==N-1) return cost;
        	for (int i = 0; i<4; i++) {
        		int nx = x + dirx[i];
        		int ny = y + diry[i];
        		if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    int newCost = cost + (arr[nx][ny] == 0 ? 1 : 0); // 검은 방이면 비용 +1
                    if (newCost < distance[nx][ny]) {
                        distance[nx][ny] = newCost;
                        pq.offer(new Node(nx, ny, newCost)); // 우선순위 큐에 추가
                    }
                }
        	}
        	
        }
        return -1;
	}
	
	static int bfsdijkstra() {
		Deque<Node> deque = new LinkedList<>();
		deque.addFirst(new Node(0,0,0));
		distance[0][0] = 0; // 시작 지점은 변경 횟수 0
		while(!deque.isEmpty()) {
			Node current = deque.pollFirst();
			int x = current.x;
            int y = current.y;
            int cost = current.cost;
            // 목적지 도달하면 최소 변경 횟수 반환
            if (x == N - 1 && y == N - 1) return cost;
            
         // 네 방향 탐색 (상, 하, 좌, 우)
            for (int i = 0; i < 4; i++) {
                int nx = x + dirx[i];
                int ny = y + diry[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    int newCost = cost + (arr[nx][ny] == 0 ? 1 : 0); // 검은 방이면 1 추가

                    if (newCost < distance[nx][ny]) {
                        distance[nx][ny] = newCost;
                        if (arr[nx][ny] == 1) {
                            deque.addFirst(new Node(nx, ny, newCost)); // 흰 방이면 앞에 추가 (우선 방문)
                        } else {
                            deque.addLast(new Node(nx, ny, newCost)); // 검은 방이면 뒤에 추가
                        }
                    }
                }
            }
        }
        return -1; // 도달 불가능한 경우 (이 문제에서는 발생하지 않음)
		
	}
	
	
	
	static class Node implements Comparable<Node>{
		int x; 
		int y;
		int cost;
		public Node() {
			// TODO Auto-generated constructor stub
		}
		public Node(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	

}
