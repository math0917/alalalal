import sys

import collections

n = int(sys.stdin.readline())

arr = list(map(lambda x : [int(x),int(x)],sys.stdin.readline().split()))

stack = collections.deque([arr[0]])

max_area = -1

for i in range(1,n):
    weight = 0
   
    while stack:
        this_turn = stack.pop()
        if this_turn[0] < arr[i][0]:
            stack.append(this_turn)
            break
        else:
            weight  += this_turn[1]
          
            max_area = max(this_turn[0]*weight, max_area)
    stack.append([arr[i][0], weight+arr[i][1]])
weight = 0

while stack:
 
    this_turn = stack.pop()
    weight += this_turn[1]

    max_area = max(this_turn[0]*weight, max_area)
print(max_area)
