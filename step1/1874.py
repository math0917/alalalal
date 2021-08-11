import sys
import collections

n = int(sys.stdin.readline())

arr = [int(sys.stdin.readline()) for _ in range(n)]

stack = collections.deque([])
result = []
index = 0
flag = 1
num = 1

while(index<n):
    if num <= arr[index]:
        result.append('+')
        stack.append(num)
        num+=1
        continue
    else:
        if len(stack) == 0:
            flag = 0
            break
        tok = stack.pop()
        if tok != arr[index]:
            flag = 0
            break
        else:
            result.append('-')
            index+=1
            continue
if flag :
    for i in result:
        print(i,'',end='')
else:
    print('NO')