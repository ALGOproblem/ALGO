# APS 응용 DAY08_09 그래프 최소 비용

## Prim vs. Kruskal vs. Dijkstra

| 항목              | **Prim**                                 | **Kruskal**                                | **Dijkstra**                               |
| ----------------- | ---------------------------------------- | ------------------------------------------ | ------------------------------------------ |
| **목적**          | **모든 정점을 최소 비용으로 연결** (MST) | **모든 정점을 최소 비용으로 연결** (MST)   | **시작점으로부터 모든 정점까지 최단 거리** |
| **그래프 유형**   | **무방향**, 연결 그래프                  | **무방향**, 연결 그래프                    | 방향/무방향 그래프 모두 가능               |
| **시작점**        | 필요함 (어디서든 가능)                   | 필요 없음 (간선 위주로 시작)               | 시작점 꼭 필요                             |
| **핵심 자료구조** | `minEdge[]`, `visited[]`, PQ(선택)       | `Edge 리스트`, `Union-Find`                | `dist[]`, `visited[]`, PQ                  |
| **우선순위 기준** | **가장 가까운 정점**                     | **가장 비용이 적은 간선**                  | **최단 거리**                              |
| **사이클 허용?**  | ❌ (사이클 생기면 안 됨)                 | ❌ (사이클 생기면 버림)                    | ⭕ (사이클 있어도 무방)                    |
| **결과**          | MST의 총 비용 (N-1개의 간선)             | MST의 총 비용 (N-1개의 간선)               | 시작점에서 각 정점까지 최단 거리           |
| **시간복잡도**    | `O(N^2)` or `O(E log N)` (PQ 사용 시)    | `O(E log E)`                               | `O((V + E) log V)`                         |
| **사용 예시**     | 통신망 구축/전력선 연결/섬 연결 문제     | 도로/케이블 설치 비용 최소화/네트워크 연결 | GPS 경로 탐색/게임 캐릭터 이동             |

| 상황/조건                              | 추천 알고리즘                      |
| -------------------------------------- | ---------------------------------- |
| **모든 정점을 연결하는 것이 목표**     | Prim 또는 Kruskal (MST)            |
| **비용이 작은 간선을 우선 사용**       | Kruskal + Union-Find               |
| **정점 수는 작고 간선이 많은 경우**    | Prim + PriorityQueue               |
| **시작점에서 이동 경로가 필요한 경우** | Dijkstra                           |
| **사이클 여부를 체크해야 할 때**       | Kruskal (명확한 Union-Find로 체크) |
| **한 지점에서 여러 지점까지 거리**     | Dijkstra                           |

## 7465. 창용 마을 무리의 개수

### 문제 접근

- 1 ~ N번의 사람들의 아는 사이 무리 개수 구하기
- 연결되어 있으면 같은 그룹
- 그룹의 개수를 구하라

| 해당 그룹 내부의 사람 수는 중요하지 않다.<br>
| 연결 묶음만 신경쓰자.

### 풀이

- `union-find`를 활용한다.

```java
static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}

static void union(int x, int y) {
	int px = find(x);
	int py = find(y);

	if (px != py) {
		parent[py] = px;
	}

}
```

- 그룹 수 확인을 위해 `Set`을 사용

```java
Set<Integer> set = new HashSet<>();
for (int i = 1; i <= N; i++) {
	find(i);
	if (!set.contains(parent[i])) {
		set.add(parent[i]);
	}
}
```

---

## 3289. 서로소 집합

- 위와 같이 `union-find`를 활용하여 문제를 풀었음.

---

## 1251. 하나로

### (1) N개의 섬들의 최소 비용

1. N개의 섬들을 모두 이어야 함 = union-find 생각
2. 경로는 중요하지 않음 = 다익스트라X
3. 시작점, 종료점이 정해지지 않음 = 다익스트라X
4. 방향성 없음

### (2) Kruskal 알고리즘 - union-find

