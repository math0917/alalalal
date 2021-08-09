import sys
import collections

n = int(sys.stdin.readline())

sentence = [list(sys.stdin.readline().split()) for _ in range(n)]

stack = collections.deque([])

for i in sentence:
    tok = i[0]
    if tok == 'push':
        stack.append(i[1])
    elif tok == 'top':
        if len(stack) == 0:
            print(-1)
        else:
            this_turn = stack.pop()
            print(this_turn)
            stack.append(this_turn)
    elif tok == 'size':
        print(len(stack))
    elif tok == 'empty':
        if len(stack) == 0:
            print(1)
        else:
            print(0)
    else:
        if len(stack) == 0:
            print(-1)
        else:
            print(stack.pop())