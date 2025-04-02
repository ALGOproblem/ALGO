import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA8275_햄스터 {
    static int[] arr;  // 사용되지 않음 (지워도 무방)
    static int N;  // 우리 수
    static int X;  // 각 우리에 넣을 수 있는 최대 햄스터 수
    static int M;  // 조건의 수
    static int[] hamster;  // 각 우리에 넣은 햄스터 수
    static boolean[] nocheck;  // 조건에 포함된 우리인지 표시
    static Map<Integer, int[]> map;  // 조건 저장 (key: 번호, value: [from, to, sum])
    static int hammax;  // 최대 햄스터 수 저장
    static int[] ans;  // 정답 배열 (최대일 때의 햄스터 배치)
    static PriorityQueue<int[]> pq;  // 사용되지 않음 (지워도 무방)

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());  // 우리 수
            X = Integer.parseInt(st.nextToken());  // 각 우리에 넣을 수 있는 최대 햄스터 수
            M = Integer.parseInt(st.nextToken());  // 조건 수
            hamster = new int[N + 1];  // 1-based 인덱스

            map = new HashMap<>();
            nocheck = new boolean[N + 1];  // 조건에 포함된 우리 표시

            // 조건 입력 받기
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int N1 = Integer.parseInt(st.nextToken());  // 시작 인덱스
                int N2 = Integer.parseInt(st.nextToken());  // 끝 인덱스
                int hamnum = Integer.parseInt(st.nextToken());  // 총합 조건

                map.put(i, new int[]{N1, N2, hamnum});  // 조건 저장

                // 조건에 포함된 우리 체크
                for (int k = N1; k <= N2; k++) {
                    nocheck[k] = true;
                }
            }

            // 조건에 포함되지 않은 우리에는 최대치 넣기
            for (int i = 1; i <= N; i++) {
                if (!nocheck[i]) hamster[i] = X;
            }

            hammax = -1;  // 초기 최대값
            ans = new int[N + 1];  // 정답 배열 초기화

            back(1);  // 백트래킹 시작

            // 출력
            if (hammax == -1) System.out.println("#" + tc + " " + -1);
            else {
                System.out.print("#" + tc + " ");
                for (int i = 1; i <= N; i++) {
                    System.out.print(ans[i] + " ");
                }
                System.out.println();
            }
        }
    }

    // 백트래킹: 모든 가능한 햄스터 배치 탐색
    public static void back(int depth) {
        if (depth == N + 1) {  // 모든 우리에 값 설정 완료
            check();  // 조건 검증
        } else if (!nocheck[depth]) {  // 조건에 포함되지 않은 경우 그대로 다음으로
            back(depth + 1);
        } else {
            // 조건에 포함된 우리라면 0 ~ X까지 시도
            for (int i = 0; i <= X; i++) {
                hamster[depth] = i;
                back(depth + 1);
            }
        }
    }

    // 현재 햄스터 배치가 조건을 만족하는지 확인
    public static void check() {
        int sum;

        // 각 조건에 대해 검증
        for (int i = 0; i < M; i++) {
            sum = 0;
            int[] temp = map.get(i);
            for (int k = temp[0]; k <= temp[1]; k++) {
                sum += hamster[k];
            }
            if (sum != temp[2]) return;  // 조건 불만족 → 종료
        }

        // 조건을 모두 만족했으면 총 햄스터 수 계산
        sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += hamster[i];
        }

        // 최댓값 갱신
        if (sum > hammax) {
            answer();  // 현재 배치 저장
            hammax = sum;
        }
    }

    // 현재 배치를 정답 배열에 저장
    public static void answer() {
        for (int i = 1; i <= N; i++) {
            ans[i] = hamster[i];
        }
    }
}