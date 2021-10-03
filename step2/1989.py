import sys

import collections

n = int(sys.stdin.readline())

arr = list(map(lambda x : [int(x),int(x)],sys.stdin.readline().split()))
idx = 1
for i in arr:
    i.append(idx)
    i.append(idx)
    idx+=1

stack = collections.deque([arr[0]])
max_val = -1
for i in range(1,n):
    weight = 0
    while stack:
        this_turn = stack.pop()
        if this_turn[0] < arr[i][0]:
            stack.append(this_turn)
            break
        else:
            right_idx = this_turn[2]
            if this_turn[0]*this_turn[0] < max_val:
                max_val = this_turn[0]*this_turn[0]
                max_right = right_idx
                max_left = right_idx
            weight += this_turn[1]
            while stack:

