import sys

n = int(sys.stdin.readline())

arr = list(map(int,sys.stdin.readline().split()))
arr.sort()
result_min = float('inf')
result_mid = float('inf')
result_max = float('inf')

for i in range(n-2):
    l, r= i+1, n-1
    while l<r:
       
        if abs(result_min +result_mid + result_max) > abs(arr[l]+arr[r]+arr[i]):
            result_min = arr[i]
            result_mid = arr[l]
            result_max = arr[r]
        if arr[l]+arr[r]+arr[i] > 0:
            r -= 1
        elif arr[l] + arr[r] + arr[i] < 0:
            l += 1
        else:
            print(result_min, result_mid, result_max)
            sys.exit()
print(result_min, result_mid, result_max)