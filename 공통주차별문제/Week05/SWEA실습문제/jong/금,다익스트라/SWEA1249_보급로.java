import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SWEA1249_보급로 {
    static int N;
    static int[][] road;
    static int[][] dist;
    static int[] diy = {-1, 1, 0, 0}; // 상하좌우 Y 좌표 변화
    static int[] dix = {0, 0, -1, 1}; // 상하좌우 X 좌표 변화

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트케이스 수 입력

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            road = new int[N][N]; // 지도 정보 배열

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int k = 0; k < N; k++) {
                    road[i][k] = str.charAt(k) - '0'; // 문자형 숫자를 정수형으로 변환
                }
            }

            dij(); // 다익스트라 알고리즘 수행

            System.out.println("#" + tc + " " + dist[N - 1][N - 1]); // 결과 출력
        }
    }

    public static void dij() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[2], b[2]); // 누적 거리 기준 정렬
            }
        });

        dist = new int[N][N]; // 최단 거리 저장 배열
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE); // 무한대로 초기화
        }

        dist[0][0] = 0; // 시작점은 거리 0
        pq.offer(new int[] {0, 0, 0}); // 시작 위치 삽입 (Y, X, 비용)

        while (!pq.isEmpty()) {
            int[] now = pq.poll(); // 현재 노드

            if (now[2] > dist[now[0]][now[1]]) continue; // 이미 더 짧은 경로로 방문된 경우 건너뜀
            if (now[0] == N - 1 && now[1] == N - 1) continue; // 도착 지점 도달 시 continue

            for (int i = 0; i < 4; i++) {
                int Y = now[0] + diy[i];
                int X = now[1] + dix[i];

                if (0 <= Y && Y < N && 0 <= X && X < N) { // 범위 내일 경우
                    if (dist[Y][X] > now[2] + road[Y][X]) { // 누적 비용이 더 작으면 갱신
                        dist[Y][X] = now[2] + road[Y][X];
                        pq.offer(new int[] {Y, X, dist[Y][X]}); // 새 위치와 비용 큐에 추가
                    }
                }
            }
        }
    }
}
