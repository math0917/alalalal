import sys
input = sys.stdin.readline

n = int(input())

arr = [int(input()) for _ in range(n)]

arr.sort(reverse = True)
max_weight = 0
for i in range(n):
  count = i+1
  weight = arr[i]
  max_weight = max(max_weight, weight *count)
print(max_weight)