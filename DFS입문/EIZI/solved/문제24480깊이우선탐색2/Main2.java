package 문제24480깊이우선탐색2;

import java.io.*;
import java.util.*;

public class Main2 {
    static List<Integer>[] arr; // 인접 리스트
    static int[] visitOrder; // 방문 순서 저장 배열
    static boolean[] visited; // 방문 여부 배열
    static int order = 1; // 방문 순서 카운트

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/문제24480깊이우선탐색2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점 수
        int M = Integer.parseInt(st.nextToken()); // 간선 수
        int R = Integer.parseInt(st.nextToken()); // 시작 정점

        arr = new ArrayList[N + 1];
        visitOrder = new int[N + 1]; // 방문 순서를 저장할 배열
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        // 간선 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[A].add(B);
            arr[B].add(A);
        }

        // **각 정점의 인접 리스트를 내림차순으로 정렬**
        for (int i = 1; i <= N; i++) {
            arr[i].sort(Collections.reverseOrder());
        }

        // **DFS 실행 (한 번만 실행하면 됨)**
        dfs(R);

        // **모든 정점의 방문 순서 출력 (방문하지 않은 정점은 0 출력)**
        for (int i = 1; i <= N; i++) {
            System.out.println(visitOrder[i]);
        }
    }

    public static void dfs(int node) {
        visited[node] = true;
        visitOrder[node] = order++; // 현재 정점 방문 순서 기록

        for (int next : arr[node]) { // 인접 노드 탐색
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
    public static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true; // ✅ 방문 체크를 Queue에 추가할 때 해야 함!
        visitOrder[node] = order++;

        while (!queue.isEmpty()) {
            int current = queue.poll(); // Queue에서 꺼냄

            for (int next : arr[current]) {
                if (!visited[next]) { // ✅ 방문하지 않은 경우에만 Queue에 추가
                    queue.add(next);
                    visited[next] = true; // ✅ Queue에 추가할 때 방문 체크
                    visitOrder[next] = order++; // 방문 순서 저장
                }
            }
        }
    }

}
