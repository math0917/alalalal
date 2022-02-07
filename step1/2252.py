import sys
import collections

n , m = map(int,sys.stdin.readline().split())

child = collections.defaultdict(set)

parent_count = collections.defaultdict(int)

for _ in range(m):
    tall, small = map(int,sys.stdin.readline().split())
    child[tall].add(small)
    parent_count[small] += 1

q = collections.deque([])
for i in range(1,n+1):
    if not parent_count[i]: 
        q.append(i)

while q:
    this_turn = q.popleft()
    print(this_turn,'',end='')
    for j in child[this_turn]:
        parent_count[j] -=1
        if not parent_count[j]:
            q.append(j)
            
