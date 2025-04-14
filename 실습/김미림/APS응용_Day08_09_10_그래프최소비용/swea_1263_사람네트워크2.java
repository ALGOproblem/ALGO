import java.util.*;

public class swea_1263_사람네트워크2 {
	static int N;
	static List<Integer>[] graph;
	static int[] distance;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			
			graph = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>(); 
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int isConnected = sc.nextInt();
					if (isConnected == 0) continue;
					graph[i].add(j);
				}
			}// 입력
			
			distance = new int[N];
			
			for (int i = 0; i < N; i++) {
				bfs(i);
			}
			
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				min = Math.min(min, distance[i]);
			}
			
			System.out.printf("#%d %d\n", tc, min);
		}// tc
		sc.close();
	}// main
	
	static void bfs(int start) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {start, 0});
		
		boolean[] visited = new boolean[N];
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int[] node = queue.poll();
			int curIdx = node[0];
			int curDis = node[1];
			
			for (int next : graph[curIdx]) {
				// 현재 정점과 연결되지 않은 정점, 이미 최단 거리를 구한(방문한) 정점은 건너뛰기
				if (visited[next]) continue;
				
				// 방문 처리, 최단 거리 갱신
				visited[next] = true;
				distance[start] += curDis + 1;
				queue.offer(new int[] {next, curDis + 1});
			}
			
		}//while
		
	}// bfs

}
