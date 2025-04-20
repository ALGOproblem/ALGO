// 처음에는 단순히 높이가 N-1인 노드 수만 더하면 안되는 건가 생각함
// 하지만 어떤 노드를 얼리어답터로 선택하느냐에 따라 전체 최소 개수가 달라짐
// 그래서 DP로 각 노드의 상태(얼리/비얼리)에 따라 자식의 최적 선택을 반영해야 함
// 예를 들면 부모가 얼리어답터일 수도 있고, 자식이 얼리어답터일 수도 있고..
//얼리어답터 ❌인 노드는 → 친구가 모두 ⭕여야 함
//얼리어답터 ⭕인 노드는 → 친구는 아무거나 가능

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        graph = new ArrayList[n + 1];
        dp = new int[n + 1][2];  // [0] = 비어답터, [1] = 얼리 어답터
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        dfs(1);
        // 루트 노드가 얼리어답터 or 비어답터 중 최소 선택 -> 이 부분 놓침
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int cur) {
        visited[cur] = true;
        dp[cur][0] = 0;  // 현재 노드가 비어답터
        dp[cur][1] = 1;  // 현재 노드가 얼리 어답터
        for (int child : graph[cur]) {
            if (!visited[child]) {
                dfs(child);
                // 내가 얼리 어답터가 아니면, 자식은 반드시 얼리 어답터
                dp[cur][0] += dp[child][1];
                // 내가 얼리 어답터라면, 자식은 얼리든 아니든 최소 선택 가능
                dp[cur][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
}
