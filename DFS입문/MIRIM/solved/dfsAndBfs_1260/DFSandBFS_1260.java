package DFS입문.MIRIM.solved.dfsAndBfs_1260;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DFSandBFS_1260 {
    static int N;
    static int M;
    static int V;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 정점 개수
        M = sc.nextInt(); // 간선의 개수
        V = sc.nextInt(); // 탐색 시작 정점 번호

        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<Integer>());
        }
        //isUsed = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dfs(V);
        System.out.println();
        Arrays.fill(visited, false);
        bfs(V);

    }

    static void dfs(int v) {
        if (v <= N) {
            System.out.print(v + " ");
            visited[v] = true;
            Collections.sort(graph.get(v));

            for (int i = 0; i < graph.get(v).size(); i++) {
                if (visited[graph.get(v).get(i)]) continue;
                dfs(graph.get(v).get(i));
            }
        }

    }

    static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;

        while(!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            Collections.sort(graph.get(current));
            for (int i = 0; i < graph.get(current).size(); i++) {
                int next = graph.get(current).get(i); // 인접 노드
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

    }
}
