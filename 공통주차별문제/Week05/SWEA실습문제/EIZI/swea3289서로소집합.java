
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea3289서로소집합 {
	static int[] arr;
	static int[] rank;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // N개의 집합 (<=1,000,000)
			int M = Integer.parseInt(st.nextToken()); // M개의 입력을 주어지는 연산의 개수(<=100,000)
			arr = new int[N + 1]; // n은 1부터 시작
			rank = new int[N + 1];
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < N + 1; i++) {
				arr[i] = i;
			}//자기자신을 가리키도록 초기화
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int type = Integer.parseInt(st.nextToken()); // 0이면(합연산) 1이면(확인)
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				if (type==0) union(A, B);
				else {
					if(isUnion(A,B)) sb.append("1");
					else sb.append("0");
				}
			}
			System.out.println("#"+test+" "+sb);
		}

	}
	static int findSet(int a) {
		if (a != arr[a]) arr[a] = findSet(arr[a]); // 경로압축에 return문 쓰면 안ㄷ..
		return arr[a];
	}
	static void union(int a, int b) {
        a = findSet(a); // findSet을 통해 루트를 연결하지 않으면 중간 노드만 연결해서 꼬임
        b = findSet(b);
        if (a==b) return;
        if (rank[a]<rank[b]) arr[a] = b;
        else if (rank[a]>rank[b]) arr[b] = a;
        else {
        	arr[b] = a;
        	rank[a]++;
        }
	}
	static boolean isUnion(int a, int b) {
		return findSet(a) == findSet(b); //간결하게
	}

}
