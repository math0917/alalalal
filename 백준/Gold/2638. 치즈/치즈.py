import sys
input = sys.stdin.readline
import collections
n, m = map(int,input().split())

dx = [-1,0,1,0]
dy = [0,1,0,-1]

cheese = [list(map(int,input().split())) for _ in range(n)]
time = 0

visited = [[False]*m for _ in range(n)]
stack = collections.deque([(0,0)])
visited[0][0] = True
while stack:
  this_row, this_col = stack.popleft()
  for i in range(4):
    row = this_row + dx[i]
    col = this_col + dy[i]
    if 0 <= row < n and 0 <= col < m and cheese[row][col] == 0 and not visited[row][col]:
      visited[row][col]= True
      stack.append((row,col))
      
while True:
  stack = collections.deque([])
  
  for i in range(n):
    for j in range(m):
      if cheese[i][j] == 1:
        count = 0
        for k in range(4):
          row = i + dx[k]
          col = j + dy[k]
          if 0 <= row < n and 0 <= col < m:
            if cheese[row][col] == 0 and visited[row][col]:
              count += 1
        if count >= 2:
          stack.append((i,j))
  if not stack:
    break
  else:
    time += 1
    visited_stack = collections.deque([])
    while stack:
      row, col = stack.pop()
      cheese[row][col] = 0
      visited[row][col] = True
      visited_stack.append((row,col))
    while visited_stack:
      this_row, this_col = visited_stack.popleft()
      for k in range(4):
        row = this_row + dx[k]
        col = this_col + dy[k]
        if 0 <= row < n and 0 <= col < m and cheese[row][col] == 0 and not visited[row][col]:
          visited[row][col] = True
          visited_stack.append((row,col))
print(time)