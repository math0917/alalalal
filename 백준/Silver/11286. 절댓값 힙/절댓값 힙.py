import sys
import heapq
input = sys.stdin.readline
heap = []
N = int(input())

for _ in range(N):
    x = int(input())
    if x:
        heapq.heappush(heap,(abs(x), x))
    else:
        if heap:
            print(heapq.heappop(heap)[1])
        else:
            print(0)