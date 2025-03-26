# Week04 공통 문제

## 1. [문제 16438 - 원숭이 스포츠](https://www.acmicpc.net/problem/16438)
### (1) 문제 접근 방식
#### 분할 정복
k일차에 대하여 N/2마리까리 같은 팀을 하게 한다.<br>
같은 팀을 한 원숭이들을 또다시 절반으로 나누어 다른 팀을 하게 한다.

#### N < 7
이러한 상황에서는 분할정복의 경우의 수보다 7일이라는 시간이 더 길게 된다.<br>
그렇기에 최초 팀인 'AAAA...AA'와 같은 팀 배정이 나왔다면, 끝자리만 B로 바꾸어준다. 

### (2) 분할정복
| 문제를 작게 나누어, 각 부분을 재귀적으로 해결, 결과를 최종 합쳐서 해결한다.

#### 발상
| 문제를 반으로 나눌 수 있을까? 작은 문제로 쪼개면 전체를 구할 수 있지 않을까?

#### 활용도 높은 문제
- 문제를 쪼갤 수 있을 때
    - 배열, 수열, 트리 같이 절반으로 나눌 수 있을 때
- 재귀적으로 같은 작업 반복
    - 정렬, 탐색, 누적 계산
- 부분 문제의 정답들의 합 = 전체 정답
    - 병합 정렬

#### 예시
- 병합 정렬
- 퀵 정렬
- 이진 탐색
- 행렬 곱, 지수 계산
- 트리 순회, 재귀적 DFS

### (3) 풀이 : 분할 정복, 재귀
``` java
static void binaryTeam(int start, int end, int day) {

    if (day == 7) return;

    int mid = (start + end) / 2;
		
	for (int i = start; i <= mid; i++)
        monkeys[day][i] = 'A';
		
		for (int i = mid + 1; i <= end; i++)
			monkeys[day][i] = 'B';
		
		binaryTeam(start, mid, day + 1);
		
		binaryTeam(mid + 1, end, day + 1);
	}
```

---

## 2. [문제 17136 - 색종이 붙이기](https://www.acmicpc.net/problem/17136)

---

## 3. [문제 16946 - 벽 부수고 이동하기 4](https://www.acmicpc.net/problem/16946)

---

## 4. [문제 9081 - 단어 맞추기](https://www.acmicpc.net/problem/9081)

---
