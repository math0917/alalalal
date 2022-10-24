import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)
def next_dot_anti_clock(before_dot,row_depth):
  before_row, before_col = before_dot[0],before_dot[1]
  # 맨 왼쪽
  if before_col == 0 :
    if before_row :
      return (before_row-1,before_col)
    else:
      return (before_row, before_col + 1)
  # 오른쪽
  else:
    # 맨오른쪽
    if before_col == c-1:
      if before_row == row_depth:
        return (before_row,before_col-1)
      else:
        return (before_row+1, before_col)
    else:
      if before_row:
        return (before_row, before_col-1)
      else:
        return(before_row, before_col + 1)
def next_dot_clock(before_dot, row_depth):
  before_row, before_col = before_dot[0], before_dot[1]
  if before_col == 0:
    if before_row == r-1:
      return (before_row,before_col +1)
    else:
      return (before_row+1, before_col)
  else:
    # 맨 오른쪽
    if before_col == c-1:
      if before_row == row_depth:
        return (before_row, before_col-1)
      else:
        return (before_row-1, before_col)
    else:
      if before_row == r-1:
        return (before_row , before_col + 1)
      else:
        return (before_row, before_col-1)

def rotate(before_dot,row_depth,is_clock):
  if not is_clock:
    next_row, next_col = next_dot_anti_clock(before_dot,row_depth)
  if is_clock:
    next_row, next_col = next_dot_clock(before_dot, row_depth)
  if (next_row, next_col) == (dirt_row,0) or (next_row, next_col) == (dirt_row+1,0):
    dirt[before_dot[0]][before_dot[1]][0]= 0
    return
  dirt[before_dot[0]][before_dot[1]][0] = dirt[next_row][next_col][0]
  rotate((next_row, next_col),row_depth,is_clock)
  

r, c, t = map(int,input().split())

dirt = [list(map(int,input().split())) for _ in range(r)]

for i in range(r):
  for j in range(c):
    if dirt[i][j] == -1:
      dirt_row = i - 1
      
    dirt[i][j]=[dirt[i][j],0]

dx = [1,0,-1,0]
dy = [0,1,0,-1]

for _ in range(t):
  for i in range(r):
    for j in range(c):
      # 우선 먼지 확산부터 하자
      if dirt[i][j][0] > 0:
        count = 0
        for k in range(4):
          row = i + dx[k]
          col = j + dy[k]
          if 0 <= row < r and 0 <= col < c and dirt[row][col][0] != -1:
            if (row,col) < (i,j):
              dirt[row][col][0] += dirt[i][j][0]//5
              count += 1
            else:
              dirt[row][col][1] += dirt[i][j][0]//5
              count += 1
        dirt[i][j][0] -= (dirt[i][j][0]//5)*count
      if dirt[i][j][0] != -1:
        dirt[i][j][0] += dirt[i][j][1]
        dirt[i][j][1] = 0
      # rotate함수
  rotate((dirt_row-1,0),dirt_row,False)
  rotate((dirt_row+2,0),dirt_row+1, True)
  
      
      
result = 2

for i in range(r):
  for j in range(c):
    result += dirt[i][j][0]

print(result)
