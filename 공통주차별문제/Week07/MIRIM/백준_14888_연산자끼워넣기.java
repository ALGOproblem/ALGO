package 공통주차별문제.Week07.MIRIM;

import java.util.Scanner;

public class 백준_14888_연산자끼워넣기 {
	static int N;
	static int[] nums; //N개
	static int[] operation; // +, -, x, /
	static int[] used;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		nums = new int[N];
		
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		
		operation = new int[4];
		for (int i = 0; i < 4; i++) {
			operation[i] = sc.nextInt();
		}
		
		used = new int[4];
		
		dfs(nums[0], 1);
		
		System.out.println(max);
		System.out.println(min);
		
		sc.close();
	}
	
	static void dfs(int cal, int level) {
		
		if (level == N) {
			max = Math.max(max, cal);
			min = Math.min(min, cal);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (used[i] == operation[i]) continue;
			
			used[i]++;
			int newCal = cal;
			
			switch(i) {
			case 0: 
				newCal += nums[level];
				break;
			case 1:
				newCal -= nums[level];
				break;
			case 2:
				newCal *= nums[level];
				break;
			case 3:
				if (nums[level] == 0) continue;
				
				if (newCal < 0) {
					newCal = Math.abs(newCal);
					newCal = (-1) * (newCal / nums[level]);
				} else {
					newCal /= nums[level];
				}
				break;
			}

			dfs(newCal, level + 1);
			used[i]--;
		}
		
	}//dfs
}

