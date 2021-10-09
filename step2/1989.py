#idx건드리는거 연구해야됨
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
        left_idx = arr[i][2]
        
        if this_turn[0] < arr[i][0]:
            stack.append(this_turn)
            break
        else:
            right_idx = this_turn[3]
            left_idx = this_turn[2]
            if this_turn[0]*this_turn[1] > max_val:
                max_val = this_turn[0]*this_turn[1]
                max_right = right_idx
                max_left = left_idx
            weight += this_turn[1]
            while stack:
                compare = stack.pop()
                if compare[0] < arr[i][0]:
                    stack.append(compare)
                    left_idx = compare[3]+1
                    break
                else:
                    weight += compare[1]
                    if compare[0]*weight > max_val:
                        max_val = compare[0]*weight
                        max_right = right_idx
                        max_left = compare[2]
                    left_idx = compare[2]
        break
    stack.append([arr[i][0],arr[i][1]+weight,left_idx,arr[i][3]])

add = 0
while stack:
    value = stack.pop()
    add += value[1]
    right = value[3]
    if add*value[0] > max_val:
        max_val = add*value[0]
        max_left = value[2]
        max_right = right
    while stack:
        value = stack.pop()
        add += value[1]
        if add*value[0] > max_val:
            max_val = add*value[0]
            max_left = value[2]
            max_right = right
print(max_val)
print(max_left,max_right)