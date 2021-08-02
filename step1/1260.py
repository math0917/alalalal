import sys
import collections
n, m, v1 = map(int,sys.stdin.readline().split())

arr = [list(map(int,sys.stdin.readline().split())) for _ in range(m)]

graph = dict()

for i in arr:
    if i[0] not in graph.keys():
        graph[i[0]] = [i[1]]
    else:
        graph[i[0]].append(i[1])
    if i[1] not in graph.keys():
        graph[i[1]] = [i[0]]
    else:
        graph[i[1]].append(i[0])
already_visited = set()
v = v1
if v1 not in graph.keys():
    print(v1)
    print(v1)
    sys.exit()
stack = collections.deque([v1])
while(True):
    try:
        start = stack.pop()
    except:
        break
    if start in already_visited:
        continue
    print(start,'',end='')
    already_visited.add(start)
    possible = set(graph[start])-already_visited
    
    if len(possible) == 0:
        continue
    possible = sorted(list(possible))
    for i in reversed(possible):
        stack.append(i)
v = v1
already_visited = set()

stack = collections.deque([v1])
print('')
while(True):
    try:
        start = stack.popleft()
    except:
        break
    if start in already_visited:
        continue
    print(start,'',end='')
    already_visited.add(start)
    possible_root = set(graph[start]) - already_visited
    if len(possible_root) == 0:
        continue
    
    possible = sorted(list(possible_root))
    for i in possible:
        stack.append(i)

