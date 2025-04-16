package 공통주차별문제.Week07.EIZI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14889스타트와링크 {
	static int N;
	static int[] team1;
	static int[] team2;
	static int[][] synergy;
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ans = Integer.MAX_VALUE;
		team1 = new int[N/2];
		team2 = new int[N/2];
		synergy = new int[N+1][N+1];
		for (int i = 1; i<N+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j<N+1; j++) {
				synergy[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		makeTeam(1, 0);
		System.out.println(ans);
	}
	static void makeTeam(int idx, int tIdx) {
		if (tIdx==N/2) {
//			System.out.println("#1"+Arrays.toString(team1));
			makeAnotherTeam();
//			System.out.println("#2"+Arrays.toString(team2));
			int tmp = check();
			ans = Math.min(tmp, ans);
			return;
		}
		if (idx>N) return;
		team1[tIdx] = idx;
		makeTeam(idx+1, tIdx+1);
		makeTeam(idx+1, tIdx); // back
	}
	static void makeAnotherTeam() {
		boolean[] arr = new boolean[N+1];
		for (int i : team1) arr[i] = true;
		int idx = 0;
		for (int i = 1; i<N+1; i++) {
			if (!arr[i]) team2[idx++] = i;
		}	
	}
	static int check() {
		int synergyTeam1 = 0;
		int synergyTeam2 = 0;
		boolean[] isTeam1 = new boolean[N+1];
		boolean[] isTeam2 = new boolean[N+1];
		for (int i = 0; i<N/2; i++) {
			isTeam1[team1[i]] = true;
			isTeam2[team2[i]] = true;
		}
		
		for (int i = 1; i<N+1; i++) {
			for (int j = 1; j<N+1; j++) {
				if (i==j) continue;
				if (isTeam1[i] && isTeam1[j]) synergyTeam1 += synergy[i][j];
				if (isTeam2[i] && isTeam2[j]) synergyTeam2 += synergy[i][j];
			}
		}
		return Math.abs(synergyTeam1 - synergyTeam2);
	}

}
