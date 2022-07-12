import sys
input = sys.stdin.readline
import collections

string = input().strip()
stack = collections.deque([])
idx = 0
while idx < len(string):
    if string[idx] == '(':
        stack.append(string[idx])
    elif string[idx] == ')':
        num = 0 
        while stack:
            this_turn = stack.pop()
            if this_turn == '(':
                stack.append(num* int(stack.pop()))
                break
            elif type(this_turn) == int:
                num += this_turn
            else:
                num += 1
    else:
        stack.append(string[idx])        
    idx += 1
num = 0
while stack:
    this_turn = stack.pop()
    if type(this_turn) == int:
        num += this_turn
    else:
        num += 1
print(num)
    
    