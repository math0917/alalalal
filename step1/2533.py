import sys
input = sys.stdin.readline
import collections

graph = collections.defaultdict(list)

n =  int(input())

path = [list(map(int,input().split())) for _ in range(n)]

for v1, v2 in path:
    graph[v1].append(v2)
    graph[v2].append(v1)
