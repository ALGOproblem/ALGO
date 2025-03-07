package 실습Day01_부분집합;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제2817부분수열의합 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/문제2817부분수열의합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case<=T; test_case++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int N = Integer.parseInt(st.nextToken()); // 1<=N<=20 선택할 수 있는 자연수
			int K = Integer.parseInt(st.nextToken()); // 1<=L<=10000 제한하는 칼로리수
			int[] arr = new int[N];
			s = br.readLine();
			st = new StringTokenizer(s);
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken()); // 1<=K<=1000 만들어야 하는 수
				arr[i] = num;
			} // 입력 받기
			int count = 0;
			for (int i = 0; i< (1<<N); i++) {
				int sum = 0; 
				for (int j = 0; j<N; j++) {
					if ((i & (1<<j))!=0) {
						sum += arr[j];
					}
				}
				if (sum == K) count++;
			}
			System.out.println("#"+test_case+" "+count);
		}

	}

}
