import sys
import collections

n, p = map(int,sys.stdin.readline().split())
arr = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]
count = 0
line = []

for i in range(6):
    line.append(collections.deque([]))

for i in arr:
    this_turn_row = i[0]-1
    this_turn_prat = i[1]    
    if line[this_turn_row]:
        while line[this_turn_row]:
            compare = line[this_turn_row].pop()
            if compare > this_turn_prat:
                count+=1
            elif compare == this_turn_prat:
                line[this_turn_row].append(this_turn_prat)
                break
            else:
                line[this_turn_row].append(compare)
                line[this_turn_row].append(this_turn_prat)
                count+=1
                break
        if not line[this_turn_row]:
            line[this_turn_row].append(this_turn_prat)
            count+=1

    else:
        line[this_turn_row].append(this_turn_prat)
        count+=1
  
print(count)