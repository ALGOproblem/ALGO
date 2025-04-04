package 공통주차별문제.Week06.공통문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_1916_최소비용구하기 {
    static BufferedReader input;
    static StringTokenizer st;

    static int[] dist; // 비용 - 거리
    static boolean[] visited;

    static List<int[]>[] adj; // 인접 리스트

    // - 도시 1부터 N까지
    // - 최소 비용 -> 다익스트라
    // - priorityQueue 사용

    // 1. 정점을 하나 꺼낸다.
    // 2. 인접한 정점을 방문한다.
    // 3. 방문 아니고 거리가 기존 값보다 현재 정점에서 이동이 가까운 경우 거리 갱신
    public static void main(String[] args) throws NumberFormatException, IOException {
        input = new BufferedReader(new InputStreamReader(System.in));
        input = new BufferedReader(new FileReader("input.txt"));

        int N = Integer.parseInt(input.readLine()); // 도시 - 정점
        int M = Integer.parseInt(input.readLine()); // 버스 - 간선

        // 초기화
        adj = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        // 버스 정보
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(st.nextToken()); // 출발지
            int end = Integer.parseInt(st.nextToken()); // 도착지
            int cost = Integer.parseInt(st.nextToken()); // 비용

            adj[start].add(new int[] { end, cost });
        }

        // 초기화
        dist = new int[N + 1]; // 도시 1부터
        Arrays.fill(dist, Integer.MAX_VALUE);
        visited = new boolean[N + 1];
        //

        st = new StringTokenizer(input.readLine());
        int startCity = Integer.parseInt(st.nextToken()); // 시작 도시
        int endCity = Integer.parseInt(st.nextToken()); // 도착 도시

        dijkstra(startCity);

        System.out.println(dist[endCity]);
    }// main

    static void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        pq.offer(new int[] { start, 0 });
        dist[start] = 0;

        while (!pq.isEmpty()) {
            // 정점을 하나 꺼낸다.
            int[] cur = pq.poll();
            int curCity = cur[0];

            if (visited[curCity]) {
                continue;
            }
            visited[curCity] = true;

            // 인접 정점을 방문하며 갱신한다.
            for (int[] city : adj[curCity]) {
                int nextCity = city[0];
                int nextCost = city[1];

                // 기존 cost > 현재 cost + 비용
                if (!visited[nextCity] && dist[nextCity] > dist[curCity] + nextCost) {
                    dist[nextCity] = dist[curCity] + nextCost;
                    pq.offer(new int[] { nextCity, dist[nextCity] });
                }
            } // for
        } // while
    } // dijkstra
}
