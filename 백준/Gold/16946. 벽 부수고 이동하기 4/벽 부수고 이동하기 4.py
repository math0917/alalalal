import sys
input = sys.stdin.readline
import collections
n, m = map(int,input().split())

maze = [list(map(int,input().strip())) for _ in range(n)]
dx = [-1,0,1,0]
dy = [0,1,0,-1]
dictionary = collections.defaultdict(int)

dictionary[1] = 0
set_num = 1
for i in range(n):
    for j in range(m):
        if maze[i][j] == 0:
            set_num += 1
            count = 1
            maze[i][j] = set_num
            stack = collections.deque([(i,j)])
            while stack:
                row, col = stack.popleft()
                for k in range(4):
                    this_row = row + dx[k]
                    this_col = col + dy[k]
                    if 0<= this_row < n and 0<=this_col<m and maze[this_row][this_col] == 0:
                        count += 1
                        maze[this_row][this_col] = set_num
                        stack.append((this_row,this_col))
            dictionary[set_num] = count
for i in range(n):
    for j in range(m):
        if maze[i][j] == 1:
            this_turn_set = set()
            for k in range(4):
                row = i+dx[k]
                col = j+dy[k]
                if 0<= row < n and 0 <= col < m:
                    this_turn_set.add(maze[row][col])
            count = 0
            for p in this_turn_set:
                count += dictionary[p]
            count += 1
        else:
            count = 0
        print(count%10,end='')
    print('')