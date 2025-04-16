package 공통주차별문제.Week07.EIZI;
import java.util.*;

public class Main {
    static class Meeting { // 정렬 활용하려고
        int start, end;
        public Meeting(int s, int e) {
            start = s; 
            end = e;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Meeting[] meetings = new Meeting[N];

        for (int i = 0; i < N; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            meetings[i] = new Meeting(s, e);
        }

        //종료 빠른 순으로 정렬
        Arrays.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                if (o1.end == o2.end) {
                    return o1.start - o2.start;
                }
                return o1.end - o2.end;
            }
        });

        // P[i] = i번째 회의와 겹치지 않는 가장 마지막 회의의 인덱스
        int[] P = new int[N];
        for (int i = 0; i < N; i++) {
            P[i] = -1; // 초기값
        }
        int[] endTimes = new int[N];
        for (int i = 0; i < N; i++) {
            endTimes[i] = meetings[i].end;
        }

        // i번째 회의의 start와 겹치지 않는 회의 j를 찾음 (meetings[j].end <= meetings[i].start)
        for (int i = 0; i < N; i++) {
            int startI = meetings[i].start;
            // 이진 탐색으로 end <= startI 인 j의 최대 인덱스 찾기
            int left = 0, right = i - 1;
            int pos = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (endTimes[mid] <= startI) {
                    pos = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            P[i] = pos;
        }

        int[] dp = new int[N];
        // dp[0] = 1 (첫 회의 하나만)
        dp[0] = 1;

        //DP까지는 생각해냈는데 도저히 점화식이 생각이 안나서 이 부분은 GPT의 도움을 받았다...
        // 원래는 GREEDY로 푸는게 시간복잡도가 더 깔끔하다고 한다.

      
        for (int i = 1; i < N; i++) {
            // 회의 i를 포함하지 않는 경우: dp[i-1]
            int notUse = dp[i - 1];
            // 회의 i를 포함하는 경우: 1 + dp[P(i)] (P(i)가 -1이면 0)
            int use = 1;
            if (P[i] != -1) {
                use += dp[P[i]];
            }
            dp[i] = Math.max(notUse, use);
        }
        System.out.println(dp[N - 1]);
        sc.close();
    }
}
