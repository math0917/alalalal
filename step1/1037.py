import sys

n = int(sys.stdin.readline())

arr = list(map(int,sys.stdin.readline().split()))

max_val = max(arr)
min_val = min(arr)

print(max_val*min_val)