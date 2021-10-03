import sys
import collections
n = int(sys.stdin.readline())

histo = [[int(sys.stdin.readline()),1] for _ in range(n)]
stack = collections.deque([histo[0]])
max_area = -1
for i in range(1,n):
    number = 0
    
    while stack:
        this_turn = stack.pop()
        
        if this_turn[0] < histo[i][0]:
            stack.append(this_turn)
            break
        else:
            number += this_turn[1]
            max_area = max(max_area,number * this_turn[0])
    stack.append([histo[i][0],number+histo[i][1]])
number = 0
while stack:
    this_turn = stack.pop()
    number += this_turn[1]
    max_area = max(max_area,number * this_turn[0])
   
print(max_area)
        
