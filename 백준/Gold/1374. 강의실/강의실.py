import sys
import heapq
n = int(sys.stdin.readline())

heap = []
count =0
for _ in range(n):
    n, start, finish = map(int,sys.stdin.readline().split())
    heapq.heappush(heap, (start,finish))
right_heap = []
while heap:
    this_turn = heapq.heappop(heap)
    if right_heap:
        this_turn_finish, this_turn_start = heapq.heappop(right_heap)
        if this_turn[0] >= this_turn_finish:
            heapq.heappush(right_heap,(this_turn[1], this_turn[0]))
        else:
            count += 1
            heapq.heappush(right_heap,(this_turn_finish, this_turn_start))
            heapq.heappush(right_heap,(this_turn[1], this_turn[0]))
    else:
        count += 1
        heapq.heappush(right_heap, (this_turn[1],this_turn[0]))
print(count)