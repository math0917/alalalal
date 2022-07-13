import sys
input = sys.stdin.readline
import collections

n, m = map(int,input().split())

dx = [1,1,-1,-1,-1,1,0,0]
dy = [1,-1,1,-1,0,0,1,-1]

maze = [list(map(int,input().split())) for _ in range(n)]
max_length = 0
for i in range(n):
    for j in range(m):
        visited = [[False]*m for _ in range(n)]
        visited[i][j] = True
        stack = collections.deque([])
        stack.append((i,j,0))
        while stack:
            this_turn_row, this_turn_col, this_turn_len = stack.popleft()
            
            if maze[this_turn_row][this_turn_col] == 1:
                max_length = max(max_length, this_turn_len)
                break
            max_length = max(max_length, this_turn_len)
            for k in range(8):
                row = this_turn_row + dx[k]
                col = this_turn_col + dy[k]
                if 0<= row < n and 0<= col < m and not visited[row][col]:
                    visited[row][col] = True
                    stack.append((row,col,this_turn_len+1))
                    
                    
print(max_length)