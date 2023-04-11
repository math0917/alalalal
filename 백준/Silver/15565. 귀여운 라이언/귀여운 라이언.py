import sys
import collections
input = sys.stdin.readline

n, k = map(int, input().split())

arr = list(map(int,input().split()))
result = float('inf')
idx = 0
queue = collections.deque([])
while idx < len(arr):
  if arr[idx] == 1:
    queue.append(idx)
    if (len(queue) == k):
      result = min(result, idx - queue.popleft() + 1)
  idx += 1
print(-1 if result == float('inf') else result)