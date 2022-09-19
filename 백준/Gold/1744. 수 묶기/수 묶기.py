import sys
input = sys.stdin.readline

n = int(input())

arr = [int(input()) for _ in range(n)]

arr.sort()

left = []
right = []

for i in range(len(arr)):
  if arr[i] <= 0:
    left.append(arr[i])
  else:
    right.append(arr[i])
right.reverse()

idx = 0
result = 0
for i in range(0,len(left),2):
  try:
    result += left[i]*left[i+1]
  except:
    result += left[i]
for i in range(0, len(right),2):

  try:
    if right[i+1] == 1:
      result += (1 + right[i])
      continue
    result += right[i]*right[i+1]
  except:
    result += right[i]
print(result)
