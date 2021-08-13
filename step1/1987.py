import sys
import collections 

def per(stack,arr,max):
        this_turn = stack.pop()
        this_row = this_turn[0][0]
        this_col = this_turn[0][1]
        
        ptr = [[this_row-1, this_col], [this_row+1, this_col], [this_row, this_col-1], [this_row, this_col+1]]
        count=0
        for i in ptr:
            
            row = i[0]
            col = i[1]
            
            if row >= n or row <= -1 or col >= m or col <= -1 :
                continue
            this_num = ord(arr[row][col])
            if this_turn[1][this_num - 65] == False:
                continue
            else:
                this_turn[1][this_num - 65] = False
               
                stack.append([(row,col),this_turn[1]])

                max = per(stack,arr,max)

                this_turn[1][this_num - 65] = True
                count+=1
            
        if not count:
            ptr = this_turn[1].count(False)
            if max < ptr:
                max = ptr
        
        return max
        
n, m = map(int,sys.stdin.readline().split())

arr = [list(map(str,sys.stdin.readline().strip())) for _ in range(n)]

visit = set()

alpha = [True]*26
alpha[ord(arr[0][0]) - ord('A')] = False
stack = collections.deque([[(0,0), alpha]])


result = per(stack,arr,0)

print(result)            

    