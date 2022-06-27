import sys
input = sys.stdin.readline
import collections
direction = {
    "D": (1,0),
    "L": (0,-1),
    "R": (0,1),
    "U": (-1,0)
}

N, M = map(int,input().split())

maze = [list(input().strip()) for _ in range(N)]
dictionary = collections.defaultdict(int)
visited = [[0]*M for _ in range(N)]
count = 0
set_num = 0
for i in range(N):
    for j in range(M):
        if visited[i][j] == 0:
            set_num += 1
            visited[i][j] = set_num
            count += 1

            row = i
            col = j
            while True:
                this_row = row + direction[maze[row][col]][0]
                this_col = col + direction[maze[row][col]][1]
                if visited[this_row][this_col]:
                    #갈수 없는데
                    if visited[this_row][this_col] == set_num:
                        #이번턴에 온곳이구나
                        break
                    else:
                        #이미 예전에 왔었어? 그럼 넌 안됨
                        count -= 1
                        break
                else:
                    #갈수 있다...
                    visited[this_row][this_col] = set_num
                    row, col = this_row, this_col
print(count)
