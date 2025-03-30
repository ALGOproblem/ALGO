package ALGO.공통주차별문제.Week05.공통문제;
import java.io.*;
import java.util.*;


/*
 * 종강을 맞은 민준이는 고향인 마산으로 내려갈 계획을 짜고 있었다. 
 * 늘 그랬듯, 마산으로 갈 버스를 예약하려던 순간 민준이는 집으로 가는 다른 방법이 떠올랐다. 
 * 그것은 직접 지도를 보고 고향으로 가는 가장 짧은 길을 찾는 것이다.
 * 그때, 먼저 고향으로 내려갔던 친구인 건우에게 연락이 왔다. 
 * 건우는 고향으로 내려가던 중 알 수 없는 일에 휘말려 외딴곳에 혼자 남겨지게 되었다. 
 * 건우는 유일한 구세주인 민준이에게 도움을 청한 것이었다. 그러나 마산의 남자인 민준이에게는 마산이 먼저였다. 
 * 민준이는 처량한 건우를 무시한 채 고향으로 떠나려고 했지만, 만약 고향으로 가는 길에 건우가 있다면 겸사겸사 도움을 줄 수 있을 것 같았다.
 * 지도는 양방향 그래프 형태로 되어있다. 출발지는 1번 정점 마산은 V번 정점이다. 정점은 1~V까지 있다. 건우는 P번 정점에 있다
 * 그리고 항상 1번 정점에서 P번과 V번 정점으로 갈 수 있는 경로가 존재한다
 * 중복되는 간선과 자기 자신을 가리키는 간선은 존재하지 않는다.
 */

 /*
  * 처음에 백트래킹을 써야 하나 싶었는데 V랑 E가 너무 커서 감이 안 잡혀서 GPT에게 질문
  * 세 번의 다익스트라 사용 (다익스트라 알고리즘은 최단 경로의 성질에 의해 항상 그 정점 기준으로 분리된 최단 경로의 합과 같음)
  */

public class boj18233민준이와마산그리고건우 {
    // 정점과 누적 거리를 저장하는 Node 클래스 (다익스트라용)
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;
        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
    
    // 다익스트라 알고리즘: start에서 각 정점까지의 최단 거리를 계산해서 반환
    static int[] dijkstra(int start, ArrayList<Node>[] graph, int V) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curVertex = cur.vertex;
            if (cur.distance > dist[curVertex]) continue;
            for (Node next : graph[curVertex]) {
                int nextVertex = next.vertex;
                int newDist = cur.distance + next.distance;
                if (newDist < dist[nextVertex]) {
                    dist[nextVertex] = newDist;
                    pq.offer(new Node(nextVertex, newDist));
                }
            }
        }
        return dist;
    }
    
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수
        int P = Integer.parseInt(st.nextToken()); // 건우가 위치한 정점
        
        // 1번부터 V번까지 사용 (1-indexed)
        ArrayList<Node>[] graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        // 양방향 간선 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        
        // 1번에서 모든 정점까지의 최단 경로 계산
        int[] distFromStart = dijkstra(1, graph, V);
        // 건우(P번)에서 V번(마산)까지의 최단 경로 계산
        int[] distFromP = dijkstra(P, graph, V);
        
        // 1→V의 최단 경로와 1→P + P→V의 경로 길이를 비교
        // 건우가 있는 경로가 최단 경로와 같다면 "SAVE HIM", 아니라면 "GOOD BYE" 출력
        if (distFromStart[V] == distFromStart[P] + distFromP[V]) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }
}
