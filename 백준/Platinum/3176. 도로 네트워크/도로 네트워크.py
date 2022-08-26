import sys
input = sys.stdin.readline
import collections
import math
sys.setrecursionlimit(100000)
def dfs(par,now,level):
  depth[now] = level
  parent[now][0] = par

  min_len[now][0] = dictionary[now][par]
  max_len[now][0] = dictionary[now][par]
  for i in dictionary[now].keys():
    if i!= par:
      dfs(now,i, level+1)

n = int(input())

dictionary = collections.defaultdict(dict)

for _ in range(n-1):
  fr, to, len = map(int,input().split())
  dictionary[fr][to] = len
  dictionary[to][fr] = len
dictionary[0][1] = 0
dictionary[1][0] = 0
tree_height = math.ceil(math.log2(n))+1

parent = [[0]*(tree_height+1) for _ in range(n+1)]
min_len = [[float('inf')]*(tree_height+1) for _ in range(n+1)]
max_len = [[0]*(tree_height+1) for _ in range(n+1)]
depth = [0]*(n+1)

dfs(0,1,0)

for i in range(1,tree_height+1):
  for j in range(1,n+1):
    parent[j][i] = parent[parent[j][i-1]][i-1]
    min_len[j][i] = min(min_len[j][i-1], min_len[parent[j][i-1]][i-1])
    max_len[j][i] = max(max_len[j][i-1], max_len[parent[j][i-1]][i-1])

m = int(input())

for _ in range(m):
  fr, to = map(int,input().split())
  minimum = float('inf')
  maximum = 0
  if depth[fr] < depth[to]:
    fr, to = to, fr
  while depth[fr] - depth[to]:
    index = math.floor(math.log2(depth[fr]-depth[to]))
    minimum = min(minimum, min_len[fr][index])
    maximum = max(maximum, max_len[fr][index])
    fr = parent[fr][index]
  for i in reversed(range(tree_height)):
    if parent[fr][i] != parent[to][i]:
      minimum = min(minimum, min_len[fr][i], min_len[to][i])
      maximum = max(maximum, max_len[fr][i], max_len[to][i])
      fr = parent[fr][i]
      to = parent[to][i]
  
  if fr != to:
    print(min(minimum, min_len[fr][0], min_len[to][0]), max(maximum, max_len[to][0], max_len[fr][0]))
  else:
    print(minimum, maximum)