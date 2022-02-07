import sys

n = int(sys.stdin.readline())

point = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

plus = point[n-1][0] * point[0][1]
minus = point[n-1][1] * point[0][0]
for i in range(n-1):
    plus += point[i][0]*point[i+1][1]
    minus += point[i][1]*point[i+1][0]

print(round(abs(plus-minus)/2,1))

