package 실습.김미림.APS응용_Day11_그래프응용;

import java.util.*;

public class swea_1267_작업순서 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {
            int V = sc.nextInt(); //정점의 개수
            int E = sc.nextInt(); // 간선의 개수

            List<Integer>[] graph = new ArrayList[V + 1];
            for (int i = 0; i < V + 1; i++) {
                graph[i] = new ArrayList<>();
            }// 객체 생성

            int[] degree = new int[V + 1];

            for (int i = 0; i < E; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();

                graph[from].add(to);
                degree[to]++;
            }// 입력

            Queue<Integer> queue = new LinkedList<>();
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= V; i++) {
                if (degree[i] == 0)
                    queue.offer(i);
            }// 차수 0인 정점 입력

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                sb.append(cur + " ");

                for (int next : graph[cur]) {
                    if (degree[next] == 0) continue;

                    degree[next]--;
                    if (degree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }

            System.out.printf("#%d %s\n", tc, sb.toString());

        }// tc
        sc.close();
    }//main

}
