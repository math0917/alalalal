import sys
import collections

n = int(sys.stdin.readline())

arr = [list(map(int,sys.stdin.readline().strip())) for _ in range(n)]


not_visit = set()
visited = set()
edge = dict()
#우선 arr을 한번 돌면서 1인 index가 생기면 row*n+col을 not_visit값에 넣기!
#또한 순차적으로 자기 자신 기준으로 오른쪽 아래쪽이 연결 되있는 경우를
#edge dict에 저장
#
for i in range(n):
    for j in range(n):
        this_idx = i*n+j
        if arr[i][j] == 1:
            not_visit.add(this_idx)
            if this_idx % n == n-1:
                if i==n-1:
                    continue
                if arr[i+1][j]:
                    edge[this_idx] = {this_idx+n}

            elif this_idx//n == n-1:
                if j == n-1:
                    continue
                if arr[i][j+1] :
                    edge[this_idx] = {this_idx+1}
            else:
                if arr[i][j+1]:
                    if arr[i+1][j]:
                        edge[this_idx] = {this_idx+1,this_idx+n}
                    else:
                        edge[this_idx] = {this_idx+1}
                else:
                    if arr[i+1][j]:
                        edge[this_idx] = {this_idx+n}
result = []
"""
print(not_visit)
print(edge)
"""
while(len(not_visit) != len(visited)):
    
    for i in (not_visit - visited):
        this_turn = collections.deque([i])
        visited.add(i)
        new_set = set([i])
        while (len(this_turn)!= 0):
            start = this_turn.pop()
            #start가 이미 edge.keys()에 있는경우
            if start in edge.keys():
                for j in edge[start]:
                    if j not in visited:
                        visited.add(j)
                        this_turn.append(j)
                        new_set.add(j)
            #start가 edge.keys()엔 없지만 특정 edge의 val값으로 있는경우
            #중에서도 왼쪽에서 오른쪽 val로 있는경우
            if (start-1 in edge.keys()) :
                if start in edge[start-1]: 
                    if start-1 not in visited:
                        visited.add(start-1)
                        this_turn.append(start-1)
                        new_set.add(start-1)
            #중에서도 위쪽에서 아래쪽 val로 있는경우
            if (start-n in edge.keys()):
                if start in edge[start-n]:
                    if start-n not in visited:
                        visited.add(start-n)
                        this_turn.append(start-n)
                        new_set.add(start-n)
        break
    result.append(new_set)
print(len(result))
ptr = [len(j) for j in result]
ptr = sorted(ptr)
for i in ptr:
    print(i)