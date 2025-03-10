package week1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준1446지름길 {
    static int[] arr;
    static int D;
    static int[][] shortcuts;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/백준1446지름길.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 지름길 개수
        D = Integer.parseInt(st.nextToken()); // 고속도로 길이

        // 배열 초기화 (최대값으로 설정)
        arr = new int[D + 1];
        Arrays.fill(arr, Integer.MAX_VALUE);
        arr[0] = 0; // 시작 지점은 거리 0

        // 지름길 정보 저장
        shortcuts = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            if (end <= D) { // 고속도로 범위 내에서만 지름길 사용 가능
                shortcuts[i] = new int[]{start, end, distance};
            }
        }

        // **한 칸씩 이동하면서 모든 경우를 고려**
        for (int i = 0; i <= D; i++) {
            // 기본 도로 이동 (한 칸 이동할 때 걸리는 시간)
            if (i > 0) {
                arr[i] = Math.min(arr[i], arr[i - 1] + 1);
            }

            // 현재 위치에서 도달할 수 있는 지름길 확인
            for (int[] shortcut : shortcuts) {
                int start = shortcut[0];
                int end = shortcut[1];
                int distance = shortcut[2];

                if (start == i && end <= D) { 
                    arr[end] = Math.min(arr[end], arr[start] + distance);
                }
            }
        }

        System.out.println(arr[D]); // 최단 거리 출력
    }
}
