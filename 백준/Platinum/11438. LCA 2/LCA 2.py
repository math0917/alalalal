import sys
input = sys.stdin.readline
import collections
import math
sys.setrecursionlimit(100000)
def dfs(par, now, level):
  depth[now] = level
  parent[now][0] = par
  for i in dictionary[now]:
    if i!= par:
      dfs(now, i, level+1)

n = int(input())

dictionary = collections.defaultdict(list)

for _ in range(n-1):
  fr, to = map(int,input().split())
  dictionary[fr].append(to)
  dictionary[to].append(fr)
dictionary[1].append(0)
dictionary[0].append(1)
tree_height = math.ceil(math.log2(n))+1

parent = [[0]*(tree_height+1) for _ in range(n+1)]
depth = [0]*(n+1)
dfs(0,1,0)


m = int(input())
for i in range(1, tree_height+1):
  for j in range(1, n+1):
    parent[j][i] = parent[parent[j][i-1]][i-1]
for _ in range(m):
  fr, to = map(int,input().split())
  if depth[fr] < depth[to]:
    fr, to = to, fr
    
  while depth[fr] - depth[to]:
   
    index = math.floor(math.log2(depth[fr]- depth[to]))
    
    fr = parent[fr][index]
  
  for i in reversed(range(tree_height)):
    if parent[fr][i] != parent[to][i]:
      fr, to = parent[fr][i], parent[to][i]
  if fr!= to:
    print(parent[fr][0])
  else:
    print(fr)
  