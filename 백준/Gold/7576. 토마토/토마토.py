import sys
input = sys.stdin.readline
import collections

m, n = map(int,input().split())

tomato = [list(map(int,input().split())) for _ in range(n)]

dx = [-1,0,1,0]
dy = [0,1,0,-1]
stack = collections.deque([])

visited = [[False]* m for _ in range(n)]

for i in range(n):
  for j in range(m):
    if tomato[i][j] == 1 and not visited[i][j]:
      stack.append((i,j,0))
      visited[i][j] = True
      
while stack:
  this_turn_row, this_turn_col, this_turn_count = stack.popleft()
  for i in range(4):
    row = this_turn_row + dx[i]
    col = this_turn_col + dy[i]
    if 0<= row < n and 0 <= col < m and not visited[row][col]:
      if tomato[row][col] == 0:
        visited[row][col] = True
        tomato[row][col] = 1
        stack.append((row,col,this_turn_count + 1))
    
for i in range(n):
  for j in range(m):
    if tomato[i][j] == 0:
      print(-1)
      sys.exit()
      

print(this_turn_count)
      
  