import sys
import collections
n, m, v1 = map(int,sys.stdin.readline().split())

arr = [list(map(int,sys.stdin.readline().split())) for _ in range(m)]

graph = dict()
# graph의 vertex와 edge 를 dict에 넣기!
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
#dfs 탐색 시작!
stack = collections.deque([v1])
#stack에서 원소를 한개씩 빼면서 그곳으로 부터 갈 수있는곳을 조사한다.
#갈수 없으면 또 이작업을 반복 한다.
#여기서 중요한 것은 already_visit이다. 이번 stack.pop()에서 뽑은 start위치가
#이미 간 곳이라면 뛰어넘을 수 있도록 한다 
#예로 12 13 14 24에서 1 -> 2 -> 4로 가지만 stack에는 1과 연결된 3, 4가 존재할 것이므로 그것을 제외시킬 요소가 필요하다 -> #(2)
#
while(True):
    try:
        start = stack.pop()
    except:
        break
    if start in already_visited: #(2)
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
#bfs와 dfs는 각각 선입선출과 후입선출의 차이가 있을뿐 차이가 없어서
#popleft()를 이용했다.
#
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

