import sys
input = sys.stdin.readline
import collections

v, e = map(int,input().split())

edges = collections.defaultdict(list)
reverse_edge = collections.defaultdict(list)
for _ in range(e):
  fr, to = map(int,input().split())
  edges[fr].append(to)
  reverse_edge[to].append(fr)


proc_stack = collections.deque([])
return_stack = collections.deque([])
visited = [False]*(v+1)

for i in range(1,v+1):
  if not visited[i]:
    visited[i] = True
    proc_stack.append(i)
    while proc_stack:
      this_turn = proc_stack.pop()
      for i in edges[this_turn]:
        if not visited[i]:
          proc_stack.append(this_turn)
          visited[i] = True
          proc_stack.append(i)
          break   
      else:
        return_stack.append(this_turn)

visited = [False]*(v+1)
count = -1
result = []
while return_stack:
  this_turn = return_stack.pop()
  if not visited[this_turn]:
    result.append([])
    count += 1
    visited[this_turn] = True
    proc_stack.append(this_turn)
    while proc_stack:
     
      this_turn = proc_stack.pop()
      for i in reverse_edge[this_turn]:
        if not visited[i]:
          proc_stack.append(this_turn)
          visited[i] = True
          proc_stack.append(i)
          break
      else:
        result[count].append(this_turn)

for i in range(count+1):
  result[i].sort()

result.sort(key = lambda x: x[0])
print(count+1)
for i in result:
  for j in i:
    print(j,'',end='')
  print(-1)
  
  
