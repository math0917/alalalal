import sys
input = sys.stdin.readline
import collections
import math
sys.setrecursionlimit(100000)
def dfs(par, now, height):
  parent[now][0] = par
  depth[now] = height
  len[now][0] = dist[par][now]
  for i in dist[now].keys():
    if i != par:
      dfs(now, i, height+1)

n = int(input())

tree_height = math.ceil(math.log2(n))+1

dist = collections.defaultdict(dict)

for _ in range(n-1):
  fr, to, length = map(int,input().split())
  dist[fr][to] = length
  dist[to][fr] = length
dist[0][1] = 0
dist[1][0] = 0
m = int(input())


parent = [[0]*tree_height for _ in range(n+1)]

len = [[0]*tree_height for _ in range(n+1)]

depth = [0]*(n+1)

dfs(0,1,0)

for i in range(1, tree_height):
  for j in range(1, n+1):
    if parent[j][i-1] != 0:
      parent[j][i] = parent[parent[j][i-1]][i-1]
      len[j][i] = len[j][i-1]+ len[parent[j][i-1]][i-1]

for _ in range(m):
  fr, to = map(int,input().split())
  if depth[fr] != depth[to]:
    if depth[fr] < depth[to]:
      fr, to = to, fr
  result = 0

  
  while depth[fr] - depth[to]:
    index = math.floor(math.log2(depth[fr]-depth[to]))
    result += len[fr][index]
    fr = parent[fr][index]
 
  for i in reversed(range(tree_height)):
    if parent[fr][i] != parent[to][i]:
      result += len[fr][i] + len[to][i]
      fr = parent[fr][i]
      to = parent[to][i]
  if fr != to:
    result += len[fr][0] + len[to][0]
  print(result)