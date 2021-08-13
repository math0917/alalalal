import sys
import collections 
import copy

n, m = map(int,sys.stdin.readline().split())

arr = [list(map(str,sys.stdin.readline().strip())) for _ in range(n)]
bu = set()

for i in range(len(arr)):
    for j in range(len(arr[0])):
        bu.add(arr[i][j])

stack = collections.deque([[(0,0), [bu-{arr[0][0]}]]])


max = 0

while(len(stack)!=0):
    this_turn = stack.pop()
    this_row = this_turn[0][0]
    this_col = this_turn[0][1]
    
    ptr = [[this_row-1, this_col], [this_row+1, this_col], [this_row, this_col-1], [this_row, this_col+1]]
    count=0
    for i in ptr:
        row = i[0]
        col = i[1]
        if row >= n or row <= -1 or col >= m or col <= -1:
            continue
        if arr[row][col] in this_turn :
            stack.append([(row,col), bu-(this_turn+{arr[row][col]})])
            count+=1            
        else:
            continue
           
    if not count:
        if max<len(this_turn[1]):
            max = len(this_turn[1])
    
print(max)