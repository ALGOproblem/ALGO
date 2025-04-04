const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n")
  .map((element) => element.split(" "));

const N = Number(input[0]);
const line = input[1][0].split("");

// 1. 왼쪽으로 R보내기, B보내기
// 2. 오른쪽으로 R보내기, B보내기

const colors = ["R", "B"];

let result = Infinity;

for (let c = 0; c < 2; c++) {
  const color = colors[c];

  let leftCnt = 0;
  let leftFlag = false;
  for (let i = 0; i < N; i++) {
    if (line[i] !== color && !leftFlag) {
      leftFlag = true;
    }

    if (line[i] === color && leftFlag) {
      leftCnt++;
    }
  }

  let rightCnt = 0;
  let rightFlag = false;
  for (let i = N - 1; i >= 0; i--) {
    if (line[i] !== color && !rightFlag) {
      rightFlag = true;
    }

    if (line[i] === color && rightFlag) {
      rightCnt++;
    }
  }

  result = Math.min(leftCnt, rightCnt, result);
}

console.log(result);
