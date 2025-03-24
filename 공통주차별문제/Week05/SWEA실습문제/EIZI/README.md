
# SWEA APS 응용 DAY5

## swea5521 - 상원이의 생일파티 (BFS)

### 문제 핵심
- 상원이(1번)의 생일 파티에 **직접 친구**와 **친구의 친구**까지 초대
- 상원이는 자기 자신 초대 X
- **BFS로 depth 2까지 탐색해야 한다**

### 내 접근 & 의사코드
```
BFS(1번 상원이) 시작
depth 0 -> 상원
depth 1 -> 친구들
depth 2 -> 친구의 친구
depth > 2 -> 탐색 종료
```

### 내가 놓친 포인트
- `friends.contains(prev)`로 depth 관리하려 했지만 depth 관리 안 됨
- BFS에서 depth가 핵심인데 체크를 제대로 못함
- ✅ **Queue에 depth 정보를 같이 넣어 정확히 2단계까지만 탐색해야 한다**

---

## swea15849 - 이어진 쌍 (BFS로 연결 요소 판별)

### 문제 핵심
- 그래프에서 BFS로 연결 요소(component) 번호를 부여
- 쿼리마다 `connected[C] == connected[D]`로 같은 연결 요소 여부 판정

### 내 접근 & 의사코드
```
for i = 1 to N:
    BFS 돌리면서 연결된 노드들에 같은 컴포넌트 번호 부여
for each query:
    connected[C] == connected[D] 이면 이어진 쌍
```

### 내가 놓친 포인트
- `connected[prev] = count;` 위치가 잘못되어 BFS 도중 중복 덮어쓰는 실수
- `adjArr[start].isEmpty()`로 고립 노드를 스킵해버림 (고립 노드도 처리해야 함)
- ✅ 고쳐야 할 핵심:
  - `queue.add(next)`할 때 `connected[next] = count;` 처리
  - 고립 노드도 컴포넌트 부여

---

## swea14510 - 나무 높이 (수학적 그리디)

### 문제 핵심
- 나무들의 최대 높이를 기준으로 맞춰야 함
- `+2`, `+1`로 줄 수 있는 물을 계산하고 최적화
- `two >= one + 2`일 때 `two`를 줄이고 `one`을 늘리는 전략 필요

### 내 접근 & 의사코드
```
max = 가장 큰 나무 높이
for each tree:
    diff = max - tree
    two += diff / 2
    one += diff % 2

while two >= one + 2:
    two -= 1
    one += 2

if one > two:
    answer = one * 2 - 1
else:
    answer = two * 2
```

### 내가 놓친 포인트
- 완전탐색 접근했다가 시간 초과 날 수 있음
- 수학적 접근으로 `two`와 `one` 밸런스 조절이 핵심
- ✅ while문 최적화가 핵심 포인트

---

## 총 정리 포인트
| 문제명 | 핵심 로직 | 내 실수 포인트 | 교정 포인트 |
|-------|-----------|---------------|------------|
| **상원이의 생일파티** | BFS 2단계 탐색 | depth 체크를 `contains()`로 함 | Queue에 depth 관리 필요 |
| **이어진 쌍** | BFS로 연결 요소 탐색 | visited 덮어쓰기, 고립 노드 누락 | queue에 넣을 때만 마킹, 고립 처리 |
| **나무 높이** | 수학적 계산 & 최적화 | 완탐 접근 | two-one 밸런스 조절로 최적화 |

---

✅ 위 구조로 정리하면, 각 문제 핵심 + 접근 + 실수 + 교정까지 한눈에 보기 좋음!

