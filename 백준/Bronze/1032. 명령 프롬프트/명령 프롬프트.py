import sys
input = sys.stdin.readline

n = int(input())

pattern = [list(input().strip()) for _ in range(n)]

for i in range(len(pattern[0])):
    this_turn_set = set()
    for j in range(n):
        this_turn_set.add(pattern[j][i])
    if len(this_turn_set) == 1:
        print(pattern[0][i],end='')
    else:
        print('?',end='')