import sys
input = sys.stdin.readline
n = int(input())
lst = []
for _ in range(n):
    lst.append(tuple(list(input().split())[1:]))

lst.sort()
string = ""

for i in range(n):
    if not(i):
        for j in range(len(lst[i])):
            print('--'*j+lst[i][j])
    else: 
        try:
            for j in range(len(lst[i])):
                if lst[i][j] == lst[i-1][j]:
                    continue
                else:
                    for t in range(j, len(lst[i])):
                        print('--'*(t) + lst[i][t])
                    break
        except:
            for t in range(j, len(lst[i])):
                print('--'*(t) + lst[i][t])
                