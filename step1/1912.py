import sys

n = int(sys.stdin.readline())
arr = list(map(int,sys.stdin.readline().split()))

result = [[-1001,arr[0]]]

for i in range(1,n):
    max_val = max(result[i-1])
    if result[i-1][1] < 0:
        result.append([max_val,arr[i]])
    else:
        result.append([max_val,result[i-1][1]+arr[i]])


ptr = [max(j) for j in result]
print(max(ptr))