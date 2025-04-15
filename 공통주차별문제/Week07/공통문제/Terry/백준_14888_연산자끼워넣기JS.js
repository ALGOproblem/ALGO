const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input[0];
const numbers = input[1].split(" ").map(Number);
const operators = input[2].split(" ").map(Number);
const selected = Array(N-1).fill(0);

let minNum = Infinity;
let maxNum = -Infinity;

const recur = (level) => {
  if (level == N - 1) {
    cal();
    return;
  }

  for (let i = 0; i < 4; i++) {
    if (operators[i] === 0) {
      continue;
    }

    selected[level] = i;
    operators[i]--;
    recur(level + 1);
    operators[i]++;
  }
};

// 식 계산
const cal = () => {
  let result = numbers[0];

  for (let i = 0; i < N - 1; i++) {
    const nextNum = numbers[i + 1];

    switch (selected[i]) {
      case 0:
        result += nextNum;
        break;
      case 1:
        result -= nextNum;
        break;
      case 2:
        result *= nextNum;
        break;
      case 3:
        result /= nextNum;
        break;
    }
  
    result = parseInt(result);
  }

  maxNum = Math.max(maxNum, result);
  minNum = Math.min(minNum, result);
};

const main = () => {
  recur(0);
  // js에서 0하고 -0하고 다르게 인식해서 -0은 false가 된다.
  console.log(maxNum ? maxNum : 0);
  console.log(minNum ? minNum : 0);
};

main();
