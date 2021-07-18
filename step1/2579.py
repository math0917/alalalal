import sys

n = int(sys.stdin.readline())

arr = [int(sys.stdin.readline()) for _ in range(n)]
if n == 1:
    print(arr[0])
    exit()
result = []
for i in range(n):
    result.append([])
    for j in range(2):
        result[i].append(0)
result[0][0] = arr[0]
result[1][1] = arr[0]+arr[1]
result[1][0] = arr[1]
for i in range(2,n):
    for j in range(2):
        if j == 0:
            max_val = max(result[i-2])
            result[i][j] = max_val+arr[i]
        elif j == 1:
            result[i][j] = result[i-1][0]+arr[i]
print(max(result[-1]))