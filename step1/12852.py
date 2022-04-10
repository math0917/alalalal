import sys
import collections
n = int(sys.stdin.readline())

idx = 1
dp = [float('inf')]*(n+1)
graph = [float('inf')]*(n+1)
dp[1] =1

for i in range(1, n+1):
    if i%3 == 0:
        if dp[i]>dp[i//3] + 1:
            dp[i] = dp[i//3] + 1
            graph[i] = i//3
    if i%2 == 0:
        if dp[i] > dp[i//2] + 1:
            dp[i] = dp[i//2] + 1
            graph[i] = i//2
    if dp[i] > dp[i-1] + 1:
        dp[i] =dp[i-1] + 1
        graph[i] =i-1

print(dp[-1]-1)
idx = n
if idx == 1:
    print(1)
    exit()
print(idx,'',end='')
while True:
    print(graph[idx],'',end='')
    idx = graph[idx]
    if idx == 1:
        break