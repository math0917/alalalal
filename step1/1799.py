import sys
import collections

def possible(row,col):
    for i in range(4):
        iter_row = row + dx[i]
        iter_col = col + dy[i]
        while 0<= iter_row < n and 0 <= iter_col < n:
            if visited[iter_row][iter_col]:
                return False
            iter_row += dx[i]
            iter_col += dy[i]
    return True
def dfs(row,col,cnt,is_even):
    result[is_even] = max(result[is_even],cnt)
    x = row
    y = col 

    for i in range(x,n):
        while y < n:
            this_turn_row,this_turn_col = i, y
            if not visited[this_turn_row][this_turn_col] and chess[this_turn_row][this_turn_col] and possible(this_turn_row, this_turn_col):
                visited[this_turn_row][this_turn_col] = True
                dfs(this_turn_row,this_turn_col, cnt+1, is_even)
                visited[this_turn_row][this_turn_col] = False
            y += 2
        y = 0 if (is_even and (i+1)%2 == 0) or (not(is_even) and (i+1)%2) else 1

        
    


n = int(sys.stdin.readline())

chess = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

dx = [1,1,-1,-1]
dy = [1,-1,1,-1]

visited = [[False]*n for _ in range(n)]
result=[0,0]
dfs(0,0,0,1)
dfs(0,1,0,0)         

print(sum(result))
