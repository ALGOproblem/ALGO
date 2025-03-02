package DFS입문.MIRIM.solved.virus_2606;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Virus_2606 {
    static List<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int N;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 바이러스 -> 연결된 컴퓨터 모두 바이러스
        // 1번 -> 감염되게 되는 컴퓨터의 수(1번 제외) -> 너비 우선, 깊이 우선 모두 가능
        N = sc.nextInt(); // 컴퓨터의 수
        int V = sc.nextInt(); // 간선의 수

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
        } // 인덱스 번호 = 컴퓨터 번호

        for (int i = 0; i < V; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        visited = new boolean[N + 1];
        dfs(1);
        System.out.println(count);
    }

    static void dfs(int v) {
        if (!visited[v]) {
            visited[v] = true;
            count++;

            if (v == 1)
                count--;

            for (int i = 0; i < graph.get(v).size(); i++) {
                dfs(graph.get(v).get(i));
            }
        }
    }// dfs
}
