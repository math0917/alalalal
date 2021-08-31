import sys
from collections import deque
from copy import deepcopy

sys.setrecursionlimit(10000)

n = int(sys.stdin.readline())

result = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]
stack = deque([])
def left_shift(ptr):
    xtr = [[0]*n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if ptr[i][j] != 0 :
                if stack:
                    this_turn = stack.pop()
                   
                    if this_turn[0] == ptr[i][j] and this_turn[1] == 0:
                        
                        stack.append((this_turn[0]+ptr[i][j],1))
                    else:
                        stack.append((this_turn[0],0))
                        stack.append((ptr[i][j],0))
                else:
                    stack.append((ptr[i][j],0))
      
        idx = 0
        while stack:
            this_turn = stack.popleft()[0]
            xtr[i][idx] = this_turn
            idx+=1

    return xtr
    
def right_shift(ptr):
    xtr = [[0]*n for _ in range(n)]
    for i in range(n):
        for j in reversed(range(n)):
            if ptr[i][j] != 0 :
                if stack:
                    this_turn = stack.pop()
                    if this_turn[0] == ptr[i][j] and this_turn[1] == 0:
                        stack.append((this_turn[0]+ptr[i][j],1))
                    else:
                        stack.append((this_turn[0],0))
                        stack.append((ptr[i][j],0))
                else:
                    stack.append((ptr[i][j],0))
        idx = n-1
        while stack:
            this_turn = stack.popleft()[0]
            xtr[i][idx] = this_turn
            idx-=1

    return xtr

def upper_shift(ptr):
    xtr = [[0]*n for _ in range(n)]
    for i in range(n): #col
        for j in range(n): #row
            if ptr[j][i] != 0:
                if stack:
                    this_turn = stack.pop()
                    if this_turn[0] == ptr[j][i] and this_turn[1] == 0:
                        stack.append((this_turn[0] + ptr[j][i],1))
                    else:
                        stack.append((this_turn[0],0))
                        stack.append((ptr[j][i],0))
                else:
                    stack.append((ptr[j][i],0))
        idx = 0
        while stack:
            this_turn = stack.popleft()[0]
            xtr[idx][i] = this_turn
            idx+=1

    return xtr
    
def down_shift(ptr):
    xtr = [[0]*n for _ in range(n)]
    for i in range(n): #col
        for j in reversed(range(n)): #row
            if ptr[j][i] != 0:
                if stack:
                    this_turn = stack.pop()
                    if this_turn[0] == ptr[j][i] and this_turn[1] == 0:
                        stack.append((this_turn[0] + ptr[j][i],1))
                    else:
                        stack.append((this_turn[0],0))
                        stack.append((ptr[j][i],0))
                else:
                    stack.append((ptr[j][i],0))
        idx = n-1
        while stack:
            this_turn = stack.popleft()[0]
            xtr[idx][i] = this_turn
            idx-=1   
    
    return xtr
def dfs(count,arr):
    global max_count
    if count == 5:
        for i in range(n):
            for j in range(n):
                if max_count<arr[i][j]:
                    max_count = arr[i][j]

        return
    
    dfs(count+1,left_shift(arr))
    dfs(count+1,right_shift(arr))
    dfs(count+1,upper_shift(arr))
    dfs(count+1,down_shift(arr))
max_count = 0
dfs(0,result)

print(max_count)

