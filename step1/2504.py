import sys
input = sys.stdin.readline
import collections

string = list(input().strip())

stack = collections.deque([])

while string:
    this_turn = string.pop(0)
    if this_turn == '[' or this_turn == '(':
        stack.append(this_turn)
    elif this_turn == ']':
        num = 0
        flag= 0
        while stack:
            check = stack.pop()
            if type(check) == int:
                num += check
                flag = 1
            elif check == '[':
                break
            else:
                print(0)
                sys.exit()
        else:
            print(0)
            sys.exit()
        if flag:
            stack.append(3*num)
        else:
            stack.append(3)
    else :
        num = 0
        flag= 0
        while stack:
            check = stack.pop()
            if type(check) == int:
                num += check
                flag = 1
            elif check == '(':
                break
            else:
                print(0)
                sys.exit()
        else:
            print(0)
            sys.exit()
        if flag:
            stack.append(2*num)
        else:
            stack.append(2)
num = 0
while stack:
    
    this_num = stack.pop()
    if type(this_num) != int:
        print(0)
        sys.exit()
    else:
        num += this_num
print(num)