import sys
input = sys.stdin.readline
import collections

dx = [-1,0,1,0]
dy = [0,1,0,-1]

n, m = map(int,input().split())
maze = [list(input().strip()) for _ in range(n)]
flag = False
for i in range(n):
  for j in range(m):
    if maze[i][j] == '0':
      maze[i][j] = "."
      flag = True
      break
  if flag:
    break
result = float('inf')
visited = [[[False]*m for _ in range(n)] for _ in range(64)]

queue = collections.deque([])

queue.append((i,j,0,0))

while queue:
  this_row, this_col, this_key, this_count= queue.popleft()
  for i in range(4):
    row = this_row + dx[i]
    col = this_col + dy[i]
    if 0 <= row < n and 0 <= col < m and not visited[this_key][row][col]:
      if maze[row][col] == '.':
        queue.append((row,col,this_key,this_count+1))
        visited[this_key][row][col] = True
      elif 'a' <= maze[row][col] <= 'f':
        temp_key = this_key | 1 << (ord(maze[row][col]) - ord('a'))
        visited[temp_key][row][col] = True
        queue.append((row,col,temp_key,this_count+1))
      elif 'A' <= maze[row][col] <="F":
        if this_key & (1 << (ord(maze[row][col]) - ord("A"))):
          visited[this_key][row][col] = True
          queue.append((row,col,this_key,this_count+1))
      elif maze[row][col] == '1':
        print(this_count+1)
        sys.exit()
print(-1)