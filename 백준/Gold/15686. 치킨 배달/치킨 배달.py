import sys
import itertools

input = sys.stdin.readline

n, m = map(int,input().split())

chicken = [list(map(int,input().split())) for _ in range(n)]
chicken_count = set()
house = set()
for i in range(n):
  for j in range(n):
    chicken_count.add((i,j) if chicken[i][j]==2 else (-1,-1))
    chicken[i][j] = 0 if chicken[i][j]==2 else chicken[i][j]
    house.add((i,j) if chicken[i][j] == 1 else (-1,-1))
house.remove((-1,-1))
chicken_count.remove((-1,-1))

result = float('inf')

for i in itertools.combinations(chicken_count,m):
  count = 0
  for j in house:
    this_turn_count = float('inf')
    for k in i:  
      this_turn_count = min(this_turn_count,abs(j[0] - k[0]) + abs(j[1] - k[1]))
    count += this_turn_count
  result = min(result, count)
print(result)
