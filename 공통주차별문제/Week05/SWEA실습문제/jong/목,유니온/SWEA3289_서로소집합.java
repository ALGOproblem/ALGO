import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3289_서로소집합 {
	static int N;  // 원소의 개수
    static int M;  // 연산의 개수
    static int[] parent;  // 각 원소의 부모를 저장하는 배열 (대표 원소)

    // find 함수: 재귀를 통해 대표 원소를 찾고 경로 압축(path compression) 수행
    static int find(int a) {
    	if(parent[a]==a) return a;  // 자기 자신이 대표면 그대로 반환
    	else {
            return parent[a]=find(parent[a]);  // 대표를 찾으며 경로 압축
        }
    }
    
    // union 함수: 두 집합을 하나로 합침
    static void union(int a, int b) {
    	int rootA=find(a);  // a의 대표 원소 찾기
    	int rootB=find(b);  // b의 대표 원소 찾기
    	if(rootA!=rootB)  
            parent[rootB]=rootA;  // 서로 다르면 하나로 합침 (rootB를 rootA에 연결)
    }
    
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수 입력
		   
		for(int tc = 1; tc <= T; tc++) {
			StringBuilder sb = new StringBuilder();  // 출력 결과를 저장할 StringBuilder
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());  // 원소 개수
			M = Integer.parseInt(st.nextToken());  // 연산 개수
			
			parent = new int[N + 1];  // 1번부터 사용하기 위해 N+1 크기로 배열 생성
			for(int i = 1; i <= N; i++) {
				parent[i] = i;  // 초기에는 자기 자신이 대표 원소
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int act = Integer.parseInt(st.nextToken());  // 연산 종류 (0: union, 1: check)
				int a = Integer.parseInt(st.nextToken());  // 원소 a
				int b = Integer.parseInt(st.nextToken());  // 원소 b
				
				if(act == 0) union(a, b);  // 합집합 연산
				else sb.append(check(a, b));  // 같은 집합인지 확인 후 결과 저장
			}
			
			System.out.println("#" + tc + " " + sb);  // 테스트 케이스 결과 출력
		}
	}

    // check 함수: 두 원소가 같은 집합에 속해 있는지 확인 (1: 같음, 0: 다름)
	public static int check(int a, int b) {
		int rootA = find(a);
    	int rootB = find(b);
    	if(rootA == rootB) return 1;
    	else return 0;
	}
}
