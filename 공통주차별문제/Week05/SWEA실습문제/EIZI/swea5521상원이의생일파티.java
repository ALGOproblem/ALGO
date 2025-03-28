
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 상원이의 초대장 (N명, 상원이 1번)
 * 1. 친한 친구 O
 * 2. 친한 친구의 - 친한 친구 O
 * 3. 상원이는 친구가 없을 수도 있으며 상원이 자체는 초대장을 받지 않음
 */

public class swea5521상원이의생일파티 {
	static int[][] adjArr;
	static ArrayList<Integer> friends; 
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken())+1; // 반 학생 수 (<=500)
			int M = Integer.parseInt(st.nextToken()); // 친구 관계 (<=10^4)
			adjArr = new int[N][N];
			friends = new ArrayList<>();
			friends.add(1);
			for (int i = 0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				adjArr[A][B] = adjArr[B][A] = 1;
				if (A==1) friends.add(B);
			}//adjArr입력
			System.out.println("#"+test+" "+inviteFriend());
		}
	}
	static int inviteFriend() {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];
		int answer = -1;
		queue.add(1); //상원이
		while(!queue.isEmpty()) {
			int prev = queue.poll();
			answer++;
			for (int i = 0; i<N; i++) {
				if (adjArr[prev][i]==1 && !visited[i] && friends.contains(prev) && i!=1) {
					queue.add(i);
					visited[i] = true;
				}
			}
		}
		return answer;
	}
	

}
