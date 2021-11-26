import sys
import heapq

n, k = map(int,sys.stdin.readline().split()) 
# n은 보석의 정보개수 k 는 가방의 개수

jewel = []
for _ in range(n):
    weight, cost = map(int,sys.stdin.readline().split())
    heapq.heappush(jewel, (weight, cost))
# [0]무게 [1] 은 가격
bag = []
for _ in range(k):
    heapq.heappush(bag, int(sys.stdin.readline()))
result = 0
accumulate_cost = []
while bag:
    this_turn = heapq.heappop(bag)

    while jewel:
        weight, cost = heapq.heappop(jewel)
        if weight <= this_turn:
            heapq.heappush(accumulate_cost, -cost)
        else:
            heapq.heappush(jewel, (weight, cost))
            break
    if not accumulate_cost:
        continue
    result+= -heapq.heappop(accumulate_cost)

        
print(result)