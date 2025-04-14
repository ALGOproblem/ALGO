const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = Number(input[0]);

// 초기화
const dp = Array.from({ length: N + 1 }, () => 0); // dp[i]는 i일까지 얻을 수 있는 최대 이익

const counsel = [];
for (let i = 1; i < N + 1; i++) {
  const [day, cost] = input[i].trim().split(" ").map(Number);
  counsel.push({ day, cost });
}

for (let i = 0; i < N; i++) {
  // 이전 값 들고오기
  dp[i + 1] = Math.max(dp[i], dp[i + 1]);

  const { day, cost } = counsel[i];
  if (i + day > N) {
    continue;
  }

  // 다음 값 갱신
  dp[i + day] = Math.max(dp[i + day], dp[i] + cost);
}

console.log(dp[N]);
