import sys
input = sys.stdin.readline
import collections
direction = { 
             1: (1,0),
             2:(-1,0),
             3:(0,-1),
             4:(0,1)
             }
loc = (0,0)
n=int(input())
x = set()
y = set()
dictionary = collections.defaultdict(list)
for _ in range(6):
  dir, len = map(int,input().split())
  loc = (loc[0] + direction[dir][0]*len, loc[1] + direction[dir][1]*len)
  dictionary[loc[0]].append(loc[1])
  x.add(loc[0])
  y.add(loc[1])

min_x, max_x= min(x),max(x)
min_y, max_y = min(y), max(y)
for i in x:
  if i != min_x and i != max_x:
    mid_x = i
for i in y:
  if i != min_y and i != max_y:
    mid_y = i
# 왼쪽위 뻥
if max_y not in dictionary[min_x]:
  print(n * ((max_x - min_x)* (max_y- min_y) - (mid_x - min_x)* (max_y - mid_y)))
# 오른쪽 위 뻥
elif max_y not in dictionary[max_x]:
  print(n * ((max_x - min_x)* (max_y- min_y) - (max_x - mid_x)* (max_y - mid_y)))
  
# 왼쪽아래 뻥
elif min_y not in dictionary[min_x]:
  print(n * ((max_x - min_x)* (max_y- min_y) - (mid_x - min_x)* (mid_y - min_y)))
  
# 오른쪽 아래 뻥
else:
  print(n * ((max_x - min_x)* (max_y- min_y) - (max_x - mid_x)* (mid_y - min_y)))
  