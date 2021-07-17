import sys

n = int(sys.stdin.readline())

arr = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

for i in arr:
    diff = i[1]-i[0]
    sum = 0
    for j in range(diff):
        sum += j//2+1
        if sum>=diff:
            break
    print(j+1)