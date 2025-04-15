const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

const [M, N] = input[0].split(" ").map(Number);
const matrix = [];
const dp = Array.from(Array(M), () => Array(N).fill(-1));
// 상 하 좌 우
const dx = [-1, 1, 0, 0];
const dy = [0, 0, -1, 1];

for (let i = 1; i < 1 + M; i++) {
  matrix.push(input[i].split(" ").map(Number));
}

const dfs = (x, y) => {
  if (y === M - 1 && x === N - 1) {
    return 1;
  }

  if (dp[y][x] !== -1) {
    return dp[y][x];
  }

  dp[y][x] = 0;

  for (let d = 0; d < 4; d++) {
    const nx = x + dx[d];
    const ny = y + dy[d];

    // matrix 범위 벗어나면 지나가
    if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
      continue;
    }

    // 기존이 더 작으면 지나가
    if (matrix[y][x] <= matrix[ny][nx]) {
      continue;
    }

    dp[y][x] += dfs(nx, ny);
  }

  return dp[y][x];
};

const main = () => {
  console.log(dfs(0, 0));
};

main();
