

---
코드는 제가 작성했고 gpt돌려서 주석 달았습니다!
# Week4 공통 문제
---

## 1. [문제 1868_파핑파핑지뢰찾기기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LwsHaD1MDFAXc)
- **설명:**
  
1. 주변에 폭탄이 없는 '.'을 찾는다.
2. bfs로 들어간다. ---bfs는 주변에 폭탄 있는 '.'에서 멈춘다  (주변에 폭탄이 있으면서  폭탄이 없는 '.'이 주변에 있는 '.')
3. 이때, bfs는 for문을통해 2차원 배열 모두 돈다. 
4. 이러면 주변에 폭탄 있는 '.'는 check가 안되어있어서 이를 전부 세서 답에 보태면 됩니다. (주변에 폭탄이 있으면서 폭탄이 없는 '.'이가 없는 '.')

---

## 2. [문제 1952 - 수영장](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq)
- **설명:**  
  재귀문제
  그냥 모든 경우수를 재귀로 돌리면됨.
  1개월권과 3개월권 시작은 달에 최소한 하루 이상의 운동일이 있을때만 수행
  재귀 돌리고 1년권이랑 비교하고 답 제출하면됨.

---

## 3. [문제 1953-탈주범 검거](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq)
- **설명:**  
  구현문제 
  그냥 모든 경우의수 해서 푸는 방법 밖에는 없음.
  1.  지금 파이프==어디로 갈지 정함(MOVE 함수)
  2.  어디로 갈지 정해지면 그 좌표의 파이프 상태 확인 (INSERT함수 +CHECK 함수수)
  3.  파이프가 갈 수 있는 상태면 QU에 넣어서 BFS 수행 (1-2-3 계속수행행)
  (여기서 항상 시간(L)넘지 않은지 확인해야 함)
  VISIT처리되면 ANS++하고 
  BFS끝나면 출력력
  
---

## 4. [문제 2105_디저트가게](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu)
- **설명:**  
백트레킹 문제
  그냥 방향을 꺾으면서 모든 경우의수 재면 됨(사각형이 되게 미리 INT[]로 정해버림)
  대신에 기존 백트레킹과 다르게 재구 경우가 2개임 
   (기존 방향으로 간다 와 뱡향을 꺾는다.)

  이때, 조건에 안맞으면 RETURN
  안 맞는 조건: 
  1. SET에 같은 수 있다
  2. 방향 꺽는게 사각형을 넘었다 (INT DIRECT>=4) 

---

## 5. [문제 4008_숫자만들기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeRZV6kBUDFAVH)
- **설명:**  
 그냥 단순 백트레킹 문제 

---

## 6. [문제 7733_치즈도둑 ](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWrDOdQqRCUDFARG)
- **설명:**  
 날짜를 KEY로 해서 치즈 좌표를 MAP에 저장 
 각 날짜별로 KEY를 대입해서 치즈 좌표 받아서 -1로변경
 (HashMap<높이, 좌표목록> + BFS + boolean[][] visit
  map을 이용해 그날에만 녹을 좌표만 빠르게 접근)


 BFS수행하면서 각 날짜별로 치즈 구역 구하기 

---

## 7. [문제 8275_햄스터](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWxQ310aOlQDFAWL)
- **설명:**  
백트레킹 문제제
  원래 백트래킹 돌리면 사전순으로 알아서 됨.
  (대신 미리 조건에 없는 케이지의 햄스터수를 최대로 맞춰줘야함)
  그리고 중요한점은 
  햄스터최대수가 0인 경우가 있음(이걸 못챙겨서 46개 테스트케이스중 43개만 맞음)

  제가 푼 2가지 방법이 있음
  1. 아예 PQ등 다양한 방식으로 사전순으로 정리해서 PQ.POLL해서 정답 도출
  2. 햄스터 최대 수를 -1로 초기값을 설정해서 햄스터 최대수가 -1이면 조건에 맞는 수가 없으므로 -1출력 아니면 
  각 케이지별 수 출력(제가 쓴 방식)
---