import sys
input = sys.stdin.readline
import heapq


n = int(input())

path = [list(map(int,input().split())) for _ in range(n)]

length = int(input())
heap = []
ever_heap = []
for i in path:
    i.sort()
    heapq.heappush(heap, (i[1],i[0]))
max_length = 0
while heap:
    this_turn_finish, this_turn_start = heapq.heappop(heap)
    if this_turn_finish - this_turn_start > length:
        continue
    heapq.heappush(ever_heap, this_turn_start)
    while ever_heap:
        this_turn = heapq.heappop(ever_heap)
        if this_turn_finish - this_turn <= length:
            heapq.heappush(ever_heap, this_turn)
            break
    max_length = max(max_length, len(ever_heap))
print(max_length)