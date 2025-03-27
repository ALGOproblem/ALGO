import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA7465_창용마을무리의개수 {
	static int N;  // 사람 수
	static int M;  // 관계 수
	static int[] parent;  // 대표 노드를 저장하는 배열
	
	// find 함수: 대표 노드(루트)를 찾는 함수 + 경로 압축
	static int find(int a) {
		if(parent[a]==a) return a;  // 자기 자신이 대표면 그대로 반환
		else {
            return parent[a]=find(parent[a]);  // 대표를 찾고 경로 압축
        }
	}
	
	// union 함수: 두 사람을 같은 집합으로 묶음
	static void union(int a, int b) {
		int rootA=find(a);
		int rootB=find(b);
		if(rootA!=rootB) parent[rootB]=rootA;  // 루트가 다르면 병합
	}
	
	public static void main(String args[]) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수 입력
		   
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());  // 사람 수
			M = Integer.parseInt(st.nextToken());  // 관계 수
			
			parent = new int[N+1];  // 1번부터 N번까지 사용하므로 N+1 크기
			for(int i = 1; i <= N; i++) {
				parent[i] = i;  // 초기에는 자기 자신이 대표
			}
			
			// 관계 정보를 바탕으로 union 실행
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			
			// 모든 사람의 대표를 찾아서 set에 저장 (중복 제거됨 → 무리 수)
			Set<Integer> set = new HashSet<>();
			for(int i = 1; i <= N; i++) {
				set.add(find(i));
			}

			// set의 크기가 곧 무리의 개수
			System.out.println("#" + tc + " " + set.size());
		}
	}
}
