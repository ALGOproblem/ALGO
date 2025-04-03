import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1251_하나로 {
	static List<node> list;  // 간선 리스트 (모든 정점 간 거리)
	static int[][] nodenum;  // 각 섬의 x, y 좌표 저장
	
	// 간선을 표현하는 클래스
	static class node implements Comparable<node> {
		int from;       // 시작 정점
		int to;         // 도착 정점
		double dist;    // 거리 (제곱 거리)

		node(int from, int to, double dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
		
		// 거리 기준으로 오름차순 정렬
		public int compareTo(node a) {
			return Double.compare(this.dist, a.dist);
		}
	}

	static int[] parent;  // Union-Find를 위한 부모 배열
	
	// find 함수: 대표 노드 찾기 (경로 압축 포함)
	static int find(int N) {
		if(parent[N] == N) return N;
		else return parent[N] = find(parent[N]);
	}
	
	// union 함수: 두 정점을 하나의 집합으로 합침
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) return false;  // 이미 같은 집합이면 false
		else parent[rootB] = rootA;       // 합치기
		return true;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수 입력
	   
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());  // 섬의 개수
			list = new ArrayList<>();
			
			StringTokenizer stx = new StringTokenizer(br.readLine());  // x 좌표들
			StringTokenizer sty = new StringTokenizer(br.readLine());  // y 좌표들
			
			nodenum = new int[N][2];  // 섬의 좌표 저장
			for(int i = 0; i < N; i++) {
				nodenum[i][0] = Integer.parseInt(stx.nextToken());  // x 좌표
				nodenum[i][1] = Integer.parseInt(sty.nextToken());  // y 좌표
			}
			
			Double E = Double.parseDouble(br.readLine());  // 환경 부담금 비율
			
			// 모든 섬 쌍에 대해 거리 계산 후 간선 리스트에 추가
			for(int i = 0; i < N; i++) {
				for(int k = i + 1; k < N; k++) {
					double dx = nodenum[i][0] - nodenum[k][0];
					double dy = nodenum[i][1] - nodenum[k][1];
					double dist = dx * dx + dy * dy;  // 제곱 거리
					list.add(new node(i, k, dist));
				}
			}
		
			Collections.sort(list);  // 거리 기준으로 정렬 (Kruskal 준비)
			
			parent = new int[N];  // Union-Find 초기화
			for(int i = 0; i < N; i++) {
				parent[i] = i;
			}
			
			double ansdist = 0;  // 총 거리 누적 변수
			int count = 0;       // 선택한 간선 수
			
			// MST 구성 (Kruskal 알고리즘)
			for(node a : list) {
				if(union(a.from, a.to)) {
					ansdist += a.dist;
					count++;
				}
				if(count == N - 1) break;  // MST 완성되면 중단
			}
			
			// 환경 부담금 곱하고 반올림
			double ans = Math.round(ansdist * E);
			
			System.out.println("#" + tc + " " + (long)ans);  // 정답 출력 (형변환 주의)
		}
	}
}
