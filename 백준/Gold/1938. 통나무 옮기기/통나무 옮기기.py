import sys
input = sys.stdin.readline
import collections
dx= [0,0,-1,1]
dy = [1,-1,0,0]
tong = []
n = int(input())

visited = [[[False] * 2 for _ in range(n)] for _ in range(n)]
map = [list(input().strip()) for _ in range(n)]

for i in range(n):
  for j in range(n):
    if map[i][j] == 'B':
        map[i][j] = '0'
        tong.append((i,j))
q = collections.deque([])
if tong[1][0] == tong[0][0]:
  visited[tong[1][0]][tong[1][1]][0] = True
  q.append((tong[1][0], tong[1][1], 0, 0))
else:
  visited[tong[1][0]][tong[1][1]][1] = True
  q.append((tong[1][0], tong[1][1], 1, 0))
  
while q:
  thisTurn = q.popleft()
  thisRow = thisTurn[0]
  thisCol = thisTurn[1]
  thisDir = thisTurn[2]
  thisLen = thisTurn[3]
  thisRowCol = []
  count = 0
  if map[thisRow][thisCol] == 'E':
    count += 1
  thisRowCol.append((thisRow, thisCol))
  for i in range(2):
    row = thisRow + dx[thisDir * 2 + i]
    col = thisCol + dy[thisDir * 2 + i]
    if map[row][col] == 'E':
      count += 1
    thisRowCol.append((thisRow + dx[thisDir * 2 + i], thisCol + dy[thisDir * 2 + i]))
  if count == 3:
    print(thisLen)
    sys.exit(0)
  for k in range(4):
    row = thisRow + dx[k]
    col = thisCol + dy[k]
    if 0 <= row < n and 0 <= col < n and not visited[row][col][thisDir]:
      for rowCol in thisRowCol:
        row = rowCol[0] + dx[k]
        col = rowCol[1] + dy[k]
        if not(0 <= row < n and 0 <= col < n and map[row][col] !='1'):
          break
      else:
        row = thisRow + dx[k]
        col = thisCol + dy[k]
        visited[row][col][thisDir] = True
        q.append((row,col,thisDir,thisLen + 1))
  newDir = (thisDir + 1) % 2
  if not visited[thisRow][thisCol][newDir]:
    flag = True
    for i in range(-1,2):
      for j in range(-1,2):
        row = thisRow + i
        col = thisCol + j
        if not (0 <= row < n and 0 <= col < n and map[row][col] != '1'):
          flag = False
          break
      if not flag :
        break        
    if flag:
      visited[thisRow][thisCol][newDir] = True
      q.append((thisRow, thisCol, newDir, thisLen + 1))

print(0)