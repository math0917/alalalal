import sys
import collections
n = int(sys.stdin.readline())

stack = collections.deque([])

for i in range(1,n+1):
    stack.append(i)

while(True):
    
    discard = stack.popleft()
    try:
        bottom = stack.popleft()
    except:
        print(discard)
        break
    stack.append(bottom)
