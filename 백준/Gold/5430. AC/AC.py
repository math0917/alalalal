import sys
input = sys.stdin.readline
import collections

T = int(input())

for _ in range(T):
    order = input().strip()
    length = int(input())
    str = input().strip()[1:-1]
    str = str.split(',')
    deque = collections.deque([])
    front = True
    for i in range(length):
        deque.append(i)
    for i in order:
        if i == "R":
            front = not(front)
        else:
            if front:
                try:
                    deque.popleft()
                except:
                    print('error')
                    break
            else:
                try:
                    deque.pop()
                except:
                    print('error')
                    break
    else:
        string = '['
        while deque:
            if front:
                this_turn = deque.popleft()
                string += str[this_turn]+','
            else:
                this_turn = deque.pop()
                string += str[this_turn]+','
        if len(string) == 1:
            print(string + ']')
        else:
            print(string[:-1]+']')