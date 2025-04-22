package 공통주차별문제.Week08.MIRIM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 백준_2533_사회망서비스 {
    static int N;
    static List<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        graph = new ArrayList[N + 1];
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }// 객체 설정

        for (int i = 1; i < N; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }// 입력

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
        sc.close();
    }// main

    static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0; // 얼리어답터 아님 -> 해당 서브트리에서 필요한 최소 얼리어답터 수
        dp[node][1] = 1; // 얼리어답터 맞음 -> 해당 서브트리에서 필요한 최소 얼리어답터 수

        for (int child : graph[node]) {
            if (!visited[child]) {
                dfs(child);

                dp[node][0] += dp[child][1]; // 얼리어답터 아님 -> 자식들 모두 얼리어답터여야 함! -> 자식이 얼리어답터인 경우의 수 더함
                dp[node][1] += Math.min(dp[child][0], dp[child][1]); // 얼리어답터 맞음 -> 선택 가능 -> 둘 중 최솟값 선택
            }
        }

    }//dfs

}