import sys
import collections
n = int(sys.stdin.readline())

arr = [sys.stdin.readline().strip() for _ in range(n)]

for i in arr:
    stack = collections.deque([])
    flag = 1
    for j in i:
        if j == '(':
            stack.append(j)
        else:
            if len(stack) == 0:
                flag = 0
                break
            else:
                tok = stack.pop()
                if tok == '(':
                    continue
    if len(stack) != 0:
        flag = 0
    if flag:
        print('YES')
    else:
        print('NO')
