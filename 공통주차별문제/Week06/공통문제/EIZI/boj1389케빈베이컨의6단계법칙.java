package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj1389케빈베이컨의6단계법칙 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 2<=N<=100 (유저의 수)
		int M = Integer.parseInt(st.nextToken()); // 1<=M<=5000 (친구 관계의 수)
		int[][] relationships = new int[N+1][N+1];
		for (int i = 1; i<N+1; i++) {
			Arrays.fill(relationships[i], 6000);
			relationships[i][i] = 0;
		}
		for (int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 2<=N<=100 (유저의 수)
			int B = Integer.parseInt(st.nextToken()); // 1<=M<=5000 (친구 관계의 수)
			relationships[A][B] = 1;
			relationships[B][A] = 1;
			
		}//무향그래프
		for (int k = 1; k<N+1; k++) {
			for (int i = 1; i<N+1; i++) {
				for (int j = 1; j<N+1; j++) {
					relationships[i][j] = Math.min(relationships[i][j], relationships[i][k]+relationships[k][j]);
				}
			}
		}
		
		int maxDist = Integer.MAX_VALUE;
		int maxDistNum = Integer.MAX_VALUE;
		for (int i = 1; i<N+1; i++) {
			int dist = 0;
			for (int j = 1; j<N+1; j++) {
				dist += relationships[i][j];
			}
			if (maxDist > dist) {
				maxDist = dist;
				maxDistNum = i;
			}
		}
		System.out.println(maxDistNum);
		

	}

}
