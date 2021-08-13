import sys
import collections

n = int(sys.stdin.readline())

arr = [list(map(int,sys.stdin.readline().strip())) for _ in range(n)]
result = []
for i in range(n):
    for j in range(n):
        if arr[i][j] == 1:
            count = 0
            stack = collections.deque([[i,j]])
            while len(stack):
                this_turn = stack.pop()
                row, col = this_turn[0], this_turn[1]
                ptr = [[row-1,col],[row+1,col], [row,col+1],[row,col-1]]
                for k in ptr:
                    row = k[0]
                    col = k[1]
                    if row >= n or row <= -1 or col >= n or col <= -1:
                        continue
                    if arr[row][col]==1:
                        stack.append([row,col])
                        arr[row][col]= 2
                        count+=1
            result.append(count)

           
result = sorted(result)
print(len(result))
for i in result:
    if i == 0:
        print(1)
    else:
        print(i)