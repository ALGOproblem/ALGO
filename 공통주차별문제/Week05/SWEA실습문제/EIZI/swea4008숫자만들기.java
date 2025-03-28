package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class swea4008숫자만들기 {
	static int N; //숫자 개수
	static int[] nums;
	static int[] operator;
	static int[] arrOperator;
	static int sumMax;
	static int sumMin;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/swea4008숫자만들기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());
			nums = new int[N];
			operator = new int[4];
			arrOperator = new int[N-1];
			sumMax = Integer.MIN_VALUE;
			sumMin = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i<4; i++) operator[i] = Integer.parseInt(st.nextToken()); // 연산자 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i<N; i++) nums[i] = Integer.parseInt(st.nextToken()); // 숫자 입력
			backTracking(0);
			int answer= sumMax - sumMin;
			System.out.println("#"+test+" "+answer);
			
		}

	}
	static void backTracking(int depth) {
		if (depth == N-1) {
			calculate(arrOperator);
			return;
		}
		for (int i = 0; i<4; i++) {//연산자를 뽑기
			if (operator[i]>0) {
				operator[i]--;
				arrOperator[depth] = i; // 0(+) 1(-) 2(*) 3(/)
				backTracking(depth+1);//호출
				operator[i]++;//되돌리기
			}
		}
	}
	static void calculate(int[] arrOperator) {
		int sum = nums[0];
		for (int i = 0; i<N-1; i++) { // 연산자횟수만큼 계산
			switch(arrOperator[i]) {
			case 0:
				sum += nums[i+1];
				break;
			case 1:
				sum -= nums[i+1];
				break;
			case 2:
				sum *= nums[i+1];
				break;
			case 3:
				sum = Math.round(sum/nums[i+1]);
				break;
			}
		}
		if (sum > sumMax) sumMax = sum;
		if (sum < sumMin) sumMin = sum;

	}

}
