const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = Number(input[0]); // 도시 - 정점
const M = Number(input[1]); // 버스 - 간선

const dist = Array(N + 1).fill(Infinity);
const visited = Array(N + 1).fill(false);

const adj = Array.from({ length: N + 1 }, () => []);
for (let i = 2; i < 2 + M; i++) {
  [start, end, cost] = input[i].split(" ").map(Number);
  adj[start].push({ end, cost });
}

const [startCity, endCity] = input.at(-1).split(" ").map(Number);

const getMinNode = () => {
  let minDistance = Infinity;
  let minNode = -1;

  for (let i = 1; i <= N; i++) {
    if (!visited[i] && dist[i] < minDistance) {
      minDistance = dist[i];
      minNode = i;
    }
  }
  return minNode;
};

const dijkstra = (start, end) => {
  dist[start] = 0;

  for (let i = 0; i < N; i++) {
    const curNode = getMinNode();
    if (curNode === -1) break;
    visited[curNode] = true;

    for (const { end: nextCity, cost } of adj[curNode]) {
      const distance = dist[curNode] + cost;

      if (distance < dist[nextCity]) {
        dist[nextCity] = distance;
      }
    }
  }

  return dist[end];
};

console.log(dijkstra(startCity, endCity));
