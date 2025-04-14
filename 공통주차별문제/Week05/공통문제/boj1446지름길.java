package ALGO.공통주차별문제.Week05.공통문제;

import java.util.*;
import java.io.*;

public class boj1446지름길 {
    static class Edge implements Comparable<Edge> {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost; // 비용 오름차순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 지름길 개수
        int D = Integer.parseInt(st.nextToken()); // 고속도로 길이
        ArrayList<Edge>[] graph = new ArrayList[D+1]; // 그래프 인접 리스트: 0~D까지 (D+1개)
        for (int i = 0; i <= D; i++) {
            graph[i] = new ArrayList<>();
        }

        // 1. 일반 도로(1km씩 진행) 연결: i에서 i+1로, 비용=1
        for (int i = 0; i < D; i++) {
            graph[i].add(new Edge(i+1, 1));
        }

        // 2. 지름길 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            // 지름길 도착점이 D 이하일 때만 의미가 있음
            if (E <= D) {
                graph[S].add(new Edge(E, dist));
            }
        }

        // 다익스트라를 위해 거리 배열 준비
        int[] distArr = new int[D+1];
        Arrays.fill(distArr, Integer.MAX_VALUE);
        distArr[0] = 0; // 시작점(0km) 거리 = 0

        // 우선순위 큐 (비용 오름차순)
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0)); // (노드=0, 비용=0)

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int currentPos = cur.to;
            int currentCost = cur.cost;

            // 이미 더 짧은 경로로 방문한 적 있으면 패스
            if (distArr[currentPos] < currentCost) continue;

            // D에 도달했다면 굳이 더 볼 필요가 없음 (최소 비용)
            if (currentPos == D) break;

            // 인접 간선 확인
            for (Edge next : graph[currentPos]) {
                int nextPos = next.to;
                int nextCost = currentCost + next.cost;

                // 더 짧은 경로 발견 시 갱신
                if (nextCost < distArr[nextPos]) {
                    distArr[nextPos] = nextCost;
                    pq.offer(new Edge(nextPos, nextCost));
                }
            }
        }

        // distArr[D] 가 최단 거리
        System.out.println(distArr[D]);
    }
}
