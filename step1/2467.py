import sys

n = int(sys.stdin.readline())

arr = list(map(int,sys.stdin.readline().split()))
result_min = float('inf')
result_max = float('inf')

l, r = 0, n-1

while l<r:
    if abs(result_min + result_max) >= abs(arr[l] + arr[r]):
        result_min = arr[l]
        result_max = arr[r]
    
    
    if arr[l] + arr[r] > 0:
        r-=1
    elif arr[l] + arr[r] < 0:
        l += 1
    else:
        break

print(result_min, result_max)