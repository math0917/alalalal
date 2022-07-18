import sys
input = sys.stdin.readline
import heapq
import collections

day, M, N = map(int,input().split())

arr = [list(map(int,input().split())) for _ in range(N)]

heap = []

heapq.heapify(heap)
for priority, level in arr:
    heapq.heappush(heap, (level, -priority))
comparePriority = 0
delheap = []
while heap:
    
    if day == 0:
        level, priority = heapq.heappop(heap)
        priority *= -1
        this_turn = heapq.heappop(delheap)
        
        if priority < this_turn:
            heapq.heappush(delheap, this_turn)
            continue
        comparePriority -= this_turn
        day+=1
    else:
        level, priority = heapq.heappop(heap)
        priority*=-1
    comparePriority += priority
    heapq.heappush(delheap, priority)
    if comparePriority >= M:
        day -= 1
        while day > 0:
            level, priority = heapq.heappop(heap)
            day -= 1
        print(level)
        sys.exit()
    day-=1
print(-1)