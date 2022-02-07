import sys
import heapq

def find(num):
    if parent[num] == num:
        return num
    else:
        parent[num] = find(parent[num])
        return parent[num]

def union(small, big):
    b = find(big)
    s = find(small)
    parent[b] = s
n = int(sys.stdin.readline())

space = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

heap_x = []
heap_y = []
heap_z = []
for i in range(n):
    heapq.heappush(heap_x,(space[i][0], i))
    heapq.heappush(heap_y,(space[i][1], i))
    heapq.heappush(heap_z,(space[i][2], i))
heap = []
for _ in range(n-1):
    x1 = heapq.heappop(heap_x)
    x2 = heapq.heappop(heap_x)
    heapq.heappush(heap,(x2[0]-x1[0], min(x1[1],x2[1]),max(x1[1],x2[1])))
    heapq.heappush(heap_x,x2)
    y1 = heapq.heappop(heap_y)
    y2 = heapq.heappop(heap_y)
    heapq.heappush(heap,(y2[0]-y1[0], min(y1[1],y2[1]),max(y1[1],y2[1])))
    heapq.heappush(heap_y,y2)
    z1 = heapq.heappop(heap_z)
    z2 = heapq.heappop(heap_z)
    heapq.heappush(heap,(z2[0]-z1[0], min(z1[1],z2[1]),max(z1[1],z2[1])))
    heapq.heappush(heap_z,z2)
parent = [i for i in range(n)]
result = 0
count = 0
while count < n-1:
    this_turn = heapq.heappop(heap)
    if find(this_turn[1]) != find(this_turn[2]):
        result += this_turn[0]
        count += 1
        union(this_turn[1], this_turn[2])
print(result)