import sys
input = sys.stdin.readline
import collections

def find(num):
  if parent[num] != num:
    parent[num] = find(parent[num])
    return parent[num]
  return num

def union (num1, num2):
  num1, num2 = min(num1, num2), max(num1, num2)
  num1_parent = find(num1)
  num2_parent = find(num2)
  parent[num2_parent] = num1_parent

dx = [-1,0,1,0]
dy = [0,1,0,-1]


r, c = map(int,input().split())

water = [list(input().strip()) for _ in range(r)]

parent = collections.defaultdict(int)

visited = [[0]*c for _ in range(r)]

section = 1

search_point = collections.deque([])

duck_section = []

for i in range(r):
  for j in range(c):
    if ((water[i][j] == '.')
        or (water[i][j] == 'L')) and not visited[i][j]:
      if water[i][j] == "L":
        duck_section.append(section)
        water[i][j] = '.'
      queue = collections.deque([(i,j)])
      visited[i][j] = section
      
      while queue:
        this_row , this_col = queue.popleft()
        flag = False
        for k in range(4):
          row = this_row + dx[k]
          col = this_col + dy[k]
          if 0 <= row < r and 0 <= col < c and not visited[row][col]:
            if water[row][col] == '.':
              
              visited[row][col] = section
              queue.append((row,col))
            elif water[row][col] == 'X':
              flag = True
              
            else:
              visited[row][col] = section
              water[row][col] = '.'
              queue.append((row,col))
              duck_section.append(section)
        if flag:
          search_point.append((this_row,this_col,1))
      section += 1

for i in range(1, section):
  parent[i] = i
this_count = 0
while find(duck_section[0]) != find(duck_section[1]):
  this_row, this_col, this_count = search_point.popleft()
  
  for k in range(4):
    row = this_row + dx[k]
    col = this_col + dy[k]
    if 0 <= row < r and 0 <= col < c:
      if water[row][col] == '.':
        continue
      elif water[row][col] == 'X':
        search_point.append((row,col, this_count + 1))
        # 어랏 뭔가 있는 친구네?
        if visited[row][col]:
          union(visited[row][col], visited[this_row][this_col])
          water[row][col] = '.'
        # 얘는 이제 진짜 순수한 친구 그 주변에 X
        else:
          visited[row][col] = visited[this_row][this_col]
          for t in range(4):
            find_row = row + dx[t]
            find_col = col + dy[t]
            if 0 <= find_row < r and 0 <= find_col < c:
              if visited[find_row][find_col]:
                union(visited[row][col], visited[find_row][find_col])
print(this_count)