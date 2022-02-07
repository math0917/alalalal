import sys
import collections
import heapq

n, m = map(int,sys.stdin.readline().split())

directed_graph = collections.defaultdict(set)

indegree_count = [0]*(n+1)

for _ in range(m):
    first, later = map(int,sys.stdin.readline().split())
    directed_graph[first].add(later)
    indegree_count[later] += 1

heap = []

for i in range(1,n+1):
    if not indegree_count[i]:
        heapq.heappush(heap,i)

result = []

while heap:
    result.append(heapq.heappop(heap))
    for i in directed_graph[result[-1]]:
        indegree_count[i] -= 1
        if not indegree_count[i]:
            heapq.heappush(heap,i)
print(*result)
    