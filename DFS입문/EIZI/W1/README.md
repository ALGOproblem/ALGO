


### 2월 4주차 DFS 문제 풀이
####백준1939 중량제한

#####0. 틀린 이유
지금까지 BFS와 DFS를 완벽히 반대로 알고 있었음

#####1. 문제 분석
5*5숫자판, 각 칸에는 0~9 숫자, 임의의 위치에서 시작해서 인접 네 방향 이동, 총 6자리 숫자
중복 방문 가능, 만들 수 있는 서로 다른 여섯 자리 수들의 개수 구하기

#####2. 풀이 방식
a. BFS로는 풀 수 없는 유형. DFS 선택
b. 종료조건 : num.length==5 되면 HashSet에 저장 (set을 이용해 중복 저장 방지)
c. 반복조건 : 유효 좌표 범위를 확인하고 dfs(x, y, String num+arr[현재좌표])

-----------------

####백준2210 숫자판점프
#####0. 틀린 이유
binarySearch 배웠지만 전혀 떠올리지 못함
List<int[]>[] graph의 형식이 생소했음
노드 0 -> [1, 2]
노드 1 -> [0, 2] [2, 3]
노드 2 -> [1, 3]

#####1. 문제 분석
N(2<=N<=10000)개의 섬 이루어진 나라. 두 개의 섬에 공장 세워두고 운영. 다리마다 중량 제한인 C 다리 존재
A와 B 사이에 옮길 수 있는 최대의 중량을 구하라

#####2. 풀이 방식
a. 모든 경우의 수 (1~N)까지 탐색하지 말고 이진탐색(BinarySearch)를 통해 canCrossBFS()를 판단

b. BinarySearch()
    - while(left<=right) // 왼쪽 범위가 오른쪽보다 작거나 같아질 때
    - int mid = (left+right)/2 // while문 안에서 mid가 초기화되어야 함
    - canCrossBfs() -> left = mid+1  or right = mid-1
c. canCrossBfs(int mid)
    - 도착가능한 섬 번호 관련 queue 선언 (모든 방향을 동일한 레벨로 처리하는 탐색에서는 Queue가 적합)
    - 방문한 섬 관리하는 boolean[] visited 선언
    - 출발점(start)를 queue에 넣고 while(!queue.isEmpty()) 실행
    - int cur = queue.poll() == end라면 true 반환
    - for (int[] next : graph[cur]) // 현재 갈 수 있는 모든 섬을 탐색
        if (방문 섬 X, 중량 <= 제한)일 때 queue에 다음 섬 넣고, visited = true 
