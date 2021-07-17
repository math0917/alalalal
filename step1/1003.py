import sys


    

n = int(sys.stdin.readline())

arr = [int(sys.stdin.readline()) for _ in range(n)]

ptr=[(1,0),(0,1)]

max_val = max(arr)
for i in range(2,max_val+1):
    ptr.append((ptr[i-2][0]+ptr[i-1][0],ptr[i-2][1]+ptr[i-1][1]))

for i in arr:
    print(ptr[i][0], ptr[i][1])

