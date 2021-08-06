import sys

n = int(sys.stdin.readline())

arr = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

result = [[arr[0][0]]]

for i in range(1,len(arr)):
    result.append([])
    for j in range(i+1):
        result[i].append(0)
    result[i][0]=result[i-1][0]+arr[i][0]
    result[i][-1] = result[i-1][-1] + arr[i][-1]
    for j in range(1,len(result[i])-1):
        result[i][j] = max(result[i-1][j-1]+arr[i][j], result[i-1][j]+arr[i][j])
print(max(result[-1]))