package DFS입문.MIRIM.w1.limitWeights_1939;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LimitWeights_1939 {
    static int N, M, v1, v2;
    static ArrayList<ArrayList<Bridge>> graph = new ArrayList<>();

    static class Bridge {
        int to, weight;

        Bridge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        int maxWeight = 0;
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int weight = sc.nextInt();
            graph.get(a).add(new Bridge(b, weight));
            graph.get(b).add(new Bridge(a, weight));

            if (maxWeight < weight)
                maxWeight = weight;
        } // 다리 입력

        v1 = sc.nextInt();
        v2 = sc.nextInt();// 공장 정점 입력

        // 이진 탐색 : mid 무게로 건너갈 수 있나? 접근
        // 체크 방식이 bfs인 것

        int left = 1;
        int right = maxWeight;

        // 목표: 가능한 최대 중량 찾기
        while (left <= right) {
            int mid = (left + right) / 2; // 체크할 중량

            if (bfs(mid)) {
                // mid 중량 통과됨 = mid 여유 -> mid보다 더 큰 값 탐색
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);

    }// main

    static boolean bfs(int weightLimit) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        // 시작점 v1부터 탐색
        queue.add(v1);
        visited[v1] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll(); // 현재 노드

            if (currentNode == v2)
                return true; // 도착 시 성공

            // 중량 제한 체크 & 연결된 다리들 큐에 삽입
            for (Bridge nextBridge : graph.get(currentNode)) {

                if (!visited[nextBridge.to] && nextBridge.weight >= weightLimit) {
                    visited[nextBridge.to] = true;
                    queue.add(nextBridge.to);
                }
            }
        } // while문

        return false;
    }// bfs
}
