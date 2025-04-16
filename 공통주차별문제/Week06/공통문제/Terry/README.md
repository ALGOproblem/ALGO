## 1463. 1로 만들기 (실버 3)

#### 풀이 과정

그리디로 풀다가 바로 혼남

```java
public class Boj1520내리막길 {
static BufferedReader input;
static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        input = new BufferedReader(new InputStreamReader(System.in));
        //input = new BufferedReader(new FileReader("input.txt"));

        int X = Integer.parseInt(input.readLine());

        int cnt = 0;

        // 3으로 나눈 나머지가 2일 때 -> 1) 짝수 , 2) 홀수 의 경우가 있다.
        // 짝수 일 때는 2로 나누고
        // 홀수 일 때는 -1 해주기
        while (X != 1) {
            if (X % 3 == 2 && X % 2 == 0) {
                X /= 2;
            } else if (X % 3 == 0) {
                X /= 3;
            } else {
                X -= 1;
            }

            cnt++;
        }
        System.out.println(cnt);
    }
}

```

#### 접근 방법
  - 1을 뺀 경우가 최고의 경우일 수 있다.
  1. 이번 dp 테이블을 이전 값에서 +1 을 한다. ( -1 인 경우 )
  2. `dp[i/2] +1` 을 계산해서 dp[i]와 비교한다. ( /2 인 경우 )
  3. `dp[i/3] +1` 을 계산해서 dp[i]와 비교한다. ( /3 인 경우 )

---

## 17615. 볼모으기 (실버 1)

#### 풀이과정

- N 은 최대 500_000
- N^2로 풀 경우 250_000_000_000 으로 시간 초과
- O(N) 으로 풀자

#### 접근방법

1. R, B를 왼쪽 혹은 오른쪽으로 보낸 횟수를 센다.
    - R을 왼쪽으로 보낼 경우
        1. 배열을 왼쪽부터 탐색하고 B를 찾는다.
        2. B를 찾은 뒤 R을 마주치면 `cnt++` 한다.
    - R을 오른쪽으로 보낼 경우
        1. 배열을 오른쪽부터 탐색하고 B를 찾는다.
        2. B를 찾은 뒤 R을 마주치면 `cnt++` 한다.
2. 가장 적은 횟수를 출력한다.

- 🚨 N^2 로 풀이(시간 초과)
    - R을 오른쪽으로 보낼 때
        1. B를 발견한다. 
        2. 그 이후에 있는 R을 발견하고 스왑한다.
---

## 15486. 퇴사2 (골드 5)

#### 풀이과정
- N + 1 일 째에는 회사에 없다.
- 1 <= N <= 1_500_000

1. 이제까지 최고 금액을 누적해서 dp 저장
2. `상담이 끝나는 날 페이 <  현재 페이 + 받을 페이` 일 경우 dp 갱신

- 이전까지의 금액을 누적 안해서 헤맸음 :(


---

## 1916. 최소비용 구하기 (골드 5)

#### 풀이과정
- 도시 1부터 N까지
- 최소 비용 -> 다익스트라
- priorityQueue 사용
	
1. 정점을 하나 꺼낸다.
2. 인접한 정점을 방문한다.
3. 방문 아니고 거리가 기존 값보다 현재 정점에서 이동이 가까운 경우 거리 갱신