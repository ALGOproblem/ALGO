
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * 1. for (1~N)까지 돌기
 * 			A. queue에 저장
 * 			B. while(!queue.isEmpty) 방문 하지 않았을 때 이웃 노드 저장 (queue, connected[A] = connected[B] = count)
 * 			C. while문이 끝나면 count++
 * 2. if (connected[A]==connected[B]) answer ++;
 * 
 */


public class swea15849이어진쌍 {
	static int N; // 정점
	static int M; // 간선
	static int Q; // 질의
	static List<Integer>[] adjArr;
	
	static int[] connected;
	static int count;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/swea15849이어진쌍.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 3 이상 100,000 이하
			M = Integer.parseInt(st.nextToken()); // M은 1,000 이하
			Q = Integer.parseInt(st.nextToken());
			count = 1;
			adjArr = new ArrayList[N+1];
			connected = new int[N+1];
			for (int i = 1; i<=N; i++) {
				adjArr[i] = new ArrayList<>();
			}
			for (int i = 0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				adjArr[A].add(B);
				adjArr[B].add(A);
			}//간선입력
			for (int i = 1; i<=N; i++) {
				findConn(i);
			}
			int answer = 0;
			for (int i = 0; i<Q; i++) {
				st = new StringTokenizer(br.readLine());
				int C = Integer.parseInt(st.nextToken());
				int D = Integer.parseInt(st.nextToken());
//				if (test==3) System.out.println(C+","+D+" "+connected[C]+":"+connected[D]);
				if (connected[C]!=connected[D]) {
					answer++;
				}
			}
			System.out.println("#"+test+" "+answer);
		}
		

	}
	static void findConn(int start) {
		if (connected[start]!=0) return; //  || adjArr[start].isEmpty() : 자기 자신도 연결로 들어올 수 있기 때문ㅇ
		boolean[] visited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;
		connected[start] = count;
		while(!queue.isEmpty()) {
			int prev = queue.poll();
			for (int i = 0; i<adjArr[prev].size(); i++) {
				if (!visited[adjArr[prev].get(i)]) {
					queue.add(adjArr[prev].get(i));
					visited[adjArr[prev].get(i)] = true;
					connected[adjArr[prev].get(i)] = count;
				}
			}
		}
		count++;
	}
}
