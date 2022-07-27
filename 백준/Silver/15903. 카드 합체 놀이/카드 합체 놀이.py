import sys
input = sys.stdin.readline
import heapq

n, m = map(int,input().split())
heap = []
card = list(map(int,input().split()))
for i in range(n):
    heapq.heappush(heap,(card[i], i))
    
for _ in range(m):
    num1, num1_index = heapq.heappop(heap)
    num2, num2_index = heapq.heappop(heap)
    card[num1_index] += num2
    card[num2_index] += num1
    heapq.heappush(heap,(num1+num2, num1_index))
    heapq.heappush(heap,(num1+num2,num2_index))
print(sum(card))