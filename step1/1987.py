import sys
import collections 

def per(idx,arr,alpha,depth):
        global result
        this_row = idx[0]
        this_col = idx[1]
        ptr = [[this_row-1, this_col], [this_row+1, this_col], [this_row, this_col-1], [this_row, this_col+1]]
        count=0
        for i in ptr:
            
            row = i[0]
            col = i[1]
            
            if row >= n or row <= -1 or col >= m or col <= -1 or alpha[arr[row][col]] == False:
                continue
            else:
                alpha[arr[row][col]] = False
               
                idx[0] = row
                idx[1] = col

                per(idx,arr,alpha,depth+1)

                alpha[arr[row][col]] = True
                count+=1
            
        if not count:
            
            if result < depth:
                result = depth
        
        
        
n, m = map(int,sys.stdin.readline().split())

arr = [list(map(str,sys.stdin.readline().strip())) for _ in range(n)]
for i in range(n):
    for j in range(m):
        arr[i][j] = ord(arr[i][j])- 65
alpha = [True]*26
alpha[arr[0][0]] = False

result = 1

per([0,0],arr,alpha,1)
print(result)