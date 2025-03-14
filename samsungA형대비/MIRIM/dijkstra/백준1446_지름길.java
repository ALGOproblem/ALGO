package samsungA형대비.MIRIM.dijkstra;

import java.util.*;

public class 백준1446_지름길 {
    static int N;
    static int D;
    static List<List<int[]>> roads = new ArrayList<>(); // 지름길 저장 - {현재 위치, 누적 이동거리}
    static int[] dis; // 현재 인덱스까지 오는데 걸린 이동거리

    static void dijkstra(int start) {
        dis = new int[D + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;

        // 누적 이동거리가 오름차순으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currIdx = curr[0];
            int currDis = curr[1]; // 우선순위 큐에서 꺼낸 현재까지 값 != 최단거리

            if (currDis > dis[currIdx]) continue; // 이미 다른 최단거리로 도달
            if (currIdx == D) break; // 다익스트라 특성: D에 최초 도착이 바로 최단거리임

            if (currIdx + 1 <= D && currDis + 1 < dis[currIdx + 1]) {
                dis[currIdx + 1] = currDis + 1;
                pq.offer(new int[]{currIdx + 1, dis[currIdx + 1]});
            }// 지름길 아닌 길로 이동

            for (int[] road : roads.get(currIdx)) {
                int nextIdx = road[0];
                int nextDis = road[1]; // 현재-다음까지의 이동거리
                int newDis = dis[currIdx] + nextDis; // 누적 이동거리

                if (nextIdx <= D && newDis < dis[nextIdx]) {
                    dis[nextIdx] = newDis;
                    pq.offer(new int[] {nextIdx, newDis});
                }
            }// 지름길 검토 후 최단거리라면 이동
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        D = sc.nextInt();

        for (int i = 0; i <= D; i++) {
            roads.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int distance = sc.nextInt();
            if (start <= D && end <= D && end - start > distance) {
                roads.get(start).add(new int[]{end, distance});
            }// 기존 도로보다 지름길인 경우만 저장
        }// 입력

        dijkstra(0);

        System.out.println(dis[D]);
    }
}
