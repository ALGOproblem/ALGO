# Week06 공통 문제
## 문제 1 : **백준 [17615 - 볼 모으기](https://www.acmicpc.net/problem/17615)**
- **설명** : 규칙에 따라 볼을 이동하여 같은 색끼리 모으되 최소 이동횟수를 찾는 프로그램을 작성

처음 풀때 한방향인줄 알고 15점맞음
봤더니 두 방햠임.
일단 한쪽방향 min값을 구함
여기서 내가 옮길 구슬의 색깔과 바뀌는 시점을 시작으로 이후 옮길 구슬의 색깔과 같으면 count++

각 방향별로 구슬 색갈별로 구하면 총 네번 구하면됨.


## 문제 2 : 백준 [15486-퇴사 2](https://www.acmicpc.net/problem/15486)
- **설명** : N일 동안 상담을 적절히 선택해 최대 수익을 얻는 'DP(동적 계획법)

동적계획법으로 하면 쉽게 풀림
대신에 N+1항을 추가해야함

마지막날에 만약에 상담 소요시간이 하루면 
그날 일당도 포함해야하지만 제 풀이대로하면 N+1항으로 넘어감 
그리고 마지막 계산시에 N+1항과 N항을 한번 더 비교해야함
N+1항이 N-1항 이전의 값때문에 잘못된 값을 포함할 수 있음.


## 문제 3 : [백준 1463번 : 1로 만들기](https://www.acmicpc.net/problem/1463)
- **설명** : 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력

BFS와 DP가 있음
BFS가 더 효율적이지만
DP를 연습해봄 

특히 %6을 포함해야 답이 나옴 아니면 40%쯤에 틀림 나옴
Integer을 토대로 메모제이션해서 %6,%3,%2,x 4개로 나뉘어서 뒤에 +1붙이면 됨.


## 문제 4 : [백준 1916 최소비용 구하기](https://www.acmicpc.net/problem/1916)
- **설명**: A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 구하라.

기본 다익스트라 문제 

   if(dist[now[0]]<now[1]) {
			   continue;
	}
    이게 핵심임.
    기존에 포함된 값중에 오히려 뒤쳐진 값이 존재 할 수 있음. (업데이트하면서 뒤쳐짐)
    이 식을 안 넣으면 시간초과나옴옴
=======
