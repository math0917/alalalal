import sys
input = sys.stdin.readline

sys.setrecursionlimit(10**8)
def dfs(num):
    visited[num] = True
    dp[num][0] = 1
    for i in graph[num]:
        if not visited[i]:
            dfs(i)
            dp[num][1] += dp[i][0]
            dp[num][0] += min(dp[i][0],dp[i][1])



n =  int(input())
graph = [[] for _ in range(n+1)]

for _ in range(n-1):
    v1, v2 = map(int,sys.stdin.readline().split())
    graph[v1].append(v2)
    graph[v2].append(v1)

dp = [[0,0] for _ in range(n+1)]
visited = [False]* (n+1)

dfs(1)

print(min(dp[1][0], dp[1][1]))