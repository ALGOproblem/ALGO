
# SWEA APS 응용 DAY6

## swea2105 디저트카페

### 문제 핵심
- BFS로는 풀지 못하는 문제
- 모든 마름모꼴을 뽑는 조합 계산 및 백트래킹
- 1. 모든 마름모꼴 뽑는 조합 계산 (1~N-2까지) 왼쪽 대각선 및 오른쪽 대각선만 뽑으면 ㅇㅋ
  2. 모든 포인터에서 (0,0 ~ N-1,N-1)에서 해당 마름모꼴 성립(isRectangle?) (isVisted?) (set.contains?)
  3. maxSum = Math.max(maxSum, sum);

### 내 접근 & 의사코드
```
1. 모든 마름모꼴 뽑는 조합
    for (int i = 1; i<N; i++){
        rect[depth] i;
        comb(depth+1);
    }
2. 2개를 다 뽑았을 경우에 적어도 마름모꼴이 그려지는 좌표를 선택
    if (i+ rect[0] + rect[1] < N && i + rect[0] < N && j - rect[1] >= 0) // 하, 우, 왼 확인! (상에서 시작)
3. isRectangle?
    for (int i = 0; i<4; i++) // 4방향을 탐색
        int move = (i%2 == 0) ? rect[0] : rect[1];
        for (int j = 0; j<move; j++) // 해당 방향에서 얼마나 가야 하는지
            if (curx<0 || cury<0 || curx>=N || cury>=N) return -1; // 범위 벗어남
            if (i==3 && j == move -1) // 이 조건 안 넣고 set에 포함하면 return 해버려서 fail
                if (curx != x|| cury != y) return -1
            else
                if (set.contains(cafes[curx][cury]) return -1;
                set.add(cafes[curx][cury])
            

```
---

## swea4008숫자만들기

### 문제 핵심
- operator[4] 연산자의 개수 arrOperator[N-1] 실제 사용될 연산자가 들어가는 배열
- 백트래킹을 이용

### 내 접근 & 의사코드
```
1. backtracking(int depth)
      if (depth == N-1)
          calculate(arrOperator)
          return;
      for (int i = 0; i<4; i++)
            if (operator[i]>0) operator[i]-- arrOperator[depth] = i; // 0(+) 1(-) 2(*) 3(/)
            backTracking(depth+1)
            operator[i]++; // 되돌리기
            
```
---

## swea7733치즈도둑

### 문제 핵심
- 1일부터 100일 동안 계속 숫자를 키우면서 bfs 진행
- 중간에 if (maxAns > findMaxCheese()) break; 했다가 일부 케이스에서 실패 (함부로 종료시키지 말자)
- 직관적으로 하루 지날 때마다 arr에서 -1씩 제외했는데 그럴 필요 x

### 내 접근 & 의사코드
```
1. bfs(int x, int y)
      Queue.add(x, y)
      while(!queue.isEmpty)
          for(int i = 0; i<4; i++)
              if (범위, 방문 여부, 이어진 치즈인지)
            
```
---


## swea8275햄스터

### 문제 핵심
- 시간 널널하게 줌 -> 완전 탐색도 가능가능
- backTracking 이용해서 모든 경우에서 isValid인지 확인
- printBestCandidate() 함수를 이용

### 내 접근 & 의사 코드
```
1. backTracking(int depth)
  if (depth ==N)
        if (isValid()) validCandidates.add(arr.clone()) // clone 안하면 똑같은 것들만 인쇄..
  for (int i = x; i>=0; i--)
        arr[depth] = i;
        backTracking(depth +1);
            
```
---










