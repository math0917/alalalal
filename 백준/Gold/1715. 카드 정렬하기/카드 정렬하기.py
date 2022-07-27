import sys
input = sys.stdin.readline
import heapq

n = int(input())
heap = []
for _ in range(n):
    heapq.heappush(heap, int(input()))
count = 0
while heap:
    num1 = heapq.heappop(heap)
    if heap:
        num2 = heapq.heappop(heap)
        count += num1 + num2
        heapq.heappush(heap, num1 + num2)
    else:
        print(count)
