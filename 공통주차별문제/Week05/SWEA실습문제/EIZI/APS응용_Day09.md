# SWEA APS 응용 DAY9

## swea1238contact

### 문제 핵심
- 유향 그래프
- 시작하는 정점이 주어짐. 가중치가 없음 (BFS)

### 내 접근 & 의사코드

```
bfs(start)
  Queue, visited, dist 선언
  while(!queue.isEmpty())
      int cur = queue.poll()
      for (int next : arr[cur])
          if (!visited[next])
                visited[next] = true;
                dist[next] = dist[cur]+1; // 이걸 생각 못하고 따로 int level 선언해야 하나 고민하다가 틀림
                queue.offer(next);

  int maxDist = 0;
  for문 돌면서 MaxDist;
  for문 돌면서 ans구하기

```

---------------------------------------------


## swea1249보급로

### 문제 핵심
- 인접 행렬 그 자체를 구하는 것
- 다익스트라를 써야 하는 것
- 처음에 cost를 어디에다가 써야 하는지 헷갈렸는데 그냥 노드 자체에 저장하면서 갖고 다니는 게 정답!

### 내 접근 & 의사코드

```
dijkstra(startX, startY, starCost)
    pq 선언, int[][] dist 선언 (무한대로 초기화)
    pq.offer(new Node(startx, starty, startCost)
    while(!pq.isEmpty)
        Node cur = pq.poll()
        if (cost> dist[s][e]) continue; // 크면 여기는 더 이상 보지 않아
        if (s==N-1 && e== N-1) ans = dist[s][e]; // 최종적으로 도착했을 때 거리!
        for (int i = 0; i<4; i++)
            int ns, ne 선언
            범위 초과 확인
            if(cost + arr[ns][ne] < dist[ns][ne])
                dist[ns][ne] = nextCost;
                pq.offer(new Node(ns, ne, nextCost))

```

---------------------------------------------
