import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class 백준16438_원숭이스포츠 {
	static int N; // 원숭이 수
	static int[] teamInfo; // idx = 원숭이 번호(0~N-1), 해당 원숭이가 가진 팀 정보(비트마스킹)
	static int[] teamResult; // day(0~6) = idx, 해당 날의 팀 정보

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		teamInfo = new int[N];
		Arrays.fill(teamInfo, (1 << N) - 1);

		teamResult = new int[7];

		dfs();

		for (int i = 0; i < 7; i++) {
			int team = teamResult[i];
			StringBuilder sb = new StringBuilder();

			for (int j = 0; j < N; j++) {
				boolean isATeam = ((team >> j) & 1) == 0;
				sb.append(isATeam ? 'A' : 'B');
			}
			System.out.println(sb.toString());
		} // 결과 출력

		sc.close();
	}// main

	// 주의: 111111, 0처럼 모든 자리수가 같으면 안된다 = 모두가 같은 팀
	static void dfs() {
		Stack<int[]> stack = new Stack<>();

		for (int i = (1 << N) - 2; i > 0; i--) {
			if (!isValidTeam(i)) continue;
			stack.push(new int[] { 0, i }); // day, team정보
		}

		while (!stack.isEmpty()) {
			int[] cur = stack.pop();
			int curDay = cur[0];
			int curTeam = cur[1];

			if (curDay == 7) {
				if (isValid())
					return;
				continue;
			}
			teamResult[curDay] = curTeam;
			
			int[] preTeamInfo = teamInfo.clone();
			updateTeamInfo(curTeam);

			// 다음 날 팀 저장
			for (int i = (1 << N) - 2; i > 0; i--) {
				if (!isValidTeam(i)) continue;
				stack.push(new int[] { curDay + 1, i });
			}
			
			teamInfo = preTeamInfo;
		} // stack while

	}// bfs

	static void updateTeamInfo(int team) {
		int[] newTeamInfo = new int[N];

		for (int i = 0; i < N; i++) {
			int resultByDay = 0;

			for (int j = 0; j < N; j++) {
	
				
				// i와 j번째 원숭이가 같은 팀이면 j번째에 1 기록
				if (((team >> i) & 1) == ((team >> j) & 1)) {
					resultByDay |= 1 << j;
				}
			}

			// i번째 원숭이와 여태까지 다른 팀
			newTeamInfo[i] = teamInfo[i] & resultByDay;
		}
		teamInfo = newTeamInfo;

	}// orAnd

	// isValid: 마지막 teamInfo 검사 시
	static boolean isValid() {
		for (int i = 0; i < N; i++) {
			if (teamInfo[i] != (1 << i)) return false;
		}
		return true;
	}

	// isValidTeam: 0000...0, 1111...1이면 유효하지 않음 = false
	static boolean isValidTeam(int team) {
		return team != 0 && team != (1 << N) - 1;
	}

}
