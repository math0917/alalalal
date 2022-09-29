import sys
input = sys.stdin.readline
import collections

direction = {'D':(1,0), 'U' : (-1,0), 'R':(0,1), "L" : (0,-1)}

n, m = map(int,input().split())

maze = [list(input().strip()) for _ in range(n)]
# 0은 안가봄 1은 이번턴에가봄 2는 가봤는데 못빠져나감 3은 가봤는데 빠져나감
visited = [[0]*m for _ in range(n)]
result = 0
for i in range(n):
  for j in range(m):
    if not visited[i][j]:
      stack = collections.deque([(i,j)])
      visited[i][j] = 1
      while stack:
        this_row, this_col = stack[-1]
        row_add, col_add = direction[maze[this_row][this_col]]
        row = this_row + row_add
        col = this_col + col_add
        # ㅇㅋ 이 친구는 합격
        if 0 > row or row >= n or 0 > col or col >= m:
          while stack:
            this_row, this_col = stack.popleft()
            result += 1
            visited[this_row][this_col] = 3
        elif visited[row][col] == 0:
          stack.append((row,col))
          visited[row][col] = 1
        elif visited[row][col] == 1:
          visited[row][col] = 2
          while stack:
            this_row, this_col = stack.popleft()
            visited[this_row][this_col] = 2
        elif visited[row][col] == 2:
          while stack:
            this_row, this_col = stack.popleft()
            visited[this_row][this_col] = 2
        else:
          while stack:
            this_row, this_col = stack.popleft()
            result += 1
            visited[this_row][this_col] = 3
print(result)