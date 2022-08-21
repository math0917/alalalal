import sys
import collections
input = sys.stdin.readline

n, m = map(int,input().split())

maze = [list(map(int,input().strip())) for _ in range(n)]
visited = [[0]*m for _ in range(n)]

dx = [-1,0,1,0]
dy = [0,1,0,-1]

queue = collections.deque([(0,0,1)])

while queue:
 
  this_row, this_col, this_count = queue.popleft()
  if this_row == n-1 and this_col == m-1:
    print(this_count)
    sys.exit()
  for i in range(4):
    row = this_row + dx[i]
    col = this_col + dy[i]
    if 0 <= row < n and 0 <= col < m and maze[row][col]:
      if not visited[row][col]:
        queue.append((row,col, this_count + 1))
        visited[row][col] = 1