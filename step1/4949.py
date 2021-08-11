import sys
import collections
arr = []
while(True):
    sentence = sys.stdin.readline().rstrip()
    if sentence == '.':
        break
    arr.append(sentence)
# ([ (([( [ ] ) ( ) (( ))] )) ])
for i in arr:
    stack = collections.deque([])
    flag = 1
    for j in i:
        if j =='(' or j=='[':
            stack.append(j)
        elif j == ')' or j == ']':
            if len(stack) == 0:
                flag = 0   
                break
            tok = stack.pop()
            if (tok == '(' and j == ')') or (tok == '[' and j ==']'):
                continue
            else:
                flag = 0
                break
    if len(stack) != 0:
        flag = 0

    if flag:
        print('yes')
    else:
        print('no')