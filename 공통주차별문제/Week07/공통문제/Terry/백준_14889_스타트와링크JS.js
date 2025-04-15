const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

// 1. 조합
// 2. 팀인지 확인
// 3. 능력치 계산

// 팀 능력치 차이가 최소일 때
const N = Number(input[0]);
const team = [];
const selected = Array.from({ length: N }, () => false);
let minNum = Infinity;

// 초기화
for (let index = 1; index < 1 + N; index++) {
  team.push(input[index].split(" ").map(Number));
}

// 1. 조합 구하기
const recur = (level) => {
  if (level === N) {
    cal();
    return;
  }

  for (let i = level; i < N; i++) {
    selected[i] = true;
    recur(i + 1);
    selected[i] = false;
  }
};

// 3. 팀 능력치 계산
const cal = () => {
  if (!isTeam()) {
    return;
  }

  let trueSum = 0;
  let falseSum = 0;
  for (let i = 0; i < N; i++) {
    for (let j = i + 1; j < N; j++) {
      if (selected[i] !== selected[j]) continue;
      if (selected[i]) {
        trueSum += team[i][j] + team[j][i];
        continue;
      }

      falseSum += team[i][j] + team[j][i];
    }
  }

  minNum = Math.min(minNum, Math.abs(trueSum - falseSum));
};

// 2 팀 분배가 반반인가?
const isTeam = () => {
  let cnt = 0;

  selected.forEach((bool) => {
    if (bool) cnt++;
  });

  // 팀 분배가 반이 아니면
  if (cnt !== N / 2) {
    return false;
  }

  return true;
};

recur(0);
console.log(minNum);
