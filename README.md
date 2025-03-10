# ALGO

## 스터디 진행 방식

### 공통 문제
week 1 : DFS 문제 | 홀수만 풀기
week 2 : SWEA 나무 물주기 & 다익스트라 백준 1446 지름길 & 다익스트라 백준 14284 간선 이어가기 2 & 백준 2665 미로만들기
week 3 : DFS 문제 20057 마법사 상어와 토네이도 & BFS 문제 2178 미로 탐색 & 다익스트라 백준 4485 녹색 옷 입은 애가 젤다지?   




### 패키지 구조
```
ALGO
├─ README.md
├─ Common problem
└─ Theme of problem
   ├─ README.md
   ├─ EIZI💛
   │  ├─ baekjun${Question Number}.java
   │  ├─ baekjun${Question Number}.java
   │  └─ README.md
   └─ MIRIM🐬
      ├─ baekjun${Question Number}.java
      ├─ baekjun${Question Number}.java
      └─ README.md
```
### **README.md**
#### 1. 레포지토리
- `ALGO` 의 진행방식 소개
  
#### 2. 문제 테마별 패키지
- 해당 테마의 포함된 문제 소개
- 참여자들의 참여 현황 공유
  
| 이름 | 완료  | 고전 혹은 체크|
|------|------|----------------|
| 김미림 | 🐬 |  🔨  |
| 이지민 | 💛 |  🤬  |


#### 3. 참여자별 패키지
- 테마 문제에 대한 메모
- 개념, 고전했던 점, 주의할 점 등을 기록
<br>

### 공통 과제
#### 1. 공통과제 선정
#### 2. 문제 업로드
#### 3. `pull request`를 통해 답안 제출
#### 4. 코드 리뷰

<br>

### Git commit message


---
### 필수 학습 개념들

- 알고리즘의 시간 복잡도(Time Complexity) 및 공간 복잡도(Space Complexity) 개념 및 점근적 표기법(Asymptotic Notation)
- 컴퓨터 기초 - Bit연산과 정수 및 소수 표현(Bit manipulation)
- 프로그래밍 언어 기초 - 재귀함수(Recursion function)
- 자료구조  스택(Stack)
- 자료구조 - 큐(Queue)
- 자료구조 - 트리(Tree)
- 자료구조 - 그래프(Graph)
- 알고리즘 - 그래프 탐색 알고리즘(DFS:Depth First Search)
- 알고리즘 - 그래프 탐색 알고리즘(BFS:Breadth First Search)
- 알고리즘 - 완전 탐색(Brute-force / Exhaustive Search)
  
<br>

### 추가적으로 학습하면 좋은 개념들
> 아래 나오는 항목들은 필수적인 항목들은 아니지만, 잘 알고 있으면 A형을 안정적으로 취득할 수 있도록 도와주는 개념
<br>

- 알고리즘 - 백트래킹(Backtracking)
- 구현 팁 - Run-Length 인코딩
- 구현 팁 - 비트마스킹(Bitmasking)
- 알고리즘 - 다익스트라 최단경로 알고리즘(Dijkstra shortest path algorithm)
- 알고리즘 - 다이나믹 프로그래밍 기초(Dynamic programming)
- 개념, 팁 - 상태 모델링(State Modeling)
- 라이브러리 - C++ STL(Standard Template Library) 중 vector, queue, sort, priority_queue 등
- 자료구조 - 우선순위 큐
- 알고리즘 - 이진 탐색(Binary search)
<br>

---
### DFS 문제 | 홀수만 풀기


