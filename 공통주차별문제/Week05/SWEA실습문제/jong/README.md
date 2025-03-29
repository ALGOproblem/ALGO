

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

## 8. [문제 5521_상원이의생일파티](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWWO3kT6F2oDFAV4)
- **설명:**  
  친구관계 양방향으로 LIST 저장하고
  상원이(1) 친구 SET에 저장
  상원이 친구의 친구도 LIST에서 찾아서 SET에 저장
  이러면 ANS.SIZE()가 답이 됨


---

## 9. [문제 14510_나무높이](https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AYFofW8qpXYDFAR4)
- **설명:**  

그리디 문제
혹시 의문점있다면 설명해드리겠습니다.
설명하기 너무 힘들어요... 
---

## 10. [문제 1251_하나로](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD)
- **설명:**  
   모든 노드들이 연결되어있다고 생각하고
   모든 간선을 노드에 저장
   노드에는 FROM/TO/DIST저장
   이중FOR문으로 모든 간선 저장 후에
   오름차순 정렬

   오름차순 순서대로 꺼내서 최종거리에 합한다
   이때, 유니온 파인드를 이용해서 서로 연결되어 있는지 확인하고 
   만약에 연결되어 있는 상태면 합하지 않는다.

---

## 10. [문제 3289_서로소집합](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr)
- **설명:**  
유니온 파인드 문제
여기에 같이 결합했는지 확인하는 CHECK메서드 추가
---

## 11. [문제 7465_창용마을무리의개수](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU)
- **설명:**  
  유니온 파인드
  그냥 위랑 똑같은 UNION이후 FIND(최종 부모로  숫자들 전부 변경)
  SET으로 몇개의 수가 있는지 확인인
---

## 12. [문제 1249_보급로](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD)
- **설명:**  
  
  다익스트라 문제

---

## 13. [문제 1238_Contact](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15B1cKAKwCFAYD)
- **설명:**  
  
  bfs문제?
  대신에 bfs를 끝까지 수행해야하고 
  now[2](depth)를 갱신하면서 가장 큰 수 찾기 (아래 부분분)
     if (now[2] > dist[now[0]][now[1]]) continue; // 이미 더 짧은 경로로 방문된 경우 건너뜀
    if (now[0] == N - 1 && now[1] == N - 1) continue; // 도착 지점 도달 시 continue


---
