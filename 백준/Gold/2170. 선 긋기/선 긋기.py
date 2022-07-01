import sys
input = sys.stdin.readline
import collections
n = int(input())

line = [list(map(int,input().split())) for _ in range(n)]
line.sort()
stack = collections.deque([])

for i in line:
    stack.append(i)

total = 0

while stack:
    left_most,right_most = stack.popleft()
    while stack:
        this_turn = stack.popleft()
        if right_most >= this_turn[0]:
            right_most = max(right_most,this_turn[1])
        else:
            total += right_most - left_most
            stack.appendleft(this_turn)
            break
    else:
        total += right_most - left_most
print(total)