import sys
input = sys.stdin.readline

n = int(input())

consult = [list(map(int,input().split())) for _ in range(n)]

profit = [0]*n


for i in range(n):
  before = profit[i-1]
  if i + consult[i][0] - 1 < n:
    profit[i+consult[i][0]-1] = max(profit[i+consult[i][0]-1], before + consult[i][1])
  profit[i] = max(before, profit[i])
print(profit[-1])