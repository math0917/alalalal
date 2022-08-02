import sys
input = sys.stdin.readline
import collections

n = int(input())

lst = [int(input()) for _ in range(n)]
count = 0
lst.reverse()
stack = collections.deque([])

idx = 0

while idx < n:
    if not stack:
        stack.append([lst[idx],1])
        idx += 1
        continue
    if stack[-1][0] == lst[idx]:
        count += stack[-1][1]
        stack[-1][1] += 1
        if len(stack) != 1:
            count +=1
        idx += 1
        continue
    if stack[-1][0] > lst[idx]:
        count += 1
        stack.append([lst[idx], 1])
        idx += 1
        continue
    if stack[-1][0] < lst[idx]:
        count += stack.pop()[1]
print(count)