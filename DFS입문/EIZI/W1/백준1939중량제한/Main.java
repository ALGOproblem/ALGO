package 백준1939중량제한;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, start, end;
    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("res/문제1939중량제한.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 섬 개수
        M = Integer.parseInt(st.nextToken()); // 다리 개수

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int minWeight = 1;
        int maxWeight = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[A].add(new int[]{B, weight});
            graph[B].add(new int[]{A, weight});
            maxWeight = Math.max(maxWeight, weight);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(binarySearchBFS(minWeight, maxWeight));
    }

    public static int binarySearchBFS(int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;

            if (canCrossBFS(mid)) {
                left = mid + 1; // mid 가능하면 더 큰 값도 가능할지 확인
            } else {
                right = mid - 1; // mid 불가능하면 줄이기
            }
        }
        return right; // 최적의 중량 반환
    }

    public static boolean canCrossBFS(int mid) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == end) return true; // 목적지 도착하면 가능

            for (int[] next : graph[cur]) {
                int nextIsland = next[0];
                int weightLimit = next[1];

                if (!visited[nextIsland] && weightLimit >= mid) {
                    queue.add(nextIsland);
                    visited[nextIsland] = true;
                }
            }
        }
        return false; // 도착할 수 없으면 false 반환
    }
}
