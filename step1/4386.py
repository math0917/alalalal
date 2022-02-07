import sys
import heapq

def find(num):
    if parent[num] != num:
        parent[num] = find(parent[num])
        return parent[num]
    else:
        return num

def union(small, big):
    s = find(small)
    b = find(big)
    parent[b] = s
n = int(sys.stdin.readline())

star = [list(map(float,sys.stdin.readline().split())) for _ in range(n)]

heap = []

for i in range(n-1):
    for j in range(i+1,n):
        heapq.heappush(heap,(((star[i][0] - star[j][0])**2 + (star[i][1] - star[j][1])**2)**0.5, i, j))

parent = [i for i in range(n)]
count = 0
result = 0

while count < n-1:
    this_turn = heapq.heappop(heap)
    if find(this_turn[1]) != find(this_turn[2]):
        result += this_turn[0]
        union(this_turn[1], this_turn[2])
        count += 1

print(round(result,2))

