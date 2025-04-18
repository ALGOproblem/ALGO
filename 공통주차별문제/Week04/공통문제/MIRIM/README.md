# Week04 공통 문제

## 1. [문제 16438 - 원숭이 스포츠](https://www.acmicpc.net/problem/16438)
### (1) 문제 접근 방식
#### 분할 정복
k일차에 대하여 N/2마리까리 같은 팀을 하게 한다.<br>
같은 팀을 한 원숭이들을 또다시 절반으로 나누어 다른 팀을 하게 한다.

#### N < 7
이러한 상황에서는 분할정복의 경우의 수보다 7일이라는 시간이 더 길게 된다.<br>
그렇기에 최초 팀인 'AAAA...AA'와 같은 팀 배정이 나왔다면, 끝자리만 B로 바꾸어준다. 

### (2) 분할정복
| 문제를 작게 나누어, 각 부분을 재귀적으로 해결, 결과를 최종 합쳐서 해결한다.

#### 발상
| 문제를 반으로 나눌 수 있을까? 작은 문제로 쪼개면 전체를 구할 수 있지 않을까?

#### 활용도 높은 문제
- 문제를 쪼갤 수 있을 때
    - 배열, 수열, 트리 같이 절반으로 나눌 수 있을 때
- 재귀적으로 같은 작업 반복
    - 정렬, 탐색, 누적 계산
- 부분 문제의 정답들의 합 = 전체 정답
    - 병합 정렬

#### 예시
- 병합 정렬
- 퀵 정렬
- 이진 탐색
- 행렬 곱, 지수 계산
- 트리 순회, 재귀적 DFS

### (3) 풀이 : 분할 정복, 재귀
``` java
static void binaryTeam(int start, int end, int day) {

    if (day == 7) return;

    int mid = (start + end) / 2;
		
	for (int i = start; i <= mid; i++)
        monkeys[day][i] = 'A';
		
		for (int i = mid + 1; i <= end; i++)
			monkeys[day][i] = 'B';
		
		binaryTeam(start, mid, day + 1);
		
		binaryTeam(mid + 1, end, day + 1);
	}
```

---

## 2. [문제 17136 - 색종이 붙이기](https://www.acmicpc.net/problem/17136)
### 문제 접근 방식
#### (1) 전체 :  dfs + 가지치기
- 큰 색종이부터 붙이는 시도 -> 붙이기 가능 -> 붙이고 다음으로 / 원상복귀(작은 종이로 이어짐)
    ``` java
    for (int idx = 5; idx > 0; idx--) {
        // 해당 종이를 붙일 수 있는지 검사 -> 붙이기, 더 작은 종이 붙이기
        if (amount[idx] > 0 && isValid(r, c, idx)) {
            // 1. 해당 종이 붙이기 -> 다른 영역으로 넘어가기
            select(r, c, idx, 0);
            amount[idx]--;
            
            dfs(r, c + 1, count + 1);
            
            // 2. 원상복귀, 작은 종이 붙이기
            amount[idx]++;
            select(r, c, idx, 1);
        }
    }
    ```

- dfs 과정 중 현재 종이수가 구해진 최소 종이수보다 크면 구하지 않는다.
    ``` java
    // 가지치기
    if (minCount <= count) return;
    ```
  
#### (2) 일부: 완전 탐색
- 붙이기가 가능한지 판단할 때 해당 (r, c)에서 종이 영역만큼 조건들을 확인한다.
- 조건
    - map의 값이 1이어야 함
    - map의 내부 값이어야 함

#### (3) 방문 확인
재귀 활용으로 map을 그대로 활용 가능하다. <br>
마지막 (9, 10)에 도달 시 map 모든 영역에 방문 확인(`isAllCovered`)을 거쳐야 `minCount` 값을 갱신할 수 있다.

---

## 3. [문제 16946 - 벽 부수고 이동하기 4](https://www.acmicpc.net/problem/16946)
### (1) 문제 접근 방식
- 1차 : delta 방식으로 bfs 완전탐색  -> 메모리 초과
- 탐색 수 자체를 줄일 수 있는 방법으로 **그룹화(grouping)** 를 이용

### (2) 그룹화(Grouping)
1. 애니팡처럼 벽 안에 연결된 1들을 하나의 그룹으로 묶기
2. `group`에 각 그리드에 속하는 group 번호를 넣음
3. `Map<Integer, Integer> groupInfo`에 그룹번호, 그룹 크기를 저장

### (3) 풀이 방식
#### 1. 그룹화
``` java
group = new int[N][M];
for (int i = 0; i < N; i++) {
	for (int j = 0; j < M; j++) {
		if (map[i][j] == 0 && group[i][j] == 0)
			groupBFS(i, j);
	}
} // 그룹화
```

#### 2. groupBFS
``` java
static void groupBFS(int r, int c) {
	Queue<int[]> queue = new LinkedList<>();
	queue.offer(new int[]{r, c}); // 행, 열
	group[r][c] = groupIdx;
	int size = 1;

	while(!queue.isEmpty()) {
		int[] cur = queue.poll();
		int curR = cur[0];
		int curC = cur[1];

		for (int i = 0; i < 4; i++) {
			int nr = curR + dr[i];
			int nc = curC + dc[i];

			if (nr < 0 || nc < 0 || nr >= N || nc >= M ) continue;

			if (map[nr][nc] == 0 && group[nr][nc] == 0) {
				queue.offer(new int[]{nr, nc});
				group[nr][nc] = groupIdx;
				size++;
			}
		}// delta
	}// while

	groupInfo.put(groupIdx++, size);
}// bfs
```

#### 3. 근처 그룹 수 세기, 그룹크기 추가
``` java
result = new int[N][M];
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j] == 1) {
				Set<Integer> nearGroups = new HashSet<>();
				int sum = 1;

				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];

					if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 1) continue;

					int gid = group[nr][nc];
					if (nearGroups.add(gid)) {
						sum += groupInfo.get(gid);
					}
				}
				result[i][j] = sum % 10;
...
```
---

## 4. [문제 9081 - 단어 맞추기](https://www.acmicpc.net/problem/9081)
### (1) 문제 접근 방식
- 사전 순 정렬
- 뒤 -> 앞으로 조건을 확인
- 조건?
	- 예시: abc일때, c는 자신보다 작은 b를 확인하고 서로 swap한다.
 	- 예시: abzc일때, c는 자신보다 큰 z는 넘어간다. b를 확인하고 swap한다.
	- 예시: kbzca일때, c는 자신보다 큰 z는 넘어간다. b를 확인하고 swap한다. kczba가 된 후 ba는 ab로 정렬한다. 따라서 kczab가 된다.
- 위와 같이 내림차순, swap, 오름차순 정렬이 이뤄져야 한다.

