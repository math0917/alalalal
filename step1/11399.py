import sys

n = int(sys.stdin.readline())

arr = list(map(int,sys.stdin.readline().split()))

arr = sorted(arr)

len_arr = len(arr)
result = 0
for i in range(len(arr)):
    result+=(len_arr-i)*arr[i]
print(result)