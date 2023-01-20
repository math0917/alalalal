import sys
import collections

n = int(sys.stdin.readline())

path = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

dp = [[float('inf')]*(2**n) for _ in range(n)]
dp[0][1]=0 #0부터 2**0까지 길이 0
result = float('inf')
stack = collections.deque([[1,0,0]])
while stack:
    this_turn, length, where = stack.pop()
    for i in range(n):
        if ~this_turn & (1<<i):
            if path[where][i]:
                if dp[i][this_turn | (1<<i)] > length + path[where][i]:
                    dp[i][this_turn | (1<<i)] = length + path[where][i]
                    stack.append([this_turn | (1<<i), length + path[where][i], i])
for i in range(n):
    if path[i][0]:
        result = min(result, dp[i][-1] + path[i][0])
print(result)