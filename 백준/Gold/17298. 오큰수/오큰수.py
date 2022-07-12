import sys
import collections

n = int(sys.stdin.readline())

arr = list(map(int,sys.stdin.readline().split()))

result = []
for i in range(n):
    result.append(0)

stack = collections.deque([(arr[0],0)])
arr_idx = 1
while(True):
    if arr_idx == n:
        break
    
    this_turn = stack.pop()
    this_val = this_turn[0]
    this_idx = this_turn[1]
    if this_val >= arr[arr_idx]:
      
        stack.append((this_val,this_idx))
        stack.append((arr[arr_idx], arr_idx))
        arr_idx += 1
    else:
       
        result[this_idx]=arr[arr_idx]
        if len(stack)==0:
            stack.append((arr[arr_idx], arr_idx))
            arr_idx+=1

while(len(stack)!=0):
    result[stack.pop()[1]]=-1
for i in result:
    print(i,'',end='')