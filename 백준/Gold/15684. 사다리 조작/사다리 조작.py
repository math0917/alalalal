import sys
input = sys.stdin.readline

def check():
  for i in range(n):
    temp = i
    for j in range(h):
      if graph[j][temp]:
        temp += 1
      elif temp > 0 and graph[j][temp-1]:
        temp -= 1
    if temp != i:
      return False
  return True

def dfs(count, row, col):
  global ans
  if ans <= count:
    return
  if check():
    ans = min(count, ans)
    return
  if count == 3:
    return
  for i in range(row, h):
    x = col if i == row else 0
    for j in range(x, n-1):
      if graph[i][j] == 0:
        graph[i][j] = 1
        dfs(count+1, i, j+2)
        graph[i][j] = 0

n, m, h = map(int,input().split())
graph = [[0]*n for _ in range(h)]
for _ in range(m):
  a, b = map(int,input().split())
  graph[a-1][b-1] = 1
ans = 4
dfs(0,0,0)
print(ans if ans <= 3 else -1)