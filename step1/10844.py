import sys

n = int(sys.stdin.readline())

arr = []
for i in range(n+1):
    arr.append([])
    for j in range(10):
        arr[i].append(0)

for i in range(1,10):
    arr[1][i] = 1
for i in range(2,n+1):
    arr[i][1] = arr[i-1][0] 
    arr[i][-2] = arr[i-1][-1] 

    for j in range(1,9):
        arr[i][j-1] += arr[i-1][j]
        arr[i][j+1] += arr[i-1][j] 

print(sum(arr[n])%1000000000)
