import sys
import collections

n = int(sys.stdin.readline())

arr=[list(sys.stdin.readline().split()) for _ in range(n)]

stack = collections.deque([])

for i in arr:
    tok = i[0]
    if tok == 'push':
        stack.append(i[1])
    elif tok == 'back':
        try:
            this_turn = stack.pop()
            stack.append(this_turn)
            print(this_turn)
        except:
            print(-1)
    elif tok == 'front':
        try:
            this_turn = stack.popleft()
            stack.appendleft(this_turn)
            print(this_turn)
        except:
            print(-1)
    elif tok == 'size':
        print(len(stack))
    elif tok == 'empty':
        if len(stack) == 0:
            print(1)
        else:
            print(0)
    else:
        try:
            print(stack.popleft())
        except:
            print(-1)