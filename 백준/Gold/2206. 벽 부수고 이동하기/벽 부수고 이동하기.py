import sys
input = sys.stdin.readline
import collections

n, m = map(int, input().split())

maze = [list(map(int,input().strip())) for _ in range(n)]

visited = [[[float('inf')]*2 for _ in range(m)] for _ in range(n)]
# n, m, 벽을 부신횟수, 이동한 횟수
stack = collections.deque([(0,0,0,1)])
result = float('inf')
dx = [-1,0,1,0]
dy = [0,1,0,-1]
visited[0][0][0] = 1
while stack: 
   
    this_turn_row, this_turn_col, this_turn_block, this_turn_length = stack.popleft()
   
    if this_turn_row+1 == n and this_turn_col+1 == m:
        result = min(result,this_turn_length)
    else:
        for i in range(4):
            row = this_turn_row + dx[i]
            col = this_turn_col + dy[i]
            if 0 <= row < n and 0 <= col < m:
                # 갈 수 있구나
                if maze[row][col] == 0:
                    # 그럼 비교해서 갈 수 잇는지 확인
                    if visited[row][col][this_turn_block] > this_turn_length + 1:
                        visited[row][col][this_turn_block] = this_turn_length + 1
                        stack.append((row,col,this_turn_block, this_turn_length+1))
                # 갈 수 없으면 뚫고 갈 수 있는지 확인
                else:
                    if this_turn_block == 0:
                        if visited[row][col][1] > this_turn_length +1 :
                            visited[row][col][1] = this_turn_length+ 1    
                            stack.append((row,col,1, this_turn_length+1))
if type(result) == int:
    print(result)
else:
    print(-1)