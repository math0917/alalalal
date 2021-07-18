import sys

n = int(sys.stdin.readline())

arr = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

result = []
result.append([arr[0][0]])
result.append([arr[0][0]+arr[1][0],arr[0][0]+arr[1][1]])


for i in range(2,n):
    result.append([])
    result[i].append(result[i-1][0]+arr[i][0])
    for j in range(1,len(arr[i])-1):
        result[i].append(max(result[i-1][j-1]+arr[i][j],result[i-1][j]+arr[i][j]))
    result[i].append(result[i-1][-1]+arr[i][-1])
print(max(result[-1]))