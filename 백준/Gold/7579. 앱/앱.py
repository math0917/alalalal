import sys
import collections
input = sys.stdin.readline
stack = collections.deque(['*'])
n, m = map(int,input().split())

byte = list(map(int,input().split()))

cost = list(map(int,input().split()))

dp = [[0]*(sum(cost)+1) for _ in range(n+1)]

result = sum(cost)

for i in range(1,n+1):
    for j in range(sum(cost)+1):
        if cost[i-1] > j:
            dp[i][j] = dp[i-1][j]
        else:
            dp[i][j] = max(dp[i-1][j-cost[i-1]] + byte[i-1], dp[i-1][j])
        if dp[i][j]>=m:
            result = min(result,j)
print(result)