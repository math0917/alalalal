import sys
import collections
n, m = map(int,sys.stdin.readline().split())

prior = [list(map(int,sys.stdin.readline().split())) for _ in range(m)]

parent_count = [0]*(n+1)

graph = [[] for _ in range(n+1)]

for i in range(m):
    for j in range(1,prior[i][0]):
        graph[prior[i][j]].append(prior[i][j+1])
        parent_count[prior[i][j+1]] += 1
result = []
stack = collections.deque([])
for i in range(1,n+1):
    if not parent_count[i]:
        stack.append(i)

while stack:
    this_turn = stack.popleft()
    result.append(this_turn)
    for i in graph[this_turn]:
        parent_count[i] -= 1
        if not parent_count[i]:
            stack.append(i)
if len(result) == n:
    for i in result:
        print(i)
else:
    print(0)