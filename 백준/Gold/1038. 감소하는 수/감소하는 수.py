import sys
import collections

n = int(sys.stdin.readline())

q = collections.deque(['0','1','2','3','4','5','6','7','8','9'])
rank = 0
while q:
    this_turn = q.popleft()
    if rank == n:
        print(this_turn)
        sys.exit()
    rank += 1
    for i in range(10):
        if int(this_turn[-1]) > i:
            q.append(this_turn+str(i))

print(-1)