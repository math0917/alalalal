import sys
input = sys.stdin.readline
import collections
pattern = input().strip().split(':')



ipv6 = ['']*8

first = 0
idx = 0
while pattern:
    this_turn = pattern.pop(first)
    if this_turn == '':
        next_turn = pattern.pop(first)
        if next_turn == '':
            first = -1
            idx = 7
        else:
            pattern.insert(0,next_turn)
            first = -1
            idx = 7
    else:
        if first == 0:
            ipv6[idx] = this_turn
            idx += 1
        else:
            ipv6[idx] = this_turn
            idx -= 1
string = ""
for i in ipv6:
    if i == '':
        string += "0000:"
    else:
        string += "0"*(4-len(i))+i+":"
print(string[:-1])