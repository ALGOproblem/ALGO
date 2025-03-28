

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class swea7465창용마을무리의개수 {
	static int[] arr;
	static int[] rank;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 마을에 사는 사람 (<=100)
			int M = Integer.parseInt(st.nextToken()); // 서로 알고지내는 사람의 관계 수
			arr = new int[N + 1]; // n은 1부터 시작
			rank = new int[N + 1];
			for (int i = 1; i < N + 1; i++) {
				arr[i] = i;
			}//자기자신을 가리키도록 초기화
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				union(A, B);
			}
			for (int i = 1; i<N+1; i++) {
				findSet(i);
			}
			int answer = 0;
			HashSet<Integer> set = new HashSet<>();
			for (int i = 1; i<N+1; i++) {
				if (!set.contains(arr[i])) {
					set.add(arr[i]);
					answer++;
				}
			}
			System.out.println("#"+test+" " +answer);
		}
	}
	static int findSet(int a) {
		if (a != arr[a]) arr[a] = findSet(arr[a]);
		return arr[a];
	}
	static void union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if (a==b) return;
		if (rank[a]>rank[b]) arr[a] = b;
		else if (rank[b]<rank[a]) arr[b] = a;
		else arr[b] = a;
//		System.out.println(Arrays.toString(arr));
 	}
}