- 최소 비용: **간선에 집중해 최소 가중치를 가진 간선부터 더한다.**

- 사용한 간선, 사용하지 않은 간선으로 나눈다.
- 연결된 상태 : parent가 같다.
- 방문 배열: parent가 같은지 여부로 판단
- 시작점: 필요 없음

```java
for (Edge edge : edges) {
	if (union(edge.from, edge.to)) {
		total += edge.weight;
		count++;
		if (count == N - 1) break;
	}
}

Collections.sort(edges);
```

```java
for (Edge edge : edges) {
	if (union(edge.from, edge.to)) {
		total += edge.weight;
		count++;
		if (count == N - 1) break;
	}
}
```

### (3) Prim 알고리즘

- 하나의 정점에서 연결된 간선들 중 선택해 최소비용 구하기
- 연결된 정점, 아닌 정점으로 나눈다. -> 정점 위주로 사고
- 모든 정점 연결 -> visited 배열 사용(union의 개념과 유사)
- 경로 중요하지 않음, 최소 비용만 구하면 된다.

| **정점을 확장해 최소 비용 간선을 구하자**

---

#### minDis

1. 초기화

```java
Arrays.fill(minDis, Long.MAX_VALUE);
minDis[0] = 0;
```

2. 방문할 노드 찾기: 현재 노드에서 가중치가 최소인 노드

```java
for (int j = 0; j < N; j++) {
	if (!visited[j] && minDis[j] < min) {
		min = minDis[j];
		minIdx = j;
	}
}// 현재까지 최소 비용 idx 찾기
```

3. 방문 노드 확정 후, 다른 노드들의 최소비용을 방문 노드 기준으로 갱신시켜주기

```java
for (int j = 0; j < N; j++) {
if (visited[j]) continue;

long dx = graph[minIdx][0] - graph[j][0];
long dy = graph[minIdx][1] - graph[j][1];
long cost = dx * dx + dy * dy;

if (cost < minDis[j]) {
	minDis[j] = cost;
}
}// 갱신
```

---

## 1249. 보급로

### 문제 접근

1. 시작점, 종료점 존재 -> dijkstra?
2. 그리드마다 가중치값이 존재
3. delta로 이동 가능
4. 시작점-종료점까지의 경로에서 최소 비용 = 문제 목적

=> dijkstra 알고리즘

### Dijkstra 알고리즘

#### 필수 요소

- `PriorityQueue` : 가중치 기준 오름차순으로 정렬
- `int[][] distance` : 현재 위치까지의 비용 저장

```java
int[][] distance = new int[N][N];
for (int[] row : distance) {
	Arrays.fill(row, Integer.MAX_VALUE);
}
distance[0][0] = 0;
```

---

## 1238. Contact

### 문제 접근

- 방향성 있는 그래프
- 중복 방문 불가능
- 동시에 연락을 취한다 = 뻗어나감 -> bfs
  - 현재 노드와 연결만 되어있다면 방문 순서(visited 순서로 인한 변화)가 같다.
  - <span style="background-color:#fff5b1">최초 연락 도달(방문) 시 이미 처리된다.</span>
- 가장 나중에 연락을 받게 되는 사람 -> 탐색에서 depth(level)이 가장 큰 값들을 구해야 한다.

**=> bfs를 통해 depth가 가장 큰 번호들 중 최댓값을 구하자.**

### 구현

1. Queue에서 행, 열과 `depth`를 같이 `int[]`로 저장하자.
2. `boolean[] visited`를 통해 중복 방문을 막는다.
   - 이때 경로는 무의미하기에 가능하다.
   - 어차피 depth는 최초 방문에서 정해짐

```java
if (maxDepth < depth) {
	maxDepth = depth;
	max = node;

} else if (maxDepth == depth) {
	max = Math.max(max, node);
}// 큰 값, 깊이 갱신

for (int next : graph[node]) {
	if (visited[next]) continue;

	queue.offer(new int[] {next, depth + 1});
	}// next
```
