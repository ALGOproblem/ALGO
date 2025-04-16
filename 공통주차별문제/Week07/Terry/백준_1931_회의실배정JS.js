const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input[0];

const meetingList = [];

for (let index = 1; index < N + 1; index++) {
  const [start, end] = input[index].split(" ").map(Number);
  meetingList.push([start, end]);
}

meetingList.sort((a, b) => {
  // 종료시간 같으면 시작시간 기준으로 오름차순
  if (a[1] === b[1]) {
    return a[0] - b[0];
  }
  // 종료시간 기준으로 오름차순
  else {
    return a[1] - b[1];
  }
});

let cnt = 0;
let endTime = 0;

// 이전 종료 시간보다 현재 시작 시간이 같거나 크면 카운트
meetingList.forEach((meeting) => {
  const [start, end] = meeting;
  if(start >= endTime){
    endTime = end;
    cnt++;
  }
});

console.log(cnt);
