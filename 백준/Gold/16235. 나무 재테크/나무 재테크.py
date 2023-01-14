import sys
import collections
import heapq

input = sys.stdin.readline
dx = [-1, -1, -1, 0, 0 ,1 ,1 ,1]
dy = [-1, 0, 1, -1, 1, -1, 0, 1]
n, m, k = map(int,input().split())


s2d2 = [list(map(int, input().split())) for _ in range(n)]

ground = [[5]*n for _ in range(n)]

tree = [[collections.deque([]) for _ in range(n)] for _ in range(n)]

for _ in range(m):
  row, col, age = map(int,input().split())
  tree[row-1][col-1].append(age)
  


for test in range(k):
  for i in range(n):
    for j in range(n):
      if test:
        ground[i][j] += s2d2[i][j]
      if tree[i][j]:
        
        arr = collections.deque([])
        while tree[i][j]:
          tr = tree[i][j].popleft()
          if ground[i][j] >= tr:
            ground[i][j] -= tr
            arr.append(tr+1)
          else:
            ground[i][j] += tr //2
            for tr in tree[i][j]:
              
              ground[i][j] += tr // 2
            break
        
        tree[i][j] = arr
        
  
  for i in range(n):
    for j in range(n):
      
      for tr in tree[i][j]:
        
        if tr % 5 == 0:
          for t in range(8):
            row = i + dx[t]
            col = j + dy[t]
            if 0 <= row < n and 0 <= col < n:
              tree[row][col].appendleft(1)
  
  # for i in range(n):
  #   for j in range(n):
  #     print(tree[i][j],'',end='')
  #   print('')
result = 0
for i in range(n):
  for j in range(n):
    result += len(tree[i][j])
print(result)
            


        