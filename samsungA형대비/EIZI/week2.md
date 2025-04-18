# 📝 백준 알고리즘 문제 week2 풀이 정리

## 🌳 문제 14510 - 나무 높이
### 📌 해결 방법
1. **x일 만큼의 날짜를 설정**하고, **1의 날과 2의 날을 계산**한다.
2. 최대한 **2일을 먼저 사용하고**, 그 다음에 **1일을 사용**하는 방식으로 마무리한다.

---

## 🚀 문제 1446 - 지름길
### ❌ 처음에 잘못 생각한 부분
- **배열 방식**으로 문제를 풀고 싶어서 `arr[i] = i` 로 초기화 후, 
  지름길 입력을 받아서 **1~3까지 거리가 2라면 3부터 이후 D까지의 모든 거리를 업데이트**하는 방식 사용.
- 하지만 **지름길이 거리 순서대로 정렬되어 있지 않다면, 정확히 반영되지 않는 문제 발생.**

### ✅ 다시 생각한 해결 방법
1. **지름길 정보를 `shortcuts[]` 배열에 저장**
2. **0부터 시작해 모든 지점에서 지름길이 있는지 확인 후 업데이트**
   - 한 칸씩 이동하면서 모든 경우의 수를 고려
   ```java
   for (int i = 0; i <= D; i++) {
       if (i > 0) {
           arr[i] = Math.min(arr[i], arr[i - 1] + 1);
       }
       for (int[] shortcut : shortcuts) {
           int start = shortcut[0];
           int end = shortcut[1];
           int distance = shortcut[2];
           
           if (start == i && end <= D) {
               arr[end] = Math.min(arr[end], arr[start] + distance);
           }
       }
   }
   ```
3. **이 방식으로 한 칸씩 이동하면서, 최적의 경로를 지속적으로 업데이트하여 정확한 최단 거리 계산 가능!**



#### ✨ 주의할 점
- **배열을 사용한 접근법**에서 **지름길이 정렬되지 않으면 오류가 발생할 수 있음**을 확인.
- **한 칸씩 이동하면서 최적 경로를 갱신하는 방식**이 정확한 해결 방법임.
- **배열을 `Integer.MAX_VALUE` 로 초기화 후, 매 순간 최적의 값을 유지하는 방식이 중요!**



-------
## 문제 14284 📝 간선 이어가기 2

### 풀이 방식을 arr로 하려다가 너무 복잡해서 GPT의 도움을 받음

1. **입력 처리**  
   - `n`개의 정점과 `m`개의 간선을 입력받아 그래프를 **인접 리스트**로 저장한다.
2. **다익스트라 알고리즘 적용**  
   - 우선순위 큐(`PriorityQueue`)를 사용해 **가중치가 작은 간선부터 탐색**하며 최단 경로를 찾는다.
   - `s`에서 출발하여 `t`까지의 최소 비용을 계산한다.
3. **탐색 과정**  
   - 가장 작은 가중치를 가진 노드를 먼저 방문.
   - **더 짧은 거리 발견 시 갱신**하고 다시 큐에 추가.
   - `t`에 도달하는 순간 최소 가중치 합을 출력.
  



--------------------

## 📌 미로 만들기 (백준 2665)**

---

### **📖 문제 설명**
- `N x N` 크기의 바둑판 모양의 **미로**가 주어진다.
- 검은 타일(`0`)과 흰 타일(`1`)로 이루어져 있으며,  
  **(0,0)에서 (N-1,N-1)까지 이동할 수 있도록 최소한의 검은 타일을 흰 타일로 변경해야 한다.**
- **최소한의 검은 타일 변경 횟수**를 구하는 것이 목표다.

---

#### **🛠️ 해결 방법**
##### **1️⃣ 잘못된 접근 방식**
❌ **초기 코드에서 틀린 부분**  
```java
int distance = (목적지 X - 현재 X) + (목적지 Y - 현재 Y);
```
- → 이 방식은 **실제 최소 변경 횟수**가 아니라 **"목적지까지의 예상 거리"** 를 기준으로 탐색했음.
- → 결과적으로 **최적 경로를 보장하지 않음.**

❌ **BFS & 다익스트라 혼용 문제**  
- BFS 탐색 후, 검은 방(0)이 나오면 `pq.offer()`로 추가했으나  
  **우선순위 큐(`pq.poll()`)가 탐색 순서를 흐트러뜨릴 가능성이 있음.**
- **BFS와 다익스트라를 구분해서 사용해야 함.**

---

##### **2️⃣ 다익스트라 알고리즘 적용 (O(E log V))**
✅ **우선순위 큐(`PriorityQueue`)를 활용하여 검은 방(0)을 최소한으로 변경하는 경로 탐색**
- 가중치(검은 방을 바꾼 횟수)가 작은 노드부터 방문.
- `distance[][]` 배열을 사용하여 **각 위치까지의 최소 변경 횟수 저장.**

```java
PriorityQueue<Node> pq = new PriorityQueue<>();
pq.offer(new Node(0, 0, 0)); // 시작점 (0,0)
distance[0][0] = 0;
```
- **우선순위 큐에서 가중치가 가장 작은 노드를 먼저 탐색.**
- 검은 방(`0`)을 만나면 **비용 `+1` 추가.**
- `distance[][]`를 활용해 **더 적은 변경 횟수로 방문할 경우만 갱신.**

**💡 시간 복잡도:** `O(E log V)`

---

##### **3️⃣ BFS + 다익스트라 혼용 (0-1 BFS, O(E))**
✅ **우선순위 큐 없이 `Deque`(덱)를 활용하여 더 빠르게 탐색 가능!**
- **흰 방(1)** → **우선 방문** (`addFirst()`)
- **검은 방(0)** → **나중 방문** (`addLast()`)

```java
Deque<Node> deque = new LinkedList<>();
deque.addFirst(new Node(0, 0, 0)); // 시작점
```
- **흰 방(1)을 먼저 탐색하여 빠르게 이동.**
- **검은 방(0)은 뒤에 탐색하여 변경 횟수를 최소화.**

**💡 시간 복잡도:** `O(E)`, 다익스트라보다 빠름!



## **🚀 정리**
| 알고리즘 | 설명 | 시간 복잡도 |
|---------|---------|---------|
| **잘못된 초기 접근** | 목적지까지의 예상 거리 사용 → 최적 경로 보장 X | ❌ |
| **다익스트라** | 우선순위 큐(`PriorityQueue`) 사용, 최소 변경 횟수 갱신 | **O(E log V)** |
| **0-1 BFS** | `Deque`(덱) 사용, 흰 방(1) 먼저 방문 → 더 빠름! | **O(E)** |




















