import sys
input = sys.stdin.readline
import collections
import heapq
testCase = int(input())


def dijkstra(t):
  global m
  dist = [float('inf')] * m
  dist[0] = 0
  parent = [0] * m
  priorityQueue = []
  heapq.heappush(priorityQueue, (0,0))
  while priorityQueue:
    thisLen, thisTurn = heapq.heappop(priorityQueue)
    if thisLen > dist[thisTurn]:
      continue
    for next, length in graph[thisTurn]:
      if dist[next] > length + thisLen:
        dist[next] = length + thisLen
        parent[next] = thisTurn
        heapq.heappush(priorityQueue, (dist[next], next))

  if dist[m-1] == float('inf'):
    print("Case #", t, ": -1", sep = '')
  else:
    q = collections.deque([])
    thisTurn = m - 1
    q.append(thisTurn)
    while thisTurn != 0:
      thisTurn = parent[thisTurn]
      q.append(thisTurn)
    print("Case #", t, ": ", sep = '', end='')
    
    while q:
      print(q.pop(),"", end='')
    print('')

for t in range(1,testCase + 1):
  n, m = map(int,input().split())
  graph = collections.defaultdict(list)
  for _ in range(n):
    fr, to, z = map(int, input().split())
    graph[fr].append((to,z))
    graph[to].append((fr, z))
  dijkstra(t)

