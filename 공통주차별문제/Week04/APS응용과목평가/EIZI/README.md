
# APS 응용과목평가 정리

---

## 1. 과목평가1 – 미술관의 조명

### 문제 설명
- **미술관 구성:**  
  N×N 크기의 미술관에는 벽(1)과 조명(2)이 설치되어 있다.
- **조명의 역할:**  
  조명이 있는 칸에서는 상하좌우로 직선상의 모든 칸을 밝힌다. 단, 벽을 만나면 그 방향으로는 더 이상 빛이 전달되지 않는다.
- **목표:**  
  전체 미술관에서 조명에 의해 밝혀지는 칸의 수를 구한다. (조명 설치된 칸도 포함)

### 알고리즘 요약 (의사코드, 10줄 미만)
```pseudocode
for each cell in gallery:
    if cell has a light:
        for each of 4 directions:
            step = 1
            while within bounds and current cell is not wall:
                mark cell as lit if not already; step++
count = total cells - (walls + unlit cells)
```

### 핵심 개념 및 고려 사항
- **방향 탐색:**  
  각 조명에서 4방향으로 한 칸씩 연속 이동하며, 벽(1)을 만나면 break.
- **중복 처리:**  
  여러 조명이 같은 칸을 밝힐 수 있으므로, 이미 밝힌 칸은 다시 카운트하지 않도록 한다.
- **구현 포인트:**  
  for문과 while문을 적절히 사용하여 각 조명의 영향을 계산.

---

## 2. 과목평가2 – 룩과 나이트

### 문제 설명
- **체스판 상태:**  
  N×M 보드에 룩(1)과 나이트(2)가 주어진 초기 위치에 있다.
- **이동 규칙:**  
  - 룩: 상하좌우 한 방향으로 연속해서 이동 가능 (이동 경로에 나이트가 있으면 이동 불가).  
  - 나이트: 8가지 L자 모양의 단일 점프 이동 (이동 후 해당 칸에 룩이 있으면 이동 불가).
- **목표:**  
  주어진 K번의 이동 후 두 기물이 도달할 수 있는 서로 다른 최종 상태(위치 조합)의 개수를 백트래킹과 메모이제이션으로 구한다.

### 알고리즘 요약 (의사코드, 10줄 미만)
```pseudocode
function simulate(level):
    if level == K: record (rook_x, rook_y, knight_x, knight_y); return
    if memo[level][rook_x][rook_y][knight_x][knight_y] visited: return; mark visited
    for each of 4 rook directions:
        for step = 1 while move valid and cell ≠ knight:
            update rook position; simulate(level+1); backtrack rook
    for each of 8 knight moves:
        if move valid and cell ≠ rook:
            update knight position; simulate(level+1); backtrack knight
```

### 핵심 개념 및 고려 사항
- **백트래킹:**  
  각 재귀 호출 후 전역 변수(룩/나이트 위치)를 백업해두고, 재귀 종료 후 원래 상태로 복원하여 다른 경로에 영향을 주지 않는다.
- **while vs. for:**  
  - 룩은 한 방향으로 여러 칸 이동하므로 while문(조건 불충족 시 break) 사용.  
  - 나이트는 단 한 번의 점프이므로 for문으로 8 방향 각각 검사하며, 조건 불충족 시 continue.
- **메모이제이션:**  
  5차원 배열(memo[level][x1][y1][x2][y2])을 사용하여 동일 상태의 중복 탐색을 방지한다.

---

## 3. 과목평가3 – 건물주 고양이

### 문제 설명
- **문제 구성:**  
  여러 집(고양이집)이 있고, 각 집마다 특정 털 색깔이 떨어져 있다.  
  그리고 각 고양이는 자신이 가진 털 리스트가 있다.
- **목표:**  
  각 집에 해당하는 털 색깔을 가진 고양이에게 집을 배정하는 경우의 수를 구한다.
- **조건:**  
  한 고양이는 한 번만 배정되며, 고양이의 털 리스트에 해당 색이 포함되어야 한다.

### 알고리즘 요약 (의사코드, 10줄 미만)
```pseudocode
function matchCat(idx):
    if idx == number_of_houses: answer++; return
    currentHair = catHairs[idx]
    for each cat i:
        if (cat[i] contains currentHair and not visited):
            mark cat i visited; matchCat(idx+1); unvisit cat i
```

### 핵심 개념 및 고려 사항
- **백트래킹:**  
  재귀적으로 각 집(인덱스 idx)마다 조건에 맞는 고양이를 선택하고, 선택 후에는 방문 상태를 복원하여 다른 경우의 수를 탐색한다.
- **데이터 구조:**  
  각 고양이의 털 목록은 ArrayList로 저장하며, 입력 시 공백 제거에 주의한다.
- **조건 확인:**  
  고양이의 털 리스트에 현재 집의 털 색깔이 포함되어 있는지 확인하고, 중복 배정을 막기 위해 visited 배열을 사용한다.

