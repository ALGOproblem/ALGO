# SWEA APS 응용 DAY7

## swea1868 파핑파핑

### 문제 핵심
- 지뢰찾기의 룰
- 1. 가로, 세로, 대각선, 8방향 중 지뢰가 하나도 없다면 그 칸은 0
  2. 0인 칸을 선택하면 주변 8방향의 모든 안전칸이 자동으로 드러남
  3. 0이 아닌 칸은 따로 하나씩 클릭해야 함

### 내 접근 & 의사코드
- 0인 칸들은 BFS로 묶어 한 번의 클릭응로 처리
- BFS 내에서 0인 칸의 8방향 모든 안전 칸을 visited 처리
- 남은 안전 칸은 개별적으로 클릭

```
static chard[][] board; // 보드
static int[][] arr; // 각 칸의 주변 지뢰 개수를 저장
static boolean[][] visited;// 방문한 칸 체크 (지뢰와, 0인 칸과 0의 주변 8방향은 전부 visited 처리)
static int clickCount; // 최초 클릭 함수

bfs()
    if (!visited[nx][ny] && arr[nx][ny] !=-1)
        visited[nx][ny] = true;
        if(arr[nx][ny]==0) queue.add(new int[]{nx, ny}); //논리는 쉬운데 코드에서 위치 중요함!

bfs()할 때 마다 clickCount++
남은 안전칸 (지뢰 아니고, visited처리 되지 않은 칸) for문 돌리면서 clickCount++

(괜히 빨리 하려고 이것저것 하다가 fail 그냥 정석대로 가자)

```

---------------------------------------------