| 레벨 | 번호  | 문제 이름           | 백준 링크 | 날짜 | mirim | eizi |
|------|------|-----------------|------------|------|------|------|
| 1    | 2667 | 단지번호붙이기    | [https://www.acmicpc.net/problem/2667](https://www.acmicpc.net/problem/2667) | 02/25 | 🐬 | 💛 |
| 1    | 2468 | 안전 영역        | [https://www.acmicpc.net/problem/2468](https://www.acmicpc.net/problem/2468) |  |  | 💛 |
| 1    | 2583 | 영역 구하기      | [https://www.acmicpc.net/problem/2583](https://www.acmicpc.net/problem/2583) | 02/25 | 🐬 | 🤬 |
| 1    | 1325 | 효율적인 해킹    | [https://www.acmicpc.net/problem/1325](https://www.acmicpc.net/problem/1325) |  |  |  |
| 1    | 1926 | 그림            | [https://www.acmicpc.net/problem/1926](https://www.acmicpc.net/problem/1926) | 02/25 | 🐬 | 💛 |
| 1    | 1743 | 음식물 피하기    | [https://www.acmicpc.net/problem/1743](https://www.acmicpc.net/problem/1743) |  |  |  |
| 1    | 3184 | 양              | [https://www.acmicpc.net/problem/3184](https://www.acmicpc.net/problem/3184) | 02/25 | 🐬 | 💛 |
| 1    | 1303 | 전쟁 - 전투      | [https://www.acmicpc.net/problem/1303](https://www.acmicpc.net/problem/1303) |  |  |  |
| 1    | 14716 | 현수막         | [https://www.acmicpc.net/problem/14716](https://www.acmicpc.net/problem/14716) | 02/25 |  | 💛 |
| 1    | 3187 | 양치기 꿍       | [https://www.acmicpc.net/problem/3187](https://www.acmicpc.net/problem/3187) |  |  | 💛 |
| 1    | 1939 | 중량제한      | [https://www.acmicpc.net/problem/1939](https://www.acmicpc.net/problem/1939) | 02/25 | 🔨 | 🤬 |
| 1    | 1189 | 컴백홈         | [https://www.acmicpc.net/problem/1189](https://www.acmicpc.net/problem/1189) |  |  |  |
| 2    | 11724 | 연결 요소의 개수 | [https://www.acmicpc.net/problem/11724](https://www.acmicpc.net/problem/11724) | 02/26 |  | 💛 |
| 2    | 4963  | 섬의 개수       | [https://www.acmicpc.net/problem/4963](https://www.acmicpc.net/problem/4963) |  |  |  |
| 2    | 11725 | 트리의 부모 찾기 | [https://www.acmicpc.net/problem/11725](https://www.acmicpc.net/problem/11725) | 02/26 |  | 🤬 |
| 2    | 2644  | 촌수계산       | [https://www.acmicpc.net/problem/2644](https://www.acmicpc.net/problem/2644) |  |  |  |
| 2    | 2210  | 숫자판 점프     | [https://www.acmicpc.net/problem/2210](https://www.acmicpc.net/problem/2210) | 02/26 |  | 🤬 |
| 2    | 24479 | 알고리즘 수업 - 깊이 우선 탐색 1 | [https://www.acmicpc.net/problem/24479](https://www.acmicpc.net/problem/24479) |  |  |  |
| 2    | 24480 | 알고리즘 수업 - 깊이 우선 탐색 2 | [https://www.acmicpc.net/problem/24480](https://www.acmicpc.net/problem/24480) | 02/26 |  | 💛 |
| 2    | 13565 | 침투           | [https://www.acmicpc.net/problem/13565](https://www.acmicpc.net/problem/13565) |  |  |  |
| 3    | 1260  | DFS와 BFS      | [https://www.acmicpc.net/problem/1260](https://www.acmicpc.net/problem/1260) | 02/26 | 🔨 | 💛 |
| 3    | 2606  | 바이러스       | [https://www.acmicpc.net/problem/2606](https://www.acmicpc.net/problem/2606) |  |  🐬 |  |
| 3    | 1012  | 유기농 배추     | [https://www.acmicpc.net/problem/1012](https://www.acmicpc.net/problem/1012) |02/27  |  | 💛 |
| 4    | 1388  | 바닥 장식      | [https://www.acmicpc.net/problem/1388](https://www.acmicpc.net/problem/1388) |  |  |  |
| 4    | 16173 | 점프왕 쩰리 (Small) | [https://www.acmicpc.net/problem/16173](https://www.acmicpc.net/problem/16173) | 02/27 |  |  |

---






### 백준 A형 대비 문제 (난이도 오름차순)

| Level | 번호  | 제목                   | 링크 | mirim | eizi |
|--------|------|----------------------|------------------------------------------------|---|---|
| 1 | 20057 | 마법사 상어와 토네이도 | [https://www.acmicpc.net/problem/20057](https://www.acmicpc.net/problem/20057) | | |
| 1 | 19236 | 청소년 상어 | [https://www.acmicpc.net/problem/19236](https://www.acmicpc.net/problem/19236) | | |
| 1 | 23288 | 주사위 굴리기 2 | [https://www.acmicpc.net/problem/23288](https://www.acmicpc.net/problem/23288) | | |
| 1 | 20055 | 컨베이어 벨트 위의 로봇 | [https://www.acmicpc.net/problem/20055](https://www.acmicpc.net/problem/20055) | | |
| 1 | 14890 | 경사로 | [https://www.acmicpc.net/problem/14890](https://www.acmicpc.net/problem/14890) | | |
| 1 | 15685 | 드래곤 커브 | [https://www.acmicpc.net/problem/15685](https://www.acmicpc.net/problem/15685) | | |
| 1 | 17144 | 미세먼지 안녕! | [https://www.acmicpc.net/problem/17144](https://www.acmicpc.net/problem/17144) | | |
| 1 | 14891 | 톱니바퀴 | [https://www.acmicpc.net/problem/14891](https://www.acmicpc.net/problem/14891) | | |
| 1 | 21610 | 마법사 상어와 비바라기 | [https://www.acmicpc.net/problem/21610](https://www.acmicpc.net/problem/21610) | | |
| 1 | 17779 | 게리맨더링 2 | [https://www.acmicpc.net/problem/17779](https://www.acmicpc.net/problem/17779) | | |
| 2 | 23290 | 마법사 상어와 복제 | [https://www.acmicpc.net/problem/23290](https://www.acmicpc.net/problem/23290) | | |
| 2 | 17142 | 연구소 3 | [https://www.acmicpc.net/problem/17142](https://www.acmicpc.net/problem/17142) | | |
| 2 | 16235 | 나무 재테크 | [https://www.acmicpc.net/problem/16235](https://www.acmicpc.net/problem/16235) | | |
| 2 | 21609 | 상어 중학교 | [https://www.acmicpc.net/problem/21609](https://www.acmicpc.net/problem/21609) | | |
| 2 | 14500 | 테트로미노 | [https://www.acmicpc.net/problem/14500](https://www.acmicpc.net/problem/14500) | | |
| 2 | 14888 | 연산자 끼워넣기 | [https://www.acmicpc.net/problem/14888](https://www.acmicpc.net/problem/14888) | | |
| 2 | 15686 | 치킨 배달 | [https://www.acmicpc.net/problem/15686](https://www.acmicpc.net/problem/15686) | | |
| 2 | 14503 | 로봇 청소기 | [https://www.acmicpc.net/problem/14503](https://www.acmicpc.net/problem/14503) | | |
| 2 | 20058 | 마법사 상어와 파이어스톰 | [https://www.acmicpc.net/problem/20058](https://www.acmicpc.net/problem/20058) | | |
| 2 | 17837 | 새로운 게임 2 | [https://www.acmicpc.net/problem/17837](https://www.acmicpc.net/problem/17837) | | |
| 3 | 21611 | 마법사 상어와 블리자드 | [https://www.acmicpc.net/problem/21611](https://www.acmicpc.net/problem/21611) | | |
| 3 | 13460 | 구슬 탈출 2 | [https://www.acmicpc.net/problem/13460](https://www.acmicpc.net/problem/13460) | | |
| 3 | 12100 | 2048 (Easy) | [https://www.acmicpc.net/problem/12100](https://www.acmicpc.net/problem/12100) | | |
| 3 | 15684 | 사다리 조작 | [https://www.acmicpc.net/problem/15684](https://www.acmicpc.net/problem/15684) | | |
| 3 | 19238 | 스타트 택시 | [https://www.acmicpc.net/problem/19238](https://www.acmicpc.net/problem/19238) | | |



### BFS 연습 문제 목록

| 난이도 | 번호  | 제목                   | 링크 |  |  |
|--------|------|----------------------|------------------------------------------------|---|---|
| 1 | 2178 | 미로 탐색 | [https://www.acmicpc.net/problem/2178](https://www.acmicpc.net/problem/2178) | | |
| 1 | 1012 | 유기농 배추 | [https://www.acmicpc.net/problem/1012](https://www.acmicpc.net/problem/1012) | | |
| 1 | 1260 | DFS와 BFS | [https://www.acmicpc.net/problem/1260](https://www.acmicpc.net/problem/1260) | | |
| 1 | 1926 | 그림 | [https://www.acmicpc.net/problem/1926](https://www.acmicpc.net/problem/1926) | | |
| 1 | 2606 | 바이러스 | [https://www.acmicpc.net/problem/2606](https://www.acmicpc.net/problem/2606) | | |
| 2 | 7576 | 토마토 | [https://www.acmicpc.net/problem/7576](https://www.acmicpc.net/problem/7576) | | |
| 2 | 7569 | 토마토(3차원) | [https://www.acmicpc.net/problem/7569](https://www.acmicpc.net/problem/7569) | | |
| 2 | 2206 | 벽 부수고 이동하기 | [https://www.acmicpc.net/problem/2206](https://www.acmicpc.net/problem/2206) | | |
| 2 | 4179 | 불! | [https://www.acmicpc.net/problem/4179](https://www.acmicpc.net/problem/4179) | | |
| 2 | 1697 | 숨바꼭질 | [https://www.acmicpc.net/problem/1697](https://www.acmicpc.net/problem/1697) | | |
| 3 | 12851 | 숨바꼭질 2 | [https://www.acmicpc.net/problem/12851](https://www.acmicpc.net/problem/12851) | | |
| 3 | 13549 | 숨바꼭질 3 | [https://www.acmicpc.net/problem/13549](https://www.acmicpc.net/problem/13549) | | |
| 3 | 13913 | 숨바꼭질 4 | [https://www.acmicpc.net/problem/13913](https://www.acmicpc.net/problem/13913) | | |
| 3 | 2206 | 벽 부수고 이동하기 | [https://www.acmicpc.net/problem/2206](https://www.acmicpc.net/problem/2206) | | |
| 3 | 2146 | 다리 만들기 | [https://www.acmicpc.net/problem/2146](https://www.acmicpc.net/problem/2146) | | |


### 다익스트라 문제 목록

| 난이도 | 번호  | 제목                           | 링크                                               |   |   |
|--------|------|------------------------------|--------------------------------------------------|---|---|
| 1      | 1446 | 지름길                        | [https://www.acmicpc.net/problem/1446](https://www.acmicpc.net/problem/1446) |   | 🤬  |
| 1      | 5972 | 택배 배송                     | [https://www.acmicpc.net/problem/5972](https://www.acmicpc.net/problem/5972) |   |   |
| 1      | 14284| 간선 이어가기 2               | [https://www.acmicpc.net/problem/14284](https://www.acmicpc.net/problem/14284) |   | 🤬  |
| 1      | 1719 | 택배                          | [https://www.acmicpc.net/problem/1719](https://www.acmicpc.net/problem/1719) |   |   |
| 1      | 2665 | 미로만들기                    | [https://www.acmicpc.net/problem/2665](https://www.acmicpc.net/problem/2665) |   |   |
| 1      | 4485 | 녹색 옷 입은 애가 젤다지?     | [https://www.acmicpc.net/problem/4485](https://www.acmicpc.net/problem/4485) |   |   |
| 1      | 13424| 비밀 모임                     | [https://www.acmicpc.net/problem/13424](https://www.acmicpc.net/problem/13424) |   |   |
| 1      | 14938| 서강그라운드                  | [https://www.acmicpc.net/problem/14938](https://www.acmicpc.net/problem/14938) |   |   |
| 1      | 18223| 민준이와 마산 그리고 건우     | [https://www.acmicpc.net/problem/18223](https://www.acmicpc.net/problem/18223) |   |   |
| 1      | 1238 | 파티                          | [https://www.acmicpc.net/problem/1238](https://www.acmicpc.net/problem/1238) |   |   |
| 1      | 2211 | 네트워크 복구                 | [https://www.acmicpc.net/problem/2211](https://www.acmicpc.net/problem/2211) |   |   |
| 2      | 14496| 그대, 그머가 되어             | [https://www.acmicpc.net/problem/14496](https://www.acmicpc.net/problem/14496) |   |   |
| 2      | 1916 | 최소비용 구하기               | [https://www.acmicpc.net/problem/1916](https://www.acmicpc.net/problem/1916) |   |   |
| 2      | 1261 | 알고스팟                      | [https://www.acmicpc.net/problem/1261](https://www.acmicpc.net/problem/1261) |   |   |
| 2      | 1504 | 특정한 최단 경로              | [https://www.acmicpc.net/problem/1504](https://www.acmicpc.net/problem/1504) |   |   |
| 2      | 10282| 해킹                          | [https://www.acmicpc.net/problem/10282](https://www.acmicpc.net/problem/10282) |   |   |
| 2      | 11779| 최소비용 구하기 2             | [https://www.acmicpc.net/problem/11779](https://www.acmicpc.net/problem/11779) |   |   |
| 2      | 1445 | 일요일 아침의 데이트          | [https://www.acmicpc.net/problem/1445](https://www.acmicpc.net/problem/1445) |   |   |
| 2      | 10473| 인간 대포                     | [https://www.acmicpc.net/problem/10473](https://www.acmicpc.net/problem/10473) |   |   |
| 2      | 14461| 소가 길을 건너간 이유 7       | [https://www.acmicpc.net/problem/14461](https://www.acmicpc.net/problem/14461) |   |   |
| 3      | 18352| 특정 거리의 도시 찾기         | [https://www.acmicpc.net/problem/18352](https://www.acmicpc.net/problem/18352) |   |   |
| 3      | 1753 | 최단경로                      | [https://www.acmicpc.net/problem/1753](https://www.acmicpc.net/problem/1753) |   |   |
| 3      | 13549| 숨바꼭질 3                    | [https://www.acmicpc.net/problem/13549](https://www.acmicpc.net/problem/13549) |   |   |
| 3      | 17396| 백도어                        | [https://www.acmicpc.net/problem/17396](https://www.acmicpc.net/problem/17396) |   |   |
| 3      | 20168| 골목 대장 호석 - 기능성       | [https://www.acmicpc.net/problem/20168](https://www.acmicpc.net/problem/20168) |   |   |
| 3      | 20007| 떡 돌리기                     | [https://www.acmicpc.net/problem/20007](https://www.acmicpc.net/problem/20007) |   |   |
| 3      | 13911| 집 구하기                     | [https://www.acmicpc.net/problem/13911](https://www.acmicpc.net/problem/13911) |   |   |
| 3      | 20666| 인물이와 정수                 | [https://www.acmicpc.net/problem/20666](https://www.acmicpc.net/problem/20666) |   |   |
| 3      | 9370 | 미확인 도착지                 | [https://www.acmicpc.net/problem/9370](https://www.acmicpc.net/problem/9370) |   |   |
| 3      | 14431| 소수마을                      | [https://www.acmicpc.net/problem/14431](https://www.acmicpc.net/problem/14431) |   |   |
| 3      | 16118| 달빛 여우                     | [https://www.acmicpc.net/problem/16118](https://www.acmicpc.net/problem/16118) |   |   |
| 3      | 17940| 지하철                        



### 모의 SW 역량테스트 문제 목록

| 난이도 | 번호  | 제목                   | 링크 |
|--------|------|----------------------|------------------------------------------------|
| 3 | 5648 | 원자 소멸 시뮬레이션 | [https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGEbd6cjMDFAUo](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGEbd6cjMDFAUo) |
| 3 | 5650 | 핀볼 게임 | [https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGsMy6h3kDFAUo](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGsMy6h3kDFAUo) |
| 3 | 17472 | 다리 만들기 2 | [https://www.acmicpc.net/problem/17472](https://www.acmicpc.net/problem/17472) |
| 2 | 17471 | 게리맨더링 | [https://www.acmicpc.net/problem/17471](https://www.acmicpc.net/problem/17471) |
| 2 | 17406 | 배열 돌리기 4 | [https://www.acmicpc.net/problem/17406](https://www.acmicpc.net/problem/17406) |
| 2 | 17136 | 색종이 붙이기 | [https://www.acmicpc.net/problem/17136](https://www.acmicpc.net/problem/17136) |
| 2 | 17135 | 캐슬 디펜스 | [https://www.acmicpc.net/problem/17135](https://www.acmicpc.net/problem/17135) |
| 2 | 17070 | 파이프 옮기기 1 | [https://www.acmicpc.net/problem/17070](https://www.acmicpc.net/problem/17070) |
| 2 | 16637 | 괄호 추가하기 | [https://www.acmicpc.net/problem/16637](https://www.acmicpc.net/problem/16637) |
| 2 | 5653 | 줄기세포배양 | [https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGEbd6cjMDFAUo](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGEbd6cjMDFAUo) |
| 2 | 5656 | 벽돌 깨기 | [https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGsMy6h3kDFAUo](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGsMy6h3kDFAUo) |
| 2 | 5658 | 보물상자 비밀번호 | [https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGEbd6cjMDFAUo](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGEbd6cjMDFAUo) |
| 2 | 5644 | 무선 충전 | [https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGsMy6h3kDFAUo](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGsMy6h3kDFAUo) |
| 2 | 4014 | 활주로 건설 | [https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGEbd6cjMDFAUo](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGEbd6cjMDFAUo) |
| 2 | 4013 | 특이한 자석 | [https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGsMy6h3kDFAUo](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGsMy6h3kDFAUo) |
| 2 | 4012 | 요리사 | [https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGEbd6cjMDFAUo](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGEbd6cjMDFAUo) |
| 2 | 4008 | 숫자 만들기 | [https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGsMy6h3kDFAUo](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGsMy6h3kDFAUo) |
| 1 | 17281 | ⚾ | [https://www.acmicpc.net/problem/17281](https://www.acmicpc.net/problem/17281) |
