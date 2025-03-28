# SWEA APS 응용 DAY7

## swea1251 sw문제 응용 하나로

### 문제 핵심
- x, y의 좌표가 2차원이 아닌 하나의 줄글로 주어짐 -> arr[2][N] 배열로 좌표 받기
- 크루스칼 알고리즘 (정점의 개수 크고, 간선의 개수가 작을 때 유용) 활용 PriorityQueue 활용

### 내 접근 & 의사코드

```
while(cntConnect<N){
    edge e = pq.poll()
    if (combine(e.A, e.B))
          money+=e.cost
          cntConnect++;

combine()
    a = findSet(a)
    b = findSet(b)
    if (a==b) return false;
    if (level[a] > level[b]) arrConnected[a] = b;
    else arrConnected[b] = a;
    return true;

findSet()
    if (arrConnected[a]!=a) arrConnected[a] = findSet(arrConnected[a]));
    return arrConnected[a];

```

---------------------------------------------

## swea3289서로소집합

### 문제 핵심
- 크루스칼 알고리즘 이용

### 내 접근 & 의사코드

```
arr= new int[N+1]; // n은 1부터 시작
rank = new int[N+1];
for (int i = 1; i<=N; i++) arr[i] = i; // 자기 자신 가리키도록 초기화
나머지는 위에와 같음

```

---------------------------------------------

## swea1251 swea7465창용마을무리의개수

### 문제 핵심
- 무리를 계산할 때, arr[i]를 set에 집어 넣으면서 확인

### 내 접근 & 의사코드

```
HashSet<Integer> set = new HashSet<>();
for (int i = 1; i<=N; i++)
    if (!set.contains(arr[i])) set.add(arr[i]) answer++;

```

---------------------------------------------
