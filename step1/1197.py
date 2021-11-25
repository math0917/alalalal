import sys
import heapq

def find(num):
    global parent
    if parent[num] == num:
        return num
    else:
        return find(parent[num])
def union(small, big):
   
    parent[find(big)] = find(small)
   

v, e = map(int,sys.stdin.readline().split())
heap = []
for _ in range(e):
    edge1, edge2, weight = map(int,sys.stdin.readline().split())
    heapq.heappush(heap, (weight, edge1, edge2))

parent = [i for i in range(v+1)]

weight = 0
line = 0
while line < v-1:
    this_turn = heapq.heappop(heap)
    small_val, big_val = ((this_turn[1], this_turn[2]) if this_turn[1]< this_turn[2] else (this_turn[2], this_turn[1]))
    if find(small_val) != find(big_val):
      
        weight += this_turn[0]
        line +=1
        union(small_val, big_val)
        
        
print(weight)
