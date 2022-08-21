import sys
input = sys.stdin.readline

N, L, R, X = map(int,input().split())

level = list(map(int,input().split()))
max_len = 0
for i in range(N):
  max_len |= 1<<i

count = 0
for i in range(1, max_len+1):
  acc_sum = 0
  max_level = float('-inf')
  min_level = float('inf')
  for j in range(N):
    if i & 1<<j:
      acc_sum += level[j]
      max_level = max(max_level, level[j])
      min_level = min(min_level, level[j])
  if L <= acc_sum <= R and max_level - min_level >= X:
    count += 1
print(count)