package 공통주차별문제.Week07.공통문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 백준_1931_회의실배정 {
	static BufferedReader input;
	static StringTokenizer st;

	// 겹치지 않게 최대한 사용
	// 시작과 끝이 같을 수 있음
	// 끝나는 동시에 시작 가능

	// - 주어진 시간 안에 최대한 많은 회의를 해야 한다.
	// -> 회의 시간이 짧으면 좋다
	// -> 종료시간을 기준으로 오름차순 정렬
	// -> 회의가 끝나고 다음 시작할 회의를 찾자
	// - 추가로 종료시간 오름차순하고 시작시간도 오름차순!!

	// 1. 종료시간 기준으로 오름차순 정렬 후 선택
	// 2. 이후에 오는 회의 시작시간이 이전 종료시간보다 같거나 크면 선택
	// 3. 카운트
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int N = Integer.parseInt(input.readLine());

		// 회의 사용 시간 = [시작 시간 , 종료 시간]
		int[][] meetingList = new int[N][2];

		// 회의실 리스트 셋팅
		// 1 <= N <= 100_000
		// 0 <= start, end <= 2^31 - 1
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			meetingList[i][0] = start;
			meetingList[i][1] = end;
		}

		// 1. 종료 시간 기준 오름차순 정렬
		Arrays.sort(meetingList, (a, b) -> {
		    if (a[1] == b[1]) { // 종료 시간이 같으면
		        return a[0] - b[0]; // 시작 시간을 기준으로 오름차순 정렬
		    } else {
		        return a[1] - b[1]; // 종료 시간을 기준으로 오름차순 정렬
		    }
		});
		
		int cnt = 0;
		int endTime = Integer.MIN_VALUE;

		for (int[] meeting : meetingList) {
			int curStart = meeting[0];
			int curEnd = meeting[1];

			// 2. 회의 시작 시간이 이전 종료보다 같거나 크면 선택
			if (curStart >= endTime) {
				cnt++;
				endTime = curEnd;
			}
		}

		System.out.println(cnt);

	}
}

