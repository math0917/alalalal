import sys
input = sys.stdin.readline
import collections

def dfs(index):
  global answer
  visited[index] = True
  flag = False
  max_length = 0
  second_max_length = 0
  for to, length in dictionary[index]:
    if not visited[to]:
      flag = True
      this_length = dfs(to) + length
      if max_length < this_length:
        max_length,second_max_length = this_length, max_length
      elif second_max_length < this_length:
        second_max_length = this_length
  if not flag:
    return 0
  answer = max(answer, second_max_length + max_length)
  return max_length
      
  
    

n = int(input())

tree = [list(map(int,input().split())) for _ in range(n)]

dictionary = collections.defaultdict(list)

for arr in tree:
  idx = 1
  while arr[idx] != -1:
    dictionary[arr[0]].append((arr[idx],arr[idx+1]))
    idx += 2
answer = 0
visited = [False] * (n+1)
dp = [float('-inf')]*(n+1)    
dfs(1)

print(answer)