import sys
input = sys.stdin.readline
import collections

n, m, r = map(int,input().split())

graph = collections.defaultdict(list)

for _ in range(m):
  fr, to = map(int,input().split())
  graph[fr].append(to)
  graph[to].append(fr)

for i in graph.keys():
  graph[i].sort(key = lambda x : -x)
  
stack = collections.deque([r])
visited =[False]*(n+1)
result = [0]*(n+1)
count = 1
while stack:
  this_turn_dot = stack.pop()
  if not visited[this_turn_dot]:
    result[this_turn_dot] = count 
    count += 1
    visited[this_turn_dot] = True
  for i in graph[this_turn_dot]:
    if not visited[i]:
      stack.append(i)
      
for i in range(1,len(visited)):
  print(result[i])
      

