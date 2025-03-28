# APS 응용 DAY08_09 그래프 최소 비용

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

- 연결된 상태와 연결되지 않은 상태, 2가지로 섬을 나눈다.
- 연결된 상태 : parent가 같다.
- 방문 배열: parent가 같은지 여부로 판단

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
