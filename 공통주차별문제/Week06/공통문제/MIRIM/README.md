# Week06 공통 문제

## 문제 1 : **백준 [17615 - 볼 모으기](https://www.acmicpc.net/problem/17615)**

### 문제 접근

1. 볼 하나의 색만 선택
2. 왼쪽, 오른쪽 둘 중 하나의 방향으로만 모으기
3. 맨 끝에 존재하는 볼들의 개수는 무시해도 된다(같은 색, 정해진 방향에 대해서)

### 잘못된 접근

- 오름차순으로만 정렬하려고 했음
- 그룹이름과 그룹 사이즈를 주고 받는 그룹화방식을 떠올림
- 너무 어렵게 접근하여 알아채지 못함.

### 풀이

1. 총 빨간 공, 파랑 공의 개수 계산
2. 왼쪽 끝부터 존재하는 빨간 공의 개수 세기
3. 오른쪽 끝부터 존재하는 빨간 공의 개수 세기
4. 왼쪽 끝부터 존재하는 파란 공의 개수 세기
5. 오른쪽 끝부터 존재하는 파란 공의 개수 세기
6. 2 ~ 5에서 최대가 되는 값 찾기

---

## 문제 2 : 백준 [15486-퇴사 2](https://www.acmicpc.net/problem/15486)

### 문제 접근

1. 시간순으로 흘러감, 시간 제한(상담이 끝나는 날)
2. 시간마다 소요 시간(제한 내 존재해야함), 가치가 존재
3. 주어진 시간 내 최대 가치를 만들어내야함.
   => dp를 사용하자

### 잘못된 접근

- 이때 완전 탐색으로 풀 수 있지만, 시간이 오래 소요됨

### 풀이

#### 1. 정산은 상담이 끝난 다음 날 들어온다. 따라서 `N + 1`일에 최종 정산

```java
int[] dp = new int[N + 2]; //N + 1일차에 정산하자
```

#### 2. dp

```java
for (int i = 1; i <= N; i++) {
    int t = time[i];// 소요 시간

    dp[i] = Math.max(dp[i], dp[i - 1]);

    // 해당 일의 상담 비교
    if (i + t <= N + 1)
        dp[i + t] = Math.max(dp[i + t], dp[i] + price[i]);

}
dp[N + 1] = Math.max(dp[N], dp[N + 1]); // 최종 정산
```

---

## 문제 3 : [백준 1463번 : 1로 만들기](https://www.acmicpc.net/problem/1463)

### 문제 접근

경로 탐색, 최소 비용, 가중치 = 1 -> bfs 사용

### 잘못된 접근

지금 문제를 다시 보니 dp방식과 혼합되어 있는데, queue를 안 썼어도 될 것 같다.

### 풀이

#### 1. 자료구조

- `int[] distance = new int[num + 1];` : 최소 연산 횟수
- `int cur = curNode[0];` : 현재 숫자
- `int curDis = curNode[1];` : 현재 숫자까지의 최소 연산 횟수

#### 2. 조건

- 3으로 나누어질때
  - 3으로 나누는 연산 시행 = `curDis + 1` : 연산 1번 더 추가
  - 기존의 3으로 나누는 연산이 시행될 결과 : `distance[cur / 3]`
  - `cur / 3`을 최솟값으로 갱신
- 2로 나눌 때, 1을 뺄 때도 동일한 논리

```java
if (cur % 3 == 0 && curDis + 1 < distance[cur / 3]) {
    distance[cur / 3] = curDis + 1;
    queue.offer(new int[] {cur / 3, curDis + 1});
}
```

---

## 문제 4 : [백준 1916 최소비용 구하기](https://www.acmicpc.net/problem/1916)

### 문제 접근

1. 시작점, 도착점 명확, 가중치 다름, 최소 비용 -> 다익스트라
2. 유향 그래프

### 풀이

#### 1. 자료구조

```java
graph = new ArrayList[N + 1];
```

#### 2. 다익스트라 사용

- 현재까지 비용이 최소라면 갱신
- 가장 먼저 도착한 값이 최솟값

```java
if (nextDis < distance[next]) {
    distance[next] = nextDis;
    pq.add(new int[] {next, nextDis});
}
```
