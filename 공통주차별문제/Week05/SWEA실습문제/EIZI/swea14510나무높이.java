
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N개의 나무. 하루에 하나의 나무만 물을 줌
 * 1. 홀수날에는 물을 준 나무 키가 1자람
 * 2. 짝수날에는 물을 준 나무 키가 2자람
 */

/**
 * A형 : 구현 & 완전탐색 (but 이 문제는 수학적인 것)
 * 1. 1일짜리는 쪼갤 수 없지만
 * 2. 2일짜리는 쪼개서 1일 + 1일로 나눌 수 있다
 * 강사님 코드
 */

public class swea14510나무높이 {
	static int N;
	static int[] trees;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/swea14510나무높이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());
			trees = new int[N];
			int one = 0;
			int two = 0;
			int max = -1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i<N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, trees[i]);
			}
			for (int i = 0; i<N; i++) {
				int diff = max-trees[i];
				two += diff/2;
				one += diff%2;
			}
			while(two>=one+2) {
				two -=1;
				one +=2;
			}
			int answer = 0;
			if (one > two) answer += one*2-1;
			else answer = two*2;
			System.out.println("#"+test+" "+answer);
		}
	}//main
}
