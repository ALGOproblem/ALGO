package 실습.EIZI.실습Day03_분할정복;

import java.util.Scanner;

/*
 * a층의 b호에 살려면 자신의 아래(a-1)층의 1호부터 b호까지 사람들의 합만큼 사람을 데려와서 살아야 한다
 * 주어지는 양의 정수 k와 n에 대해 k층에 n호의 몇 명이 살고 있는지를 구하라
 * f(a,b) = f(a-1, 1) + f(a-2, 2) + f(a-3, 3) ... f(0,0)
 */

public class 백준2275부녀회장이될테야 {
	static int[][] memo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc<=T; tc++) {
			int k = sc.nextInt(); 
			int n = sc.nextInt();
			memo = new int[k+1][n+1];
			int answer = numberofPeople(k, n);
			System.out.println(answer);
			
		}
	}
	// a층 b호에 사는 사람들을 구하고 싶다
	static int numberofPeople(int a, int b) {
		// a-1층의 1호부터 b호까지 사는 사람들을 구해 다 더해야 함
		if (a==0) return b;
		if(memo[a][b]!=0) return memo[a][b];
		int sum = 0;
		for (int i = 1; i<=b; i++) {
			sum+=numberofPeople(a-1, i);
		}
		return memo[a][b] = sum;
	}

}
