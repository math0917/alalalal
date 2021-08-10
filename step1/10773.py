import sys
import collections

n = int(sys.stdin.readline())

arr = [int(sys.stdin.readline()) for _ in range (n)]

stack = collections.deque([0])

for i in arr:
    if i == 0:
        stack.pop()
    else:
        stack.append(i)
result = 0
while(len(stack)!=0):
    
    num = int(stack.pop())
    result += num

print(result)