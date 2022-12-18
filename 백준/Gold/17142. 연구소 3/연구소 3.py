import sys
input = sys.stdin.readline
import itertools
import collections
n, m = map(int,input().split())

lab = [list(map(int,input().split())) for _ in range(n)]

ans = float('inf')

dx = [0,1,0,-1]
dy = [1,0,-1,0]

virus = set()
blankCount = 0
for i in range(n):
  for j in range(n):
    if lab[i][j] == 2:
      virus.add((i,j))
    elif lab[i][j] == 0:
      blankCount += 1
if blankCount == 0:
  print(0)
  sys.exit()
for i in itertools.combinations(virus,m):
  stack = collections.deque([])
  this_turn_set = set()
  this_virus = set()
  for j in i:
    stack.append((j,0))
    this_virus.add(j)
  while stack:
    this_turn_virus, this_turn_len = stack.popleft()
    this_row, this_col = this_turn_virus[0], this_turn_virus[1]
    for j in range(4):
      row = this_row + dx[j]
      col = this_col + dy[j]
      if 0 <= row < n and 0 <= col < n and lab[row][col] == 0 and (row,col) not in this_turn_set:
        stack.append(((row,col), this_turn_len + 1))
        this_turn_set.add((row,col))
        if len(this_turn_set) == blankCount:
          ans = min(ans, this_turn_len + 1)
      if 0 <= row < n and 0 <= col < n and lab[row][col] == 2 and (row,col) not in this_virus:
        this_virus.add((row,col))
        stack.append(((row,col), this_turn_len + 1))
        
if type(ans) == float:
  print(-1)
else:
  print(ans)