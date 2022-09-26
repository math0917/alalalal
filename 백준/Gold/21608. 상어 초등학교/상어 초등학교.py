import sys
input = sys.stdin.readline
import collections

dx = [-1,0,1,0]
dy = [0,1,0,-1]

N = int(input())

classroom = [[0]*N for _ in range(N)]

like = collections.defaultdict(set)
queue = collections.deque([])
for _ in range(N**2):
  a, b, c, d, e = map(int,input().split())
  like[a].add(b)
  like[a].add(c)
  like[a].add(d)
  like[a].add(e)
  queue.append(a)

near_by_count = collections.defaultdict(int)
near_by_count['0 0'] =2
near_by_count['0 '+str(N-1)] = 2
near_by_count[str(N-1)+' 0'] = 2
near_by_count[str(N-1)+" "+str(N-1)]= 2

for i in range(1, N-1):
  near_by_count['0 '+ str(i)] = 3
  near_by_count[str(i)+' 0'] = 3
  near_by_count[str(N-1)+' '+str(i)] = 3
  near_by_count[str(i)+ ' '+str(N-1)] = 3
  for j in range(1, N-1):
    near_by_count[str(i) + ' ' +str(j)]= 4


while queue:
  this_turn_student = queue.popleft()
  this_turn_arround = -1
  for i in range(N):
    for j in range(N):
      if classroom[i][j] == 0:
        count = 0
        for k in range(4):
          row = i + dx[k]
          col = j + dy[k]
          if 0 <= row < N and 0 <= col < N and classroom[row][col] in like[this_turn_student]:
            count += 1
        if this_turn_arround < count:
          this_turn_row = i
          this_turn_col = j
          this_turn_arround = count
        elif this_turn_arround == count:
          if near_by_count[str(this_turn_row) + ' '+str(this_turn_col)] < near_by_count[str(i) + ' '+ str(j)]:
          
            this_turn_row = i
            this_turn_col = j
  
  classroom[this_turn_row][this_turn_col] = this_turn_student
  for k in range(4):
    row = this_turn_row + dx[k]
    col = this_turn_col + dy[k]
    if 0 <= row < N and 0 <= col < N :
      near_by_count[str(row) + ' '+ str(col)] -= 1
result = 0
for i in range(N):
  for j in range(N):
    count = 0
    for k in range(4):
      row = i + dx[k]
      col = j + dy[k]
      if 0 <= row < N and 0 <= col < N and classroom[row][col] in like[classroom[i][j]]:
        count += 1
    if count == 1:
      result += 1
    elif count == 2:
      result += 10
    elif count == 3:
      result += 100
    elif count == 4:
      result += 1000
print(result)

