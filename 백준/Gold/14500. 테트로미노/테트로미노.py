
import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)
import itertools
def dfs(this_row,this_col,sum,count):
  global result
  # print(this_row,this_col,sum,count)
  if count == 4:
    
    result = max(sum, result)
    return
  if count == 1:
    stack = list()
    for i in range(4):
      row = this_row + dx[i]
      col = this_col + dy[i]
      if 0 <= row < n and 0 <= col < m:
        stack.append((row,col))
    if len(stack) >= 3:
      for iteration in itertools.combinations(stack,3):
        
        this_turn_max = maze[this_row][this_col]
        for i in iteration:
          this_turn_max += maze[i[0]][i[1]]
     
        result = max(result, this_turn_max)
  
  
  for i in range(4):
    row = this_row + dx[i]
    col = this_col + dy[i]
    if 0 <= row <n and 0 <= col <m and not visited[row][col]:
      visited[row][col] = True
      dfs(row,col,sum + maze[row][col], count + 1)
      visited[row][col] = False
  
  
n, m = map(int,input().split())

maze = [list(map(int,input().split())) for _ in range(n)]

dx = [0,1,0,-1]
dy = [-1,0,1,0]

result = 0

visited = [[0]*m for _ in range(n)]

for i in range(n):
  for j in range(m):
    visited[i][j] = True
    dfs(i,j,maze[i][j],1)
    visited[i][j] = False
print(result)